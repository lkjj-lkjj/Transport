package com.example.transporter_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@ServletComponentScan(basePackages = "com.example.transporter_service.authorization")
public class TransporterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransporterServiceApplication.class, args);
    }
}
