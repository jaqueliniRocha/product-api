package com.jjcompany.productapi.controller;

public class ProductNotFoundException extends RuntimeException{
	
	public ProductNotFoundException(Long id) {
		super("Could not find product" + id);
	}
}
