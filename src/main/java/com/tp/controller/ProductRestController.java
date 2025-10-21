package com.tp.controller;

import com.tp.dao.IDao;
import com.tp.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Transactional
public class ProductRestController {

    @Autowired
    private IDao<Product> productDao;

    @GetMapping
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productDao.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productDao.create(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        productDao.update(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        Product product = productDao.findById(id);
        if (product != null) {
            productDao.delete(product);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
