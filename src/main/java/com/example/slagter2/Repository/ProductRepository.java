package com.example.slagter2.Repository;

import com.example.slagter2.Model.Dto.ProductDto;
import com.example.slagter2.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean animalExists(int animalId) {
        String checkSql = "SELECT COUNT(1) FROM sdj.animal WHERE animalid = ?";
        int count = jdbcTemplate.queryForObject(checkSql, new Object[]{animalId}, Integer.class);
        return count > 0;
    }

    public Product registerProduct(ProductDto productDto) {
        if (!animalExists(productDto.getAnimalId())) {
            throw new IllegalArgumentException("Animal with given ID does not exist.");
        }

        String sql = "INSERT INTO sdj.product (name, price, animalId) VALUES (?, ?, ? )";
        jdbcTemplate.update(
                sql,
                productDto.getName(),
                productDto.getPrice(),
                productDto.getAnimalId()
        );
        return null;
    }

    public void deleteProductById(int productId) {
        String sql = "DELETE FROM sdj.product WHERE productId = ?";
        jdbcTemplate.update(sql, productId);
    }

}
