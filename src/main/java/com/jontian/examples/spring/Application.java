package com.jontian.examples.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Simple Java Spring configuration to be used for the Spring example application. This configuration is mainly
 * composed of a database configuration and initial population via the script "products.sql" of the database for
 * querying by our Spring service bean.
 * <p>
 * The Spring service bean and repository are scanned for via @EnableJpaRepositories and @ComponentScan annotations
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(false).run(args);
    }

}
