package org.ascending.project.consumer;

import org.ascending.project.consumer.service.SQSMessageService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"org.ascending.project.consumer"})
public class ConsumerApp {
    public static void main(String[] args) {

        SpringApplication.run(ConsumerApp.class,args);
/*
        ConfigurableApplicationContext app = SpringApplication.run(ConsumerApp.class, args);
        SQSMessageService messageService = app.getBean(SQSMessageService.class);

        messageService.receiveMessage();
*/

    }
}
