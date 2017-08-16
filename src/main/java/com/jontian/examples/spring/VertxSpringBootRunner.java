package com.jontian.examples.spring;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Runner for the vertx-spring sample
 */

public class VertxSpringBootRunner {
    private static Logger logger = LoggerFactory.getLogger(VertxSpringBootRunner.class);
    private static final Vertx vertx = Vertx.vertx();

    public static void register(Verticle verticle) {
        vertx.deployVerticle(verticle);
        logger.info("vert.x deployed "+verticle.getClass());
    }

}
