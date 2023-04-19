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

import com.entity.ProductEntity;
import com.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/addProduct")
	public ProductEntity addProduct(@RequestBody ProductEntity product) {
		productRepository.save(product);
		return product;
	}

	@GetMapping("/products")
	public List<ProductEntity> getAllProduct() {
		return productRepository.findAll();
	}

	@GetMapping("/product/byId/{productId}")
	public ProductEntity getProductById(@PathVariable("productId") Integer productId) {
		Optional<ProductEntity> proOptional = productRepository.findById(productId);
		if (proOptional.isEmpty()) {
			return null;
		} else {
			return proOptional.get();
		}
	}

	@GetMapping("/product/byName/{name}")
	public List<ProductEntity> getProductByName(@PathVariable("name") String name) {
		return productRepository.findByName(name);
	}

	@DeleteMapping("/product/{productId}")
	public ProductEntity deleteProductById(@PathVariable("productId") Integer productId) {
		ProductEntity productEntity = productRepository.findById(productId).get();
		productRepository.deleteById(productId);
		return productEntity;
	}

	@PutMapping("/updateProduct")
	public ProductEntity updateProduct(@RequestBody ProductEntity productEntity) {
		productRepository.save(productEntity);
		return productEntity;

	}

}
