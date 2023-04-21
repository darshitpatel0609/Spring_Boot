package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
	
	List<AddressEntity> findAll();

	List<AddressEntity> findByAddressName(String addressName);
	
	

}
