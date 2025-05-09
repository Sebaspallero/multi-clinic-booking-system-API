package com.sebastian.clinicbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableAsync
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(title = "Multi Clinic Booking System API", version = "1.0", description = "API for booking appointments in multiple clinics"),
    servers = @Server(url = "http://localhost:8080")
)
@SpringBootApplication
public class ClinicbookingApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClinicbookingApplication.class, args);
	}
}
