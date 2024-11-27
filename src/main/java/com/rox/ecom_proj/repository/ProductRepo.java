package com.rox.ecom_proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rox.ecom_proj.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p from Product p WHERE "+
	"Lower(p.name) LIKE Lower(CONCAT('%', :keyword, '%')) OR " +
	"Lower(p.description) LIKE Lower(CONCAT('%', :keyword, '%')) OR " +
	"Lower(p.brand) LIKE Lower(CONCAT('%', :keyword, '%')) OR " +
	"Lower(p.category) LIKE Lower(CONCAT('%', :keyword, '%'))")
	List<Product> searchProduct(String keyword);

}
