package com.email.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailRequest {

	@JsonProperty("recipients")
	private List<String> recipients;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("message")
	private String message;

}