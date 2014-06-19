package reparaciones.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shop {

	private List<Customer> customers = new ArrayList<Customer>();

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		this.getCustomers().add(customer);
	}

	public List<Customer> findCustomersByName(String name) {
		Iterator<Customer> anIterator = this.getCustomers().iterator();
		List<Customer> customers = new ArrayList<Customer>();
		Customer currentCustomer = null;

		while (anIterator.hasNext()) {
			currentCustomer = anIterator.next();
			if (currentCustomer.getFirstName().equalsIgnoreCase(name)) {
				customers.add(currentCustomer);
			}
		}

		return customers;
	}

}
