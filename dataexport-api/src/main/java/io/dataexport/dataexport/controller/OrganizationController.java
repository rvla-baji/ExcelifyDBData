package io.dataexport.dataexport.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
	public ResponseEntity<byte[]> downloadExcelFile() throws IOException {

		ByteArrayInputStream streamOp = organizationService.convertTableDatatoJsonList();
		byte[] excelFileContent = IOUtils.toByteArray(streamOp);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=organization_emp_details.xlsx");
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		return ResponseEntity.ok().headers(headers).body(excelFileContent);
	}
}