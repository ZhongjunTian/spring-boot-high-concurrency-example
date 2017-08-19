package io.vertx.example.sync.fiberhandler;

import co.paralleluniverse.fibers.Suspendable;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.example.util.Runner;
import io.vertx.ext.sync.SyncVerticle;

import java.util.Arrays;

import static io.vertx.ext.sync.Sync.*;

/**
 *
 *
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Server extends SyncVerticle {

  private static final String ADDRESS = "products";


  /*
  Convenience method so you can run it in your IDE
  Note if in IDE need to edit run settings and add:
  -javaagent:/home/tim/.m2/repository/co/paralleluniverse/quasar-core/0.7.2/quasar-core-0.7.2.jar
   */
  public static void main(String[] args) {
    Runner.runExample(Server.class);
  }

  @Suspendable
  @Override
  public void start() throws Exception {

    EventBus eb = vertx.eventBus();
    eb.consumer(ADDRESS).handler(msg -> {
//      System.out.println("Waiting");
      // reply after one second
      vertx.setTimer(10, tid -> msg.reply("wibble"));
      burnCpu(10000);
    });

    // If you want to do sync stuff in an async handler it must be transformed to a fiber handler
    vertx.createHttpServer().requestHandler(fiberHandler(req -> {

      // Send a message to address and wait for a reply
      Message<String> reply = awaitResult(h -> eb.send(ADDRESS, "blah", h));

//      System.out.println("Got reply: " + reply.body());

      req.response().end("blah");

    })).listen(8080, "localhost");
  }

  private static void burnCpu(int n) {
    double[] data = new double[n];
    for(int i=0; i<data.length; i++){
      data[i] = Math.random();
    }
    Arrays.sort(data);
  }
}
