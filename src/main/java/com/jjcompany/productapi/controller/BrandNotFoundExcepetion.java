package com.jjcompany.productapi.controller;

public class BrandNotFoundExcepetion extends RuntimeException{
	
	public BrandNotFoundExcepetion(Long id) {
		super("Could not find product" + id);
	}

}
