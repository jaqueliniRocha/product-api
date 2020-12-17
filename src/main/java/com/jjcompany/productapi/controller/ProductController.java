package com.jjcompany.productapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jjcompany.productapi.model.Product;
import com.jjcompany.productapi.model.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class ProductController {

	private final ProductRepository repository;
	
	ProductController(ProductRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/product")
	List<Product> all(){
		return repository.findAll();
	}
	
	@PostMapping("/product")
	Product newProduct(@RequestBody Product newProduct) {
		return repository.save(newProduct);
	}
	
	
	@GetMapping("/product/{id}")
	Product one(@PathVariable Long id) {
		
		return repository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
	}
	
	
	@PutMapping("/product/{id}")
	Product replaceProduct(@RequestBody final Product newProduct, @PathVariable Long id) {
		return repository.findById(id)
				.map(product -> {
					product.setName(newProduct.getName());
					product.setFlavor(newProduct.getFlavor());
					product.setPrice(newProduct.getPrice());
					product.setBrand(newProduct.getBrand());
					return repository.save(product);
				})
				.orElseGet(() -> {
					newProduct.setId(id);
					return repository.save(newProduct);
				});
	}
	
	@DeleteMapping("/product/{id}")
	void deleteProduct(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
}
