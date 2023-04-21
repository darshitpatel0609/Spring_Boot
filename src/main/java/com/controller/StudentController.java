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

import com.entity.StudentEntity;
import com.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/addStudent")
	public StudentEntity addStudent(@RequestBody StudentEntity student) {
		studentRepository.save(student);
		return student;
	}

	@GetMapping("/students")
	public List<StudentEntity> getAllStudent() {
		return studentRepository.findAll();
	}

	@GetMapping("/student/byId/{studentId}")
	public StudentEntity getStudentById(@PathVariable("studentId") Integer studentId) {
		Optional<StudentEntity> proOptional = studentRepository.findById(studentId);
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proOptional.get();
		}
	}

	@GetMapping("/student/byName/{studentName}")
	public List<StudentEntity> getStudentByName(@PathVariable("studentName") String studentName) {
		return studentRepository.findByStudentName(studentName);
	}

	@DeleteMapping("/student/{studentId}")
	public StudentEntity deleteStudentById(@PathVariable("studentId") Integer studentId) {
		StudentEntity studentEntity = studentRepository.findById(studentId).get();
		studentRepository.deleteById(studentId);
		return studentEntity;
	}

	@PutMapping("/updateStudent")
	public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity) {
		studentRepository.save(studentEntity);
		return studentEntity;

	}

}