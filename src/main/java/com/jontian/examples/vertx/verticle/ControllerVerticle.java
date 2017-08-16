package com.jontian.examples.vertx.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jontian.examples.vertx.VertxSpringBootRunner;
import com.jontian.examples.vertx.db.ProductService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Simple verticle to wrap a Spring productService bean - note we wrap the productService call
 * in executeBlocking, because we know it's going to be a JDBC call which blocks.
 * As a general principle with Spring beans, the default assumption should be that it will block unless you
 * know for sure otherwise (in other words use executeBlocking unless you know for sure your productService call will be
 * extremely quick to respond)
 */
@Service
public class ControllerVerticle extends AbstractVerticle {

  public static final String ALL_PRODUCTS_ADDRESS = "example.all.products";

  @Autowired
  private ProductService productService;

  public ControllerVerticle() {
    VertxSpringBootRunner.register(this);
  }

  private Handler<Message<String>> allProductsHandler(ProductService service) {
    // It is important to use an executeBlocking construct here
    // as the productService calls are blocking (dealing with a database)
    return msg -> vertx.<String>executeBlocking(future -> {
        try {
          ObjectMapper mapper = Json.mapper;
          future.complete(mapper.writeValueAsString(productService.getAllProducts()));
        } catch (JsonProcessingException e) {
          System.out.println("Failed to serialize result");
          future.fail(e);
        }
      },
      result -> {
        if (result.succeeded()) {
          msg.reply(result.result());
        } else {
          msg.reply(result.cause().toString());
        }
      });
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.eventBus().<String>consumer(ALL_PRODUCTS_ADDRESS).handler(allProductsHandler(productService));
  }
}
