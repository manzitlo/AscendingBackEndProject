package org.ascending.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"org.ascending.project"})
public class ApplicationBootstrap {
    public static void main(String[] args){
        SpringApplication.run(ApplicationBootstrap.class, args);
    }
}
