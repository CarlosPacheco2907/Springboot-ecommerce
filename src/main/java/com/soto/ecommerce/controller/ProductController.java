package com.soto.ecommerce.controller;

import com.soto.ecommerce.persistence.entity.Product;
import com.soto.ecommerce.persistence.entity.User;
import com.soto.ecommerce.persistence.entity.UserType;
import com.soto.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class for handling product-related operations.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    // Logger to log information to console
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    /**
     * Constructor to inject ProductService dependency.
     *
     * @param productService ProductService instance
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Display a list of products.
     *
     * @param model Model to pass data to the view
     * @return View name for displaying the list of products
     */
    @GetMapping("")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());

        for (Product product : productService.findAll()) {
            LOGGER.info("Product list: {}", product);
        }

        return "products/show";
    }

    /**
     * Display the product creation form.
     *
     * @return View name for the product creation form
     */
    @GetMapping("/create")
    public String create() {
        return "products/create";
    }

    /**
     * Save a new product or update an existing one.
     *
     * @param product Product object to be saved or updated
     * @return Redirect to the controller method handling the product list page after saving or updating
     */
    @PostMapping("/save")
    public String save(Product product) {
        // Creating a sample user with UserType.ADMIN
        User user = new User(1, "", "", "", "", "", "", UserType.ADMIN);
        product.setUser(user);

        // If the product has a non-null ID, it is interpreted as an update
        if (product.getId() != null) {
            LOGGER.info("Updating existing product: {}", product);
            // In this case, Hibernate will update the product instead of inserting it
        } else {
            LOGGER.info("Product to be created: {}", product);
        }

        // Saving the product using the ProductService
        productService.saveProduct(product);

        // Redirecting to the controller method handling the product list page after saving or updating
        return "redirect:/products";
    }


    /**
     * Display the product edit form for a specific product ID.
     *
     * @param id    Product ID to edit
     * @param model Model to pass data to the view
     * @return View name for the product edit form
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = new Product();
        Optional<Product> optionalProduct = productService.getProduct(id);

        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
            model.addAttribute("product", product);
            LOGGER.info("Product displayed for editing: {}", product);
        } else {
            // Handle the case when the product with the given ID is not found
            LOGGER.warn("Product with ID {} not found.", id);
            // You might want to redirect to an error page or handle it appropriately.
        }

        return "products/edit";
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        LOGGER.info("Product id displayed for delete {}",id);
        //productService.deleteProduct(id);
        return "redirect:/products";
    }






}
