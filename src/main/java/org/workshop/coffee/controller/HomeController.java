package org.workshop.coffee.controller;

import org.workshop.coffee.domain.Product;
import org.workshop.coffee.service.ProductService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private ProductService productService;

    @Autowired
    EntityManager em;

    @Autowired
    DataSource dataSource;


    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/index", "/home"})
    public String homePage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @PostMapping("/")
    public String searchProducts(Model model, @RequestParam String input) {
        var results = searchProduct(input);
        model.addAttribute("products", results);
        return "index";
    }

    public List<Product> searchProduct (String input) {
		// use input to search the database for products
        var query = em.createQuery("SELECT p FROM Product p WHERE lower(p.productName) LIKE :input", Product.class);
        query.setParameter("input", "%" + input.toLowerCase() + "%");
        return query.getResultList();
    }
}
