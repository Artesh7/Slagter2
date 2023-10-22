package com.example.slagter2.Controller;


import com.example.slagter2.Model.Animal;
import com.example.slagter2.Model.Dto.ProductDto;
import com.example.slagter2.Model.Product;
import com.example.slagter2.Repository.AnimalRepository;
import com.example.slagter2.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final List<Product> products = new ArrayList<>();
    private int counter = 1;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody ProductDto productDto) {
        try {
            Product product = productRepository.registerProduct(productDto);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Animal with ID "+productDto.getAnimalId()+" dosen't exist.", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Integer productId) {
        return products.stream()
                .filter(a -> a.getId() == (productId))
                .findFirst()
                .orElse(null);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        try {
            productRepository.deleteProductById(productId);
            return new ResponseEntity<>("Product deleted successfully.", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting the product.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
