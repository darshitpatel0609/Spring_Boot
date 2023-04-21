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

import com.entity.EmployeeEntity;
import com.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping("/addEmployee")
	public EmployeeEntity addEmoployee(@RequestBody EmployeeEntity employee) {
		employeeRepository.save(employee);
		return employee;
	}

	@GetMapping("/employees")
	public List<EmployeeEntity> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@GetMapping("/empoloyee/byId/{employeeId}")
	public EmployeeEntity getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
		Optional<EmployeeEntity> proOptional = employeeRepository.findById(employeeId);
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proOptional.get();
		}
	}

	@GetMapping("/employee/byName/{employeeName}")
	public List<EmployeeEntity> getEmployeeByName(@PathVariable("employeeName") String employeeName) {
		return employeeRepository.findByEmployeeName(employeeName);
	}

	@DeleteMapping("/employee/{employeeId}")
	public EmployeeEntity deleteEmployeeById(@PathVariable("employeeId") Integer employeeId) {
		EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
		employeeRepository.deleteById(employeeId);
		return employeeEntity;
	}

	@PutMapping("/updateEmployee")
	public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity employee) {
		employeeRepository.save(employee);
		return employee;

	}

}
