package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Entities.Product;
import com.apirest.apirest.Repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Couldn't find the product with ID: " + id));
    }
    

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product productDetails){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        productRepository.delete(product);
        return "The product with ID: " + id + " was successfully deleted";
    }

}
