package com.email.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.service.EmailService;

@RestController
@RequestMapping("email-api")
public class EmailController {

	
	private EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/send-email")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
		emailService.sendEmail(emailRequest);
		return ResponseEntity.ok("Email sent successfully to all recipient emails!");
	}

}