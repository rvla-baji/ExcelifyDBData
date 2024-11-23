package com.email.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.model.EmailRequest;
import com.email.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(EmailRequest emailRequest) {

		List<String> recipients = emailRequest.getRecipients();
		String subject = emailRequest.getSubject();
		String message = emailRequest.getMessage();

		boolean isValidRequest = (recipients != null && subject != null && message != null);

		if (isValidRequest) {
			sendMail(recipients, subject, message);
		} else {
			log.error("Invalid email request");
		}

	}

	private void sendMail(List<String> recipients, String subject, String message) {

		String recipientAddresses = recipients.stream().collect(Collectors.joining(", "));

		log.info("Sending email to recipients: {}", recipientAddresses);

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(recipients.toArray(new String[0]));
		mailMessage.setSubject(subject);
		mailMessage.setText(message);

		try {
			javaMailSender.send(mailMessage);
			log.info("Email sent to {} recipients", recipients.size());
		} catch (Exception e) {
			log.error("Error sending email", e);
		}
	}
}