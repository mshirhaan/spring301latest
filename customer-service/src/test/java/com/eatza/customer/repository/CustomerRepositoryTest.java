package com.eatza.customer.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.customer.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired CustomerRepository customerRepository;
	
	@Test
	public void findById() {
		Customer customer = customerRepository.findById(1).get();
		assertEquals("mshirhaan", customer.getUsername());
	}
	@Test
	public void findAll() {
		List<Customer> customers = customerRepository.findAll();
		assertEquals(2, customers.size());
	}
}
