package com.vladis1350.admin.controllers;

import com.vladis1350.auth.repositories.UserRepository;
import com.vladis1350.constants.Http;
import com.vladis1350.services.CategoryService;
import com.vladis1350.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = Http.USERS)
    public ModelAndView showAdminPanel() {
        ModelAndView mod = new ModelAndView("admin/users");
        mod.addObject("userList", userRepository.findAll());
        return mod;
    }

    @GetMapping(value = Http.CATEGORIES)
    public ModelAndView showCategoryListInAdminPanel() {
        ModelAndView mod = new ModelAndView();
        mod.addObject("categoryList", categoryService.findAllCategories());
        return mod;
    }




}
