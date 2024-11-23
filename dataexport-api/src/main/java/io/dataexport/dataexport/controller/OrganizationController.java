package io.dataexport.dataexport.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dataexport.dataexport.service.OrganizationService;

@RestController
@RequestMapping("/dataExport")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/generateXlsFile")
	public ResponseEntity<String> generateOrgEmpDetails() throws IOException {
		String resulstMsg = organizationService.generateXlsFile();
		return new ResponseEntity<String>(resulstMsg, HttpStatus.OK);
	}
}