package reparaciones.services.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import reparaciones.domain.Customer;
import reparaciones.domain.Shop;
import reparaciones.resources.CustomerResource;
import reparaciones.resources.CustomerResourceAssembler;
import reparaciones.services.CustomerResourcesService;
import reparaciones.utils.RestfulPagedResourcesAssembler;
import reparaciones.utils.RestfulPage;
import reparaciones.utils.RestfulPageable;

@Service("customerResourcesService")
public class CustomerResourcesServiceImpl implements CustomerResourcesService {

	@Autowired
	Shop shop;

	@Autowired
	private CustomerResourceAssembler assembler;

	@Autowired
	private RestfulPagedResourcesAssembler pagedResourceAssembler;

	@Override
	public Resources<CustomerResource> getCustomerResources(
			RestfulPageable pageable) {

		List<Customer> customers = shop.getCustomers();
		
		if (customers.size() > 0) {
			RestfulPage<Customer> page = RestfulPage.createPage(
					customers, pageable);
			
			return pagedResourceAssembler.toResource(page, assembler);
		}
		
		//Return an empty resource
		return new Resources<CustomerResource>(assembler.toResources(customers));
	}

	@Override
	public URI createCustomer(CustomerResource customerResource) {

		Customer customer = Customer
				.newInstance(customerResource.getFirstName(),
						customerResource.getLastName()).build();

		shop.addCustomer(customer);

		URI location = ServletUriComponentsBuilder.fromCurrentServletMapping()
				.path("/customers/{id}").build().expand(customer.getId())
				.toUri();

		return location;
	}

	@Override
	public boolean deleteCustomer(Long id) {

		return shop.removeCustomer(id);
	}

	@Override
	public CustomerResource getCustomer(Long id) {

		Customer customer = shop.findCustomerById(id);

		if (customer == null)
			return null;

		return assembler.toResource(customer);
	}

	@Override
	public boolean updateCustomer(Long id, CustomerResource customerResource) {

		Customer customer = Customer
				.newInstance(customerResource.getFirstName(),
						customerResource.getLastName())
				.id(id)
				.build();

		return shop.updateCustomer(customer);
	}

}
