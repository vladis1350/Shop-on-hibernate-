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
        return productRepository.findProductById(id);
    }

    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    public Product deleteProduct(Long id) {
        return productRepository.deleteProductById(id);
    }

    public List<Product> findAllByCategoryName(String category) {
        return productRepository.findAllByCategoryName(category);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public int updateDiscountByCategory(Long idCategory, BigDecimal discount) {
        return productRepository.updateDiscountByCategory(idCategory, discount);
    }

    public List<Product> saveProductAll(List<Product> productList) {
        return productRepository.saveAll(productList);
    }
}
