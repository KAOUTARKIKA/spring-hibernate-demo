package com.tp;

import com.tp.dao.ICategoryDao;
import com.tp.dao.IProductDao;
import com.tp.entities.Category;
import com.tp.entities.Product;
import com.tp.util.HibernateConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Presentation2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        IProductDao productDao = context.getBean(IProductDao.class);
        ICategoryDao categoryDao = context.getBean(ICategoryDao.class);

        Category category = new Category("Électronique", "Appareils électroniques");
        categoryDao.create(category);
        System.out.println("Catégorie sauvegardée : " + category.getName());

        Product product = new Product("Laptop Dell", 1299.99);
        product.setCategory(category);
        productDao.create(product);
        System.out.println("Produit sauvegardé : " + product.getName() + " - Prix: " + product.getPrice());

        System.out.println("\n--- Liste des produits ---");
        productDao.findAll().forEach(p ->
                System.out.println("- " + p.getName() + ": " + p.getPrice() + "€")
        );

        ((AnnotationConfigApplicationContext) context).close();
    }
}