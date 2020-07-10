package com.vladis1350.admin.controllers;

import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryManagementController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = Http.DELETE_CATEGORY + "/{id}")
    public ModelAndView deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.delete(id);
        return new ModelAndView(Pages.REDIRECT + "admin/categories");
    }

}
