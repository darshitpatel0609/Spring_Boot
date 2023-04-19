package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

}
