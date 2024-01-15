package io.dataexport.dataexport.controller;



import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
	
	@GetMapping("/download/excel")
	public ResponseEntity<ByteArrayInputStream> downloadExcelFile() throws IOException {

		ByteArrayInputStream streamOp = organizationService.convertTableDatatoJsonList();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

		return ResponseEntity.ok().headers(headers).body(streamOp);
	}

}