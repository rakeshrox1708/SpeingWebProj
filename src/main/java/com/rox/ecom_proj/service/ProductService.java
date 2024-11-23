package com.rox.ecom_proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rox.ecom_proj.model.Product;
import com.rox.ecom_proj.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;
	
	public List<Product> getAllProducts() {
		
		return repo.findAll();
	}

	public Product getProductById(int id) {
		
		return repo.findById(id).orElse(null);
	}

}
