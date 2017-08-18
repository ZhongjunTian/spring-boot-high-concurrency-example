package com.jontian.examples.vertx.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        blockIo();
        burnCpu();
        return repo.findAll();
    }

    protected void burnCpu() {
        double[] data = new double[10000];
        for(int i=0; i<data.length; i++){
            data[i] = Math.random();
        }
        Arrays.sort(data);
    }

    protected void blockIo() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
