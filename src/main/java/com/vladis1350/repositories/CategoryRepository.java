package com.vladis1350.repositories;

import com.vladis1350.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoriesById(Long id);

    Category findCategoriesByName(String name);
}
