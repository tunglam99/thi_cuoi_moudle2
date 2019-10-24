package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductType;
import com.codegym.service.ProductService;
import com.codegym.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public Iterable<ProductType> productTypes(){
        return productTypeService.findAll();
    }



    @GetMapping("/products")
    public ModelAndView listProduct(@RequestParam("s") Optional<String> s, @PageableDefault(value =5) Pageable pageable){
        Page<Product> products;
        if (s.isPresent()){
            products = productService.findAllByNameContaining(s.get(), pageable);
        }else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product",new Product());
        return modelAndView;
    }

    @PostMapping("/save-product")
    public ModelAndView saveProduct(@Validated @ModelAttribute Product product, BindingResult result){
        if (result.hasFieldErrors()){
            System.out.println("Result Error Occured" + result.getAllErrors());
        }

        productService.save(product);

        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("message","New Employee created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if (product != null){
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product",product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error:404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-product")
    public String updateProduct(@Validated @ModelAttribute("product") Product product, BindingResult result){
        if (result.hasFieldErrors()){
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        productService.save(product);

        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if (product != null){
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error:404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:/products";
    }

    @RequestMapping("/view-product/{id}")
    public ModelAndView view(@PathVariable long id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/view","product", product);
        return modelAndView;
    }


}
