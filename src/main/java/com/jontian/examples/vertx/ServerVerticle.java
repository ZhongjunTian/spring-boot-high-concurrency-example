package com.jontian.examples.vertx;

import com.jontian.examples.vertx.verticle.ControllerVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import org.springframework.stereotype.Service;

/**
 * Simple web server verticle to expose the results of the Spring service bean call (routed via a verticle - see
 * SpringDemoVerticle)
 */
@Service
public class ServerVerticle extends AbstractVerticle {
  public ServerVerticle() {
    VertxSpringBootRunner.register(this);
  }

  @Override
  public void start() throws Exception {
    super.start();
    HttpServer server = vertx.createHttpServer();
    server.requestHandler(req -> {
      if (req.method() == HttpMethod.GET) {
        req.response().setChunked(true);

        if (req.path().equals("/products")) {
          vertx.eventBus().<String>send(ControllerVerticle.ALL_PRODUCTS_ADDRESS, "", result -> {
            if (result.succeeded()) {
              req.response().setStatusCode(200).write(result.result().body()).end();
            } else {
              req.response().setStatusCode(500).write(result.cause().toString()).end();
            }
          });
        } else {
          req.response().setStatusCode(404).write("Page not found").end();
        }

      } else {
        // We only support GET for now
        req.response().setStatusCode(405).end();
      }
    });

    server.listen(8080);
  }
}
