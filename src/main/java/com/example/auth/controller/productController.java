package com.example.auth.controller;

import com.example.auth.domain.product.Product;
import com.example.auth.domain.product.ProductRequestDTO;
import com.example.auth.domain.product.ProductResponseDTO;
import com.example.auth.repository.productRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private productRepository productRepo;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequestDTO requestData) {
        var product = new Product(requestData);
        productRepo.save(product);

        return ResponseEntity.ok("Produto criado com sucesso.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDTO>> fetchAllProducts() {
        var products = productRepo.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        return ResponseEntity.ok(products);
    }
}
