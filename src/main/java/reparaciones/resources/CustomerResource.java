package reparaciones.resources;

import org.springframework.hateoas.ResourceSupport;

public class CustomerResource extends ResourceSupport {
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "CustomerResource [firstName=" + firstName + ", lastName="
				+ lastName + ", toString()=" + super.toString() + "]";
	}
	
	
}
