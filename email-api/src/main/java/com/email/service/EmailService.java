package com.email.service;

import com.email.model.EmailRequest;

public interface EmailService {
	public void sendEmail(EmailRequest emailRequest);
}
