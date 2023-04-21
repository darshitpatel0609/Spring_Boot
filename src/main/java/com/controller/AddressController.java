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

import com.entity.AddressEntity;
import com.repository.AddressRepository;
import com.repository.CustomerRepository;

@RestController
public class AddressController {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@PostMapping("/addAddress")
	public AddressEntity addAddress(@RequestBody AddressEntity address) {
		customerRepository.save(address.getCustomer());
		addressRepository.save(address);
		return address;
	}

	@GetMapping("/addresses")
	public List<AddressEntity> getAllCustomer() {
		return addressRepository.findAll();
	}

	@GetMapping("/address/byId/{addressId}")
	public AddressEntity getaddressById(@PathVariable("addressId") Integer addressId) {
		Optional<AddressEntity> proOptional = addressRepository.findById(addressId);
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proOptional.get();
		}
	}

	@GetMapping("/address/byName/{addressName}")
	public List<AddressEntity> getAddressByName(@PathVariable("addressName") String addressName) {
		return addressRepository.findByAddressName(addressName);
	}

	@DeleteMapping("/address/{addressId}")
	public AddressEntity deleteAddressById(@PathVariable("addressId") Integer addressId) {
		AddressEntity addressEntity = addressRepository.findById(addressId).get();
		addressRepository.deleteById(addressId);
		return addressEntity;
	}

	@PutMapping("/updateAddress")
	public AddressEntity updateAddress(@RequestBody AddressEntity address) {
		addressRepository.save(address);
		return address;

	}

}
