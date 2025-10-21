package com.tp.controller;

import com.tp.dao.IDao;
import com.tp.entities.Product;
import com.tp.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/products")
@Transactional
public class ProductController {

    @Autowired
    private IDao<Product> productDao;

    @Autowired
    private IDao<Category> categoryDao;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryDao.findAll());
        return "product-form";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productDao.create(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable int id, Model model) {
        Product product = productDao.findById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productDao.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryDao.findAll());
        return "product-form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        productDao.update(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        Product product = productDao.findById(id);
        if (product != null) {
            productDao.delete(product);
        }
        return "redirect:/products";
    }
}