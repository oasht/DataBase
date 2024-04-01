package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.example")
@EntityScan("org.example")
public class Starter {
    public static void main(String[] args) throws Exception{
       SpringApplication.run(Starter.class);

    }
}