package com.command.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages={"com.command.service"})
@EnableDiscoveryClient
public class CommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandServiceApplication.class, args);
	}

}
