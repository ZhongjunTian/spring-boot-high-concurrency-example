package example.vertx.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.db.Product;
import example.vertx.VertxRegister;
import example.db.ProductService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Simple verticle to wrap a Spring productService bean - note we wrap the productService call
 * in executeBlocking, because we know it's going to be a JDBC call which blocks.
 * As a general principle with Spring beans, the default assumption should be that it will block unless you
 * know for sure otherwise (in other words use executeBlocking unless you know for sure your productService call will be
 * extremely quick to respond)
 */
@Service
public class VerticalController extends AbstractVerticle {

    public static final String ALL_PRODUCTS_ADDRESS = "/products";

    @Autowired
    private ProductService productService;

    public VerticalController() {
        VertxRegister.register(this);
    }
    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus().<String>consumer(ALL_PRODUCTS_ADDRESS).handler(msg -> vertx.<String>executeBlocking(future -> {
                    try {
                        ObjectMapper mapper = Json.mapper;
                        future.complete(mapper.writeValueAsString(getAllProducts()));
                    } catch (JsonProcessingException e) {
                        System.out.println("Failed to serialize result");
                        future.fail(e);
                    }
                }, false,
                result -> {
                    if (result.succeeded()) {
                        msg.reply(result.result());
                    } else {
                        msg.reply(result.cause().toString());
                    }
                }));
    }

    private Object getAllProducts() {
        ProductService.blockIo();
        ProductService.burnCpu();
        return productService.getAllProducts();
    }
}
