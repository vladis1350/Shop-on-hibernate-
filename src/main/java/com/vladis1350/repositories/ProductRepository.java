package com.vladis1350.repositories;

import com.vladis1350.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryName(String category);

    Product findProductByName(String name);

    Product findProductById(Long id);

    Product deleteProductById(Long id);

    @Query(value = "Update Product p SET p.discount=?2 WHERE p.category.id=?1")
    int updateDiscountByCategory(Long idCategory, BigDecimal discount);
    
}
