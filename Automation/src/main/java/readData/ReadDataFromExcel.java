package readData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class is used to get data from excel
 * 
 * @author nksharma
 *
 */

public class ReadDataFromExcel {

	private static String sheetName;

	public ReadDataFromExcel(String sheetName) {
		this.sheetName = sheetName;
	}

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;

	/*
	 * This method is used to set data using HashMap in Key and value pair.
	 */

	public static Map<String, Map<String, String>> setData() throws IOException {

		String filePath = System.getProperty("user.dir") + File.separator + "src"
				+ File.separator +"main" + File.separator + "java" + File.separator +"testData" + File.separator +"credentials.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		int lastrow = sheet.getLastRowNum();
		System.out.println(lastrow);
		Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();
		HashMap<String, String> hm = new HashMap<String, String>();

		for (int i = 0; i <= lastrow; i++) {

			row = sheet.getRow(i);
			Cell keycell = row.getCell(0);
			Cell valueCell = row.getCell(1);
			String key = keycell.getStringCellValue().toString();
			String value = valueCell.getStringCellValue().toString();
			hm.put(key, value);
			excelFileMap.put(sheetName, hm);
		}
		return excelFileMap;
	}

	/*
	 * This method is used to get value of key.
	 */

	public String getkeyValue(String key) throws IOException {

		Map<String, String> m = setData().get(sheetName);
		String value = m.get(key);
		return value;
	}

}
