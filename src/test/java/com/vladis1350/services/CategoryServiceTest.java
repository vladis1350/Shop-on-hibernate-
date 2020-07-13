package com.vladis1350.services;

import com.vladis1350.bean.Category;
import com.vladis1350.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        category = Category.builder()
                .id(9L)
                .name("Category test")
                .build();
    }

    @Test
    void saveCategory() {
        given(this.categoryRepository.save(any()))
                .willReturn(category);
        Category categoryTest = categoryService.saveCategory(category);
        assertThat(categoryTest).isEqualTo(category);
    }

    @Test
    void findAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        given(this.categoryRepository.findAll()).willReturn(categoryList);
        List<Category> categoryList1 = categoryService.findAllCategories();
        assertThat(categoryList.size() == categoryList1.size());
    }

    @Test
    void findCategoryById() {
        given(this.categoryRepository.findCategoriesById(any()))
                .willReturn(category);
        Category categoryTest = categoryService.findCategoryById(9L);
        assertThat(categoryTest.getId()).isEqualTo(9);
    }

    @Test
    void findCategoryByName() {
        given(this.categoryRepository.findCategoriesByName(any()))
                .willReturn(category);
        Category categoryTest = categoryService.findCategoryByName("Milk category");
        assertThat(categoryTest.getName()).isNotEqualTo("Milk category");
        assertThat(categoryTest.getName()).isEqualTo("Category test");
    }

    @Test
    void delete() {
        given(this.categoryRepository.deleteCategoryById(any()))
                .willReturn(category);
        Category categoryTest = categoryService.delete(category.getId());
        assertThat(categoryTest).isEqualTo(category);
    }
}