package com.vladis1350.controllers;

import com.vladis1350.auth.services.UserAccessService;
import com.vladis1350.bean.Product;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.constants.SuccessConstants;
import com.vladis1350.services.CategoryService;
import com.vladis1350.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = Http.HOME)
    public ModelAndView viewHomePage() {
        ModelAndView mod = new ModelAndView(Pages.HOME);
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productService.findAllProducts());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        return mod;
    }

    @PostMapping(value = Http.CANCEL)
    public String clearFilterAndSearch() {
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.SEARCH)
    public String searchProductById(@ModelAttribute(EntityConstant.ENTITY_ID_PRODUCT) Long idProduct, Model model) {
        Product product1 = productService.getProductById(idProduct);
        model.addAttribute(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        model.addAttribute(EntityConstant.PRODUCTS, product1);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        return Pages.HOME;
    }

    @PostMapping(value = Http.FILTER)
    public String filterProductByCategory(@ModelAttribute(EntityConstant.UNIT_CATEGORY) String category, Model model) {
        List<Product> productList = productService.findAllByCategoryName(category);
        model.addAttribute(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        return Pages.HOME;
    }
}
