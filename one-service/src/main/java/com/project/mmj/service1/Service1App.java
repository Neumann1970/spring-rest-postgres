package com.project.mmj.service1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *
 * @author Neumann
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class Service1App {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Service1App.class, args);
    }
}
