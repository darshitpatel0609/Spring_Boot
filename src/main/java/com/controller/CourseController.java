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

import com.entity.CourseEntity;
import com.repository.CourseRepository;

@RestController
public class CourseController {

	@Autowired
	CourseRepository courceRepository;

	@PostMapping("/addCource")
	public CourseEntity addCource(@RequestBody CourseEntity cource) {
		courceRepository.save(cource);
		return cource;
	}

	@GetMapping("/cources")
	public List<CourseEntity> getAllCourse() {
		return courceRepository.findAll();
	}

	@GetMapping("/course/byId/{courceId}")
	public CourseEntity getCourseById(@PathVariable("courceId") Integer courceId) {
		Optional<CourseEntity> proOptional = courceRepository.findById(courceId);
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proOptional.get();
		}
	}

	@GetMapping("/course/byName/{courceName}")
	public List<CourseEntity> getAddressByName(@PathVariable("courceName") String courceName) {
		return courceRepository.findByCourceName(courceName);
	}

	@DeleteMapping("/course/{courceId}")
	public CourseEntity deleteCourseById(@PathVariable("courceId") Integer courceId) {
		CourseEntity courceEntity = courceRepository.findById(courceId).get();
		courceRepository.deleteById(courceId);
		return courceEntity;
	}

	@PutMapping("/updateCourse")
	public CourseEntity updateCourse(@RequestBody CourseEntity course) {
		courceRepository.save(course);
		return course;

	}
}