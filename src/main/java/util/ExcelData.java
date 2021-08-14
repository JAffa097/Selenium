package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	private String testcaseID;
	public Properties prop;
	int requiredRowNumber;
	public Map<String, String> testdata = new HashMap<String, String>();
	int k;

	public ExcelData(String testcaseID) {
		this.testcaseID = testcaseID;
	}

	public Map<String, String> getTestData() {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("src\\main\\resources\\config.properties")));
			System.out.println();
			XSSFWorkbook workbook = new XSSFWorkbook(
					new FileInputStream(prop.getProperty("testDataPath") + "OrangeHRM.xlsx"));
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			XSSFRow row = sheet.getRow(0);
			int totalRows = sheet.getLastRowNum();
			int totalcolumns = row.getLastCellNum();
			// to get row number for requiredtestcase
			for (int i = 0; i <= totalRows; i++) {

				if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testcaseID)) {
					requiredRowNumber = i;
					break;
				}
			}
			for (int j = 0; j < totalcolumns; j++) {
				try {

				testdata.put(sheet.getRow(0).getCell(j).getStringCellValue(),sheet.getRow(requiredRowNumber).getCell(j).getStringCellValue());
				}catch(Exception e){}
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return testdata;
	}

}
