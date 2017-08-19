package example.vertx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"example.db","example.vertx"})
@EnableJpaRepositories(basePackages = "example.db")
@EntityScan(basePackages = "example.db")
public class VertxApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(VertxApp.class)
                .web(false)//禁用tomcat
                .run(args);
    }

}
