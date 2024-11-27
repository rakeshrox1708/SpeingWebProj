package com.rox.ecom_proj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		
		
		return repo.save(product);
	}

	public Product updateProductById(int id, Product product, MultipartFile imageFile) throws IOException {
		
		product.setImageData(imageFile.getBytes());
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		return repo.save(product);
	}

	public void deleteProduct(int id) {
		repo.deleteById(id);
		
	}

	public List<Product> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchProduct(keyword);
	}

}
