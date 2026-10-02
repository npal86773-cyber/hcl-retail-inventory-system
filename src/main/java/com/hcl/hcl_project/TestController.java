package com.hcl.hcl_project;

import com.hcl.hcl_project.model.Product;
import com.hcl.hcl_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @Autowired
    private ProductService productService;

    // Fetch all products or search by keyword: GET http://localhost:8080/api/products?search=...
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String search) {
        if (search != null && !search.trim().isEmpty()) {
            return productService.searchProducts(search);
        }
        return productService.getAllProducts();
    }

    // Add a new product: POST http://localhost:8080/api/products
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Update an existing product: PUT http://localhost:8080/api/products/{id}
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Delete a product: DELETE http://localhost:8080/api/products/{id}
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
