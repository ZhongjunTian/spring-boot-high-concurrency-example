package com.jontian.examples.vertx.verticle;

import co.paralleluniverse.fibers.Suspendable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jontian.examples.vertx.VertxSpringBootRunner;
import com.jontian.examples.vertx.db.ProductService;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sync.SyncVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.vertx.ext.sync.Sync.awaitResult;

/**
 * Created by jtian on 8/16/2017.
 */
@Service
public class FiberVerticle extends SyncVerticle {
    public static final String FIBER_ALL_PRODUCTS_ADDRESS = "fiber-get-all";
    @Autowired
    ProductService productService;

    public FiberVerticle() {
        VertxSpringBootRunner.register(this);
    }

    @Override
    @Suspendable
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();
        eb.consumer(FIBER_ALL_PRODUCTS_ADDRESS).handler(msg -> {
                ObjectMapper mapper = Json.mapper;
                try {
                    msg.reply(mapper.writeValueAsString(productService.getAllProductsAsync()));
                } catch (JsonProcessingException e) {
                    msg.reply(e.getMessage());
                }
            }
        );
    }
}
