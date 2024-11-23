package io.dataexport.dataexport.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dataexport.dataexport.constants.DataExportConstants;
import io.dataexport.dataexport.entity.Organization;
import io.dataexport.dataexport.model.OrganizationDTO;
import io.dataexport.dataexport.repository.OrganizationRepository;
import io.dataexport.dataexport.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public String generateXlsFile() throws IOException {

		List<OrganizationDTO> mappedDbData = fetchDbData();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(DataExportConstants.SHEET_NAME);

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontName(DataExportConstants.FONT_STYLE);
		headerFont.setFontHeightInPoints((short) 12);

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);

		String[] columns = { "Emp_Id", "Emp_Name", "Emp_Technology", "Emp_Location" };

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue("\t" + columns[i] + "\t");
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = 1;

		for (OrganizationDTO organizationDTO : mappedDbData) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(organizationDTO.getEmpId());
			row.createCell(1).setCellValue(organizationDTO.getEmpName());
			row.createCell(2).setCellValue(organizationDTO.getEmpTechnology());
			row.createCell(3).setCellValue(organizationDTO.getEmpLocation());
		}

		// Auto-size the columns
		for (int i = 0; i < mappedDbData.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the workbook content to a ByteArrayOutputStream
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		generateExcelFileLocally(DataExportConstants.FILE_GENERATION_PATH, outputStream);
		workbook.close();
		return "File Generated Successfully";
	}

	private List<OrganizationDTO> fetchDbData() {

		List<OrganizationDTO> orgDtoList = new ArrayList<OrganizationDTO>();
		List<Organization> fetchedDataList = organizationRepository.findAll();

		logger.info(" Fetched data list is  {}", fetchedDataList.size());

		fetchedDataList.forEach(organization -> {
			OrganizationDTO organizationDTO = new OrganizationDTO();
			organizationDTO.setEmpId(organization.getEmpId());
			organizationDTO.setEmpLocation(organization.getEmpLocation());
			organizationDTO.setEmpName(organization.getEmpName());
			organizationDTO.setEmpTechnology(organization.getEmpTechnology());
			orgDtoList.add(organizationDTO);
		});

		logger.info("Mapping :: Entity to DTO completed....!!!");
		return orgDtoList;
	}

	private void generateExcelFileLocally(String filePath, ByteArrayOutputStream out) {
		try (FileOutputStream fileOutStream = new FileOutputStream(filePath);) {
			fileOutStream.write(out.toByteArray());
			System.out.println("XLS File generated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}