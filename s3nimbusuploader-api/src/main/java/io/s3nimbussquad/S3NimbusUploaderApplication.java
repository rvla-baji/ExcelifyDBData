package io.s3nimbussquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages  = {"io.s3nimbussquad","io.s3nimbussquad.service.impl"})
public class S3NimbusUploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3NimbusUploaderApplication.class, args);
	}
}