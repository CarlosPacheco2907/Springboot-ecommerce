package com.soto.ecommerce.controller;


import com.soto.ecommerce.persistence.entity.Product;
import com.soto.ecommerce.persistence.entity.User;
import com.soto.ecommerce.persistence.entity.UserType;
import com.soto.ecommerce.service.ProductService;
import com.soto.ecommerce.service.ProductServiceImp;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    //logger to show info in console
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    public String showProducts(Model model) {

        model.addAttribute("products",productService.findAll());


        for ( Product product: productService.findAll()){
            LOGGER.info("Lista productos {}",product);
        }


        return "products/show";
    }


    @GetMapping("/create")
    public String create() {
        return "products/create";
    }


    @PostMapping("/save")
    public String save(Product product) {
        User user = new User(1,"","","","","","",UserType.ADMIN);
        product.setUser(user);
        productService.saveProduct(product);
        LOGGER.info("Objeto product {}",product.toString());

        return "redirect:/products";
    }









}
