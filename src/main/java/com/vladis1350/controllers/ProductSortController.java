package com.vladis1350.controllers;

import com.vladis1350.auth.services.UserAccessService;
import com.vladis1350.bean.Product;
import com.vladis1350.constants.*;
import com.vladis1350.services.CategoryService;
import com.vladis1350.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductSortController {

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = Http.SORTING_NAME)
    public ModelAndView sortingProductsByName(@RequestParam(name = "sortByName", required = false) String variable) {
        ModelAndView mod = new ModelAndView();

        List<Product> productList = productService.findAllProducts();
        if (variable.equals(SortingOptions.ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName, Collections.reverseOrder()));
        }
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productList);
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.setViewName(Pages.HOME);
        return mod;
    }

    @PostMapping(value = Http.SORTING_PRICE)
    public ModelAndView sortingProductsByPrice(@RequestParam(name = "sortByPrice", required = false) String variable) {
        ModelAndView mod = new ModelAndView();

            List<Product> productList = productService.findAllProducts();
        if (variable.equals(SortingOptions.ORDER_PRICE)) {
            productList.sort(Comparator.comparing(Product::getPrice));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_PRICE)){
            productList.sort(Comparator.comparing(Product::getPrice, Collections.reverseOrder()));
        }
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productList);
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.setViewName(Pages.HOME);
        return mod;
    }

    @PostMapping(value = Http.SORTING_CATEGORY)
    public ModelAndView sortingProductsByCategory(@RequestParam(name = "sortByCategory", required = false) String variable) {
        ModelAndView mod = new ModelAndView();

        List<Product> productList = productService.findAllProducts();
        if (variable.equals(SortingOptions.ORDER_CATEGORY)) {
            productList.sort(Comparator.comparing(o -> o.getCategory().getName()));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_CATEGORY)){
            productList.sort((o1, o2) -> o2.getCategory().getName().compareTo(o1.getCategory().getName()));
        }
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productList);
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.setViewName(Pages.HOME);
        return mod;
    }

    @PostMapping(value = Http.SORTING_DISCOUNT)
    public ModelAndView sortingProductsByDiscount(@RequestParam(name = "sortByDiscount", required = false) String variable) {
        ModelAndView mod = new ModelAndView();

        List<Product> productList = productService.findAllProducts();
        if (variable.equals(SortingOptions.ORDER_DISCOUNT)) {
            productList.sort(Comparator.comparing(Product::getDiscount));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_DISCOUNT)){
            productList.sort(Comparator.comparing(Product::getDiscount, Collections.reverseOrder()));
        }
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productList);
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.setViewName(Pages.HOME);
        return mod;
    }
}
