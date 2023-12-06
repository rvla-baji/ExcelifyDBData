package io.dataexport.dataexport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dataexport.dataexport.entity.Organization;
import io.dataexport.dataexport.service.OrganizationService;

@RestController
@RequestMapping("/dataexport")
public class OrganizationController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/organzTble")
	public ResponseEntity<String> fetchDataFromDBTable() throws JsonProcessingException {

		List<Organization> tableDataList = organizationService.convertTableDatatoJsonList();

		String jsonString = objectMapper.writeValueAsString(tableDataList);

		return new ResponseEntity<>(jsonString, HttpStatus.OK);

	}

}
