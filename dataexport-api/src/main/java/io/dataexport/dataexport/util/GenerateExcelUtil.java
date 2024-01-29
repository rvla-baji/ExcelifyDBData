package io.dataexport.dataexport.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.dataexport.dataexport.constants.DataExportConstants;
import io.dataexport.dataexport.model.OrganizationDTO;

@Component
public class GenerateExcelUtil {

	@Autowired
	private WebServiceUtil webServiceUtil;

	public ByteArrayInputStream createExcelFile(List<OrganizationDTO> orgDtoLost) throws IOException {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(DataExportConstants.SHEET_NAME);

			// Create a bold font
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontName(DataExportConstants.FONT_STYLE);
			headerFont.setFontHeightInPoints((short) 11);

			// Create a cell style with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Create a header row
			Row headerRow = sheet.createRow(0);
			String[] columns = { "Emp_Id", "Emp_Name", "Emp_Technology", "Emp_Location" };
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(" " + columns[i] + " ");
				cell.setCellStyle(headerCellStyle);
			}

			// Create data rows for each employee
			int rowNum = 1;
			for (OrganizationDTO orgDtoObj : orgDtoLost) {
				Row row = sheet.createRow(rowNum++);
				// for testing purpose
				System.out.println(orgDtoObj);
				row.createCell(0).setCellValue(orgDtoObj.getEmpId());
				row.createCell(1).setCellValue(orgDtoObj.getEmpName());
				row.createCell(2).setCellValue(orgDtoObj.getEmpTechnology());
				row.createCell(3).setCellValue(orgDtoObj.getEmpLocation());
			}

			// Auto-size columns
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(out);
			generateExcelFileLocally(DataExportConstants.FILE_GENERATION_PATH, out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	private void generateExcelFileLocally(String filePath, ByteArrayOutputStream out) {
		try (FileOutputStream fileOutStream = new FileOutputStream(filePath);) {
			fileOutStream.write(out.toByteArray());
			System.out.println("** File generated successfully **");
			webServiceUtil.uploadToNimbusService(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}