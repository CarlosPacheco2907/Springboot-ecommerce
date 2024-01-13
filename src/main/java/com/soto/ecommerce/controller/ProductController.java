package com.soto.ecommerce.controller;

import com.soto.ecommerce.persistence.entity.Product;
import com.soto.ecommerce.persistence.entity.User;
import com.soto.ecommerce.persistence.entity.UserType;
import com.soto.ecommerce.service.ProductService;
import com.soto.ecommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final UploadFileService uploadFileService;

    /**
     * Constructor to inject ProductService dependency.
     *
     * @param productService ProductService instance
     */
    public ProductController(ProductService productService, UploadFileService uploadFileService) {
        this.productService = productService;
        this.uploadFileService = uploadFileService;
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
     * Save a new product
     *
     * @param product Product object to be saved or updated
     * @return Redirect to the controller method handling the product list page after saving or updating
     */
    @PostMapping("/save")
    public String save(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        // Creating a sample user with UserType.ADMIN
        User user = new User(1, "", "", "", "", "", "", UserType.ADMIN);
        product.setUser(user);


        if (!file.isEmpty()) { //if the file image is not null

            // Image Upload to File System
            // Invokes the service to save the image to the file system.
            // Parameters:
            //   - file: The image file to be uploaded, represented as a MultipartFile object.
            // Returns:
            //   - nameImage: The name of the saved image file. If the file is empty, "default.jpg" is returned.
            String nameImage = uploadFileService.saveImage(file);

            //Saves the image name in our product object.
            product.setImage(nameImage);
            LOGGER.info("Product to be created with image: {}", product);
        } else {
            //if the image file is null
            product.setImage("default.jpg");
            LOGGER.info("Product to be created without image {}", product);
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


    /**
     * Handles POST requests to update a product.
     *
     * @param product The product to be updated. It is bound from the request data.
     * @param file    The MultipartFile representing the image of the product.
     * @return A String representing the view or redirect URL. Redirects to "/products" after updating.
     */
    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product, @RequestParam("img") MultipartFile file) throws IOException {
        Optional<Product> optionalProduct = productService.getProduct(product.getId());
        Product p = new Product();

        //get the product in our bd
        p = productService.getProduct(product.getId()).get();

        if (file.isEmpty()) { //If the image file is null, then the image is not updated

            //Saves the image name in our product object.
            product.setImage(p.getImage());
            LOGGER.info("Product to be update: {}", product);
        } else {//if the image is not null, then the image is updated

            // Image Upload to File System
            // Invokes the service to save the image to the file system.
            // Parameters:
            //   - file: The image file to be uploaded, represented as a MultipartFile object.
            // Returns:
            //   - nameImage: The name of the saved image file
            String nameImage = uploadFileService.saveImage(file);

            //if the image is being updated, delete the old image
            //delete image
            if (!p.getName().equals("default.jpg")) {
                uploadFileService.deleteImage(p.getImage());
            }
            //Saves the image name in our product object.
            product.setImage(nameImage);
            LOGGER.info("Product to be update: {}", product);
        }



        product.setUser(p.getUser());
        productService.saveProduct(product);

        return "redirect:/products";
    }


    /**
     * Controller method handling GET requests to delete a product by ID.
     *
     * @param id The ID of the product to be deleted.
     * @return A string indicating a redirect to the "/products" route after successfully deleting the product.
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Product p = new Product();
        p = productService.getProduct(id).get();
        //delete image
        if (!p.getImage().equals("default.jpg")) {
            uploadFileService.deleteImage(p.getImage());
        }
        // Logs information in the LOGGER indicating the ID of the product to be deleted.
        LOGGER.info("Product ID displayed for delete {}", p);
        // Calls the deleteProduct method of the productService to delete the product with the provided ID.
        productService.deleteProduct(id);

        // Returns a string indicating a redirect to the "/products" route after successfully deleting the product.
        return "redirect:/products";
    }


}
