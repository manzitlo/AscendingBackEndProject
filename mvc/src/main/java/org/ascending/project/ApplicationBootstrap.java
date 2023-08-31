package org.ascending.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"org.ascending.project"})
@ServletComponentScan(basePackages = {"org.ascending.project.filter"})
public class ApplicationBootstrap extends SpringBootServletInitializer {
    public static void main(String[] args){
        SpringApplication.run(ApplicationBootstrap.class, args);
    }

}

