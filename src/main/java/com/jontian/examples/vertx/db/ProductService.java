package com.jontian.examples.vertx.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        try {
            Thread.sleep(100_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return repo.findAll();
    }

    @Async
    public CompletableFuture<List<Product>> getAllProductsAsync() {
        List<Product> products = getAllProducts();
        return CompletableFuture.completedFuture(products);
    }
}
