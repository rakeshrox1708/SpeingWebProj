package com.rox.ecom_proj.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rox.ecom_proj.model.Product;
import com.rox.ecom_proj.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class productController {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping("/")
	public String greet() {
		return "Hello World!";
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		Product product = service.getProductById(id);
		
		if(product != null)
		return new ResponseEntity<>(product, HttpStatus.OK);
		else
			return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product,
			@RequestPart MultipartFile imageFile){
		try {
		Product product1 = service.addProduct(product, imageFile);
		return new ResponseEntity<>(product1, HttpStatus.CREATED);
		
	}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		}
	
	@GetMapping("/product/{productid}/image")
	public ResponseEntity<byte[]> getImageByProductd(@PathVariable int productid){
		Product product=service.getProductById(productid);
		byte[] imagefile = product.getImageData();
		
		return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
				.body(imagefile);
				
		
	}

}
