
package com.example.controller;

import java.util.List;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.repository.CustomeRepository;

/**
 * @author ratneshc
 *
 */


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	private Logger LOGGER = Logger.getLogger(CustomerController.class.getName());


	@Autowired
	CustomeRepository customerRepository;

	@GetMapping("/test")
	public String test() {
		LOGGER.info("get all customers");
		return "REST API test success!";
	}
	
	@GetMapping("/all")
	public List<Customer> getAllCustomers() {
		LOGGER.info("get all customers");
		return customerRepository.findAll();
	}

	@PostMapping("/create")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Customer> findById(@PathVariable(value = "id") Long customerId) {
		Customer customer = customerRepository.findOne(customerId);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}

}