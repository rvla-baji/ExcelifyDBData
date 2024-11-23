package com.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.entity.EmailLog;

public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
}
