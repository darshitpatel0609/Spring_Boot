package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.DepartmentEntity;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {

	List<DepartmentEntity> findAll();

	List<DepartmentEntity> findByDepartmentName(String departmentName);
}