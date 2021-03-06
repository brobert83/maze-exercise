package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"web", "escape"})
public class LabEscapeSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabEscapeSpringBootApplication.class, args);
    }

}
