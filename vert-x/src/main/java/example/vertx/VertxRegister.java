package example.vertx;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Runner for the vertx-vertx sample
 */

public class VertxRegister {
    private static Logger logger = LoggerFactory.getLogger(VertxRegister.class);
    private static final Vertx vertx;
    static{
        VertxOptions options = new VertxOptions();
        options.setWorkerPoolSize(200);
        vertx = Vertx.vertx(options);
    }

    public static void register(Verticle verticle) {
        vertx.deployVerticle(verticle);
        logger.info("vert.x deployed "+verticle.getClass());
    }

}
