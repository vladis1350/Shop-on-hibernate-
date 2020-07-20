package com.vladis1350.admin.controllers;

import com.vladis1350.bean.Category;
import com.vladis1350.bean.Product;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.services.CategoryService;
import com.vladis1350.services.ProductService;
import com.vladis1350.validate.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = Http.NEW_PRODUCT)
    public ModelAndView showNewProductsForm() {
        ModelAndView mod = new ModelAndView();
        mod.addObject(EntityConstant.UNIT_PRODUCT, new Product());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.setViewName(Pages.NEW_PRODUCT);
        return mod;
    }

    @GetMapping("/admin/products")
    public ModelAndView showProductListInAdminPanel() {
        ModelAndView mod = new ModelAndView();
        mod.addObject("productList", productService.findAllProducts());
        mod.addObject("categoryList", categoryService.findAllCategories());
        return mod;
    }

    @PostMapping(value = Http.SAVE_PRODUCT)
    public ModelAndView  showProduct(@RequestParam(value = "productName", required = false) String productName,
                                    @RequestParam(value = "price", required = false) BigDecimal price,
                                    @RequestParam(value = "discount", required = false) BigDecimal discount,
                                    @RequestParam(value = "category", required = false) Long idCategory,
                                    @RequestParam(value = "description", required = false) String description) {
        ModelAndView mod = new ModelAndView();
        Category category = categoryService.findCategoryById(idCategory);
        Product product = Product.builder()
                .name(productName)
                .price(price)
                .discount(discount)
                .category(category)
                .description(description)
                .build();
        Product newProduct = productService.findProductByName(productName);
        if (newProduct != null) {
            mod.addObject("errorMessage", "Продукт с таким именем уже сужествует!");
            mod.addObject(EntityConstant.PRODUCTS, productService.findAllProducts());
            mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
            mod.setViewName(Pages.PRODUCTS_PAGE);
            return mod;
        }
        if (!ProductValidator.validateName(productName)) {
            mod.addObject("errProductName", "Нзвание должно быть не менее 3-х и не более 32 символов!");
        }
        if (!ProductValidator.validatePrice(price)) {
            mod.addObject("errProductPrice", "Цена не может быть меньше 0!");
        }
        if (!ProductValidator.validateDiscount(discount)) {
            mod.addObject("errProductDiscount", "Скидка не может быть меньше 0 и больше 100%!");
        }
        if (ProductValidator.checkValidateDataProduct(product)){
            productService.saveProduct(product);
            mod.addObject("successMessage", "Продукт успешно добавлен");
        }
        mod.addObject(EntityConstant.PRODUCTS, productService.findAllProducts());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.setViewName(Pages.PRODUCTS_PAGE);
        return mod;
    }

    @GetMapping(value = Http.EDIT_PRODUCT + "/{id}")
    public ModelAndView showEditProductsForm(@PathVariable(name = "id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView(Pages.EDIT_PRODUCT);
        Product product = productService.getProductById(id);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        modelAndView.addObject(EntityConstant.UNIT_PRODUCT, product);
        return modelAndView;
    }

    @PostMapping(value = Http.SAVE_EDIT_PRODUCT)
    public ModelAndView saveEditProduct(@ModelAttribute(EntityConstant.UNIT_PRODUCT) Product product) {
        ModelAndView mod = new ModelAndView();
        if (!ProductValidator.validateName(product.getName())) {
            mod.addObject("errProductName", "Нзвание должно быть не менее 3-х и не более 32 символов!");
        }
        if (!ProductValidator.validatePrice(product.getPrice())) {
            mod.addObject("errProductPrice", "Цена не может быть меньше 0!");
        }
        if (!ProductValidator.validateDiscount(product.getDiscount())) {
            mod.addObject("errProductDiscount", "Скидка не может быть меньше 0 и больше 100%!");
        }
        if (!ProductValidator.checkValidateDataProduct(product)){
            mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
            mod.setViewName("/admin/edit_product");
            return mod;
        } else {
            productService.updateProduct(product);
            mod.addObject("successMessage", "Данные о продукте изменены");
        }
        mod.addObject(EntityConstant.PRODUCTS, productService.findAllProducts());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.setViewName("/admin/products");
        return mod;
    }

    @GetMapping(value = Http.DELETE_PRODUCT + "/{id}")
    public ModelAndView deleteProducts(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return new ModelAndView(Pages.REDIRECT + "admin/products");
    }

    @GetMapping(value = "/admin/"+ Http.DISCOUNT)
    public ModelAndView showSetDiscountForm()  {
        ModelAndView mod = new ModelAndView("admin/set_discount");
        Product product = new Product();
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAllCategories());
        mod.addObject(EntityConstant.UNIT_PRODUCT, product);

        return mod;
    }

    @PostMapping(value = Http.SET_DISCOUNT)
    public ModelAndView setDiscountForCategory(
            @ModelAttribute(EntityConstant.UNIT_CATEGORY) Long idCategory, BigDecimal discount) {
        productService.updateDiscountByCategory(idCategory, discount);
        return new ModelAndView(Pages.REDIRECT + "admin/products");
    }
}
