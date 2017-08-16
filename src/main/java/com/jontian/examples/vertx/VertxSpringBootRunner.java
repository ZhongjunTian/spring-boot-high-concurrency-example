package com.jontian.examples.vertx;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Runner for the vertx-vertx sample
 */

public class VertxSpringBootRunner {
    private static Logger logger = LoggerFactory.getLogger(VertxSpringBootRunner.class);
    private static final Vertx vertx = Vertx.vertx();

    public static void register(Verticle verticle) {
        vertx.deployVerticle(verticle);
        logger.info("vert.x deployed "+verticle.getClass());
    }

}
