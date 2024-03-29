package com.soto.ecommerce.controller;


import com.soto.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

   private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/home";
    }


}
