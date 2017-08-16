package com.jontian.examples.vertx.db;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository to connect our service bean to data
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
