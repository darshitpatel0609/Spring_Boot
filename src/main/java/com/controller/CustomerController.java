package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.repository.AddressRepository;
import com.repository.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@PostMapping("/addCustomer")
	public CustomerEntity addCustomer(@RequestBody CustomerEntity customer) {
		System.out.println(customer.getEmailId());
		System.out.println(customer.getAddress().getAddressName());
		addressRepository.save(customer.getAddress());
		//save address -> db -> addressId 
		//save customer -> 
		customerRepository.save(customer);
		return customer;
	}

	@GetMapping("/customers")
	public List<CustomerEntity> getAllCustomer() {
		return customerRepository.findAll();
	}

	@GetMapping("/customer/byId/{customerId}")
	public CustomerEntity getCustomerById(@PathVariable("customerId") Integer customerId) {
		Optional<CustomerEntity> proOptional = customerRepository.findById(customerId);
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proOptional.get();
		}
	}

	@GetMapping("/customer/byName/{name}")
	public List<CustomerEntity> getCustomerByName(@PathVariable("name") String name) {
		return customerRepository.findByName(name);
	}

	@DeleteMapping("/customer/{customerId}")
	public CustomerEntity deleteCustomerById(@PathVariable("customerId") Integer customerId) {
		CustomerEntity customerEntity = customerRepository.findById(customerId).get();
		customerRepository.deleteById(customerId);
		return customerEntity;
	}

	@PutMapping("/updateCustomer")
	public CustomerEntity updateCustomer(@RequestBody CustomerEntity customerEntity) {
		customerRepository.save(customerEntity);
		return customerEntity;

	}

}
