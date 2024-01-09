package com.soto.ecommerce.controller;


import com.soto.ecommerce.persistence.entity.Product;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("")
    public String showProducts() {
        return "products/show";
    }


    @GetMapping("/create")
    public String create() {
        return "products/create";
    }


    @PostMapping("/save")
    public String save(Product product) {
        LOGGER.info("Obejto product {}",product.toString());
        return "redirect:/products";
    }


}
