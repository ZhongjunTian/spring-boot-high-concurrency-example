package example.tomcat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"example.db","example.tomcat"})
@EnableJpaRepositories(basePackages = "example.db")
@EntityScan(basePackages = "example.db")
public class TomcatApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TomcatApp.class).run(args);
    }

}
