package com.vladis1350.services;

import com.vladis1350.bean.Product;
import com.vladis1350.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        product = productRepository.save(product);
        return product;
    }

    public Product updateProduct(Product product) {
        product = productRepository.save(product);
        return product;
    }

    public Product getProductById(Long id) {
        Optional<Product> prod = productRepository.findById(id);
        return prod.orElse(null);
    }

    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByCategoryName(String category) {
        return productRepository.findAllByCategoryName(category);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void updateDiscountByCategory(Long idCategory, BigDecimal discount) {
        String query = "Update products SET discount=:discount WHERE category_id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("discount", discount)
                .addValue("id", idCategory);
        namedParameterJdbcTemplate.update(query, parameterSource);
    }
}
