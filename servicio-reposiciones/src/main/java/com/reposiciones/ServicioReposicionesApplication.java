package com.reposiciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioReposicionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioReposicionesApplication.class, args);
	}

}
