package com.vladis1350.controllers;

import com.vladis1350.bean.Category;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryServiceController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = Http.NEW_CATEGORY)
    public String showNewCategoryForm(Model model) {
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        return Pages.CATEGORY;
    }

    @PostMapping(value = Http.SAVE_CATEGORY)
    public ModelAndView showCategory(@RequestParam(value = "categoryName") String categoryName) {
        ModelAndView mod = new ModelAndView(Pages.REDIRECT + "admin/categories");
        Category category = Category.builder()
                .name(categoryName)
                .build();
        if (categoryName.equals("")) {
            mod.addObject("errCategoryName", "Введите название категории.");
            return mod;
        }
        Category categoryDb = categoryService.findCategoryByName(categoryName);
        if (categoryDb != null) {
            mod.addObject("errCategoryName", "Такая категория уже существует.");
            return mod;
        }
        categoryService.saveCategory(category);
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        return mod;
    }
}
