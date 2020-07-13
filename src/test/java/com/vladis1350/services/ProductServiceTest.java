package com.vladis1350.services;

import com.vladis1350.bean.Category;
import com.vladis1350.bean.Product;
import com.vladis1350.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceTest {

    private Product product;
    private Product productTwo;
    private Category category;
    private Category categoryTwo;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        category = Category.builder().name("Milk category").build();
        categoryTwo = Category.builder().name("Chips category").build();
        product = Product.builder()
                .id(2L)
                .name("Salo")
                .category(category)
                .price(BigDecimal.valueOf(12.60))
                .discount(BigDecimal.valueOf(11.20))
                .build();

        productTwo = Product.builder()
                .id(2L)
                .name("Chitos")
                .category(categoryTwo)
                .price(BigDecimal.valueOf(12.60))
                .discount(BigDecimal.valueOf(11.20))
                .build();
    }

    @Test
    void saveProduct() {
        given(this.productRepository.findProductById(any()))
                .willReturn(product);
        Product product1 = productService.getProductById(2L);
        assertThat(product1.getId()).isEqualTo(2);
    }

    @Test
    void updateProduct() {
        given(this.productRepository.save(any()))
                .willReturn(product);
        Product product1 = productService.updateProduct(productTwo);
        assertThat(product1).isEqualTo(product);
    }

    @Test
    void getProductById() {
        given(this.productRepository.findProductById(any()))
                .willReturn(product);
        Product product1 = productService.getProductById(2L);
        assertThat(product1.getId()).isEqualTo(2L);
    }

    @Test
    void findProductByName() {
        given(this.productRepository.findProductByName(any()))
                .willReturn(product);
        Product product1 = productService.findProductByName("Salo");
        assertThat(product1.getName()).isEqualTo("Salo");
    }

    @Test
    void deleteProduct() {
        given(this.productRepository.deleteProductById(any()))
                .willReturn(product);
        Product product1 = productService.deleteProduct(2L);
        assertThat(product1).isEqualTo(product);
    }

    @Test
    void findAllByCategoryName() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        given(this.productRepository.findAllByCategoryName("Milk category")).willReturn(productList);
        List<Product> productList1 = productService.findAllByCategoryName("Milk category");
        assertThat(productList.size() == productList1.size());
    }

    @Test
    void findAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(productTwo);
        given(this.productRepository.findAll()).willReturn(productList);
        List<Product> productList1 = productService.findAllProducts();
        assertThat(productList.size() == productList1.size());
    }

    @Test
    void updateDiscountByCategory() {
        given(this.productRepository.updateDiscountByCategory(any(), any()))
                .willReturn(1);
        int countField = productService.updateDiscountByCategory(2L, BigDecimal.valueOf(16));
        assertThat(countField).isEqualTo(1);
    }

    @Test
    void saveAll() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(productTwo);
        given(this.productRepository.saveAll(productList)).willReturn(productList);
        List<Product> productList1 = productService.saveAll(productList);
        assertThat(productList.size() == productList1.size());
    }
}