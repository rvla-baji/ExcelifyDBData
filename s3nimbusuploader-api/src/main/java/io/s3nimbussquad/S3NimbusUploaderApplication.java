package io.s3nimbussquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class S3NimbusUploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3NimbusUploaderApplication.class, args);
	}
}