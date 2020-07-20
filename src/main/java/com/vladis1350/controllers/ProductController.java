package com.vladis1350.controllers;

import com.vladis1350.auth.services.UserAccessService;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.constants.SuccessConstants;
import com.vladis1350.services.CategoryService;
import com.vladis1350.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView searchProductById(@RequestParam(name = "searchProduct", required = false) String productName) {
        ModelAndView mod = new ModelAndView(Pages.HOME);
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());

        if (productName.equals("")) {
            mod.addObject("errSearch", "Введите название продукта!");
        }
        mod.addObject(EntityConstant.PRODUCTS, productService.searchProductByName(productName));
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        return mod;
    }

    @PostMapping(value = Http.FILTER)
    public ModelAndView filterProductByCategory(@RequestParam(name = "categoryFilter", required = false) String category) {
        ModelAndView mod = new ModelAndView();
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.addObject(EntityConstant.PRODUCTS, productService.findAllByCategoryName(category));
        mod.setViewName(Pages.HOME);
        return mod;
    }
}
