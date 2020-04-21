package com.eatza.customer.dto;

import com.eatza.customer.model.Customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CustomerDto {

	private String firstName;
	private String middleName;
	private String lastName;
	private boolean isActive;
	private String username;
	private String password;
	
	public CustomerDto(Customer customer) {
		this.firstName = customer.getFirstName();
		this.middleName = customer.getMiddleName();
		this.lastName = customer.getLastName();
		this.isActive = customer.isActive();
		this.username = customer.getUsername();
		this.password = customer.getPassword();
	}
}
