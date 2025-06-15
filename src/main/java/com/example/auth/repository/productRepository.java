package com.example.auth.repository;

import com.example.auth.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, String> {
}
