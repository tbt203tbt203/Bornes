/*package com.ntico.postgresqlApplication.controller;


import com.ntico.postgresqlApplication.model.Product;
import com.ntico.postgresqlApplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @GetMapping("/all")
    public ResponseEntity getAllProduct(){
        Product produit = new Product();
        produit.setemail("ffff@gggg");
        produit.setpseudo("fff");
        produit.setstatut("ok");
        productRepository.save(produit);
        return ResponseEntity.ok(this.productRepository.findAll());
    }
    /*@GetMapping("/pseudo")
    public ResponseEntity getUsername(){
        Product pseudo = new Product();

        pseudo.setpseudo("tbt");

        productRepository.save(pseudo);
        return ResponseEntity.ok(this.productRepository.findByPseudo("tbt"));
    }*//*
}
*/