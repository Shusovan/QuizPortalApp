package com.project.securityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.project.securityservice")
public class SecurityserviceApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(SecurityserviceApplication.class, args);
	}

}
