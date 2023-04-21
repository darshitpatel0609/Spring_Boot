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

import com.entity.DepartmentEntity;
import com.repository.DepartmentRepository;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentRepository departmentRepository;

	@PostMapping("/addDepartment")
	public DepartmentEntity addDepartment(@RequestBody DepartmentEntity departmentEntity) {
		departmentRepository.save(departmentEntity);
		return departmentEntity;
	}

	@GetMapping("/departments")
	public List<DepartmentEntity> getAllDepartment() {

		return departmentRepository.findAll();

	}

	@GetMapping("/department/byId/{departmentId}")
	public DepartmentEntity getDepartmentById(@PathVariable("departmentId") Integer departmentId) {
		Optional<DepartmentEntity> department = departmentRepository.findById(departmentId);
		if (department.isEmpty()) {
			return null;
		} else {
			return department.get();
		}

	}

	@GetMapping("/department/byName/{departmentName}")
	public List<DepartmentEntity> getDepartmentByName(@PathVariable("departmentName") String departmentName) {
		return departmentRepository.findByDepartmentName(departmentName);
	}

	@DeleteMapping("/department/{departmentId}")
	public DepartmentEntity deleteById(@PathVariable("departmentId") Integer departmentId) {
		DepartmentEntity depart = departmentRepository.findById(departmentId).get();
		departmentRepository.delete(depart);
		return depart;
	}

	@PutMapping("/updateDepartment")
	public DepartmentEntity updateDepartment(@RequestBody DepartmentEntity depart) {
		departmentRepository.save(depart);
		return depart;

	}

}