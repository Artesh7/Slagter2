package com.example.slagter2.Controller;


import com.example.slagter2.Model.Animal;
import com.example.slagter2.Model.Dto.ProductDto;
import com.example.slagter2.Model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final List<Product> products = new ArrayList<>();
    private int counter = 1;

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @PostMapping
    public Product registerProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(counter++);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        products.add(product);
        return product;
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Integer productId) {
        return products.stream()
                .filter(a -> a.getId() == (productId))
                .findFirst()
                .orElse(null);
    }

}
