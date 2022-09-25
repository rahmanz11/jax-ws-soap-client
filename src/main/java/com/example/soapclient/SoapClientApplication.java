package com.example.soapclient;

import com.example.soapclient.client.BridgeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.soapclient")
@SpringBootApplication
public class SoapClientApplication {

	@Autowired
	BridgeClient client;

	public static void main(String[] args) {
		SpringApplication.run(SoapClientApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("In CommandLineRunnerImpl ");
			client.call();
		};
	}
}
