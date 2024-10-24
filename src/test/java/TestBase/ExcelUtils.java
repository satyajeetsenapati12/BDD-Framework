package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class ExcelUtils {

    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFCell cell = null;
    private static final String path = "src\\main\\resources\\Excel\\SAL_Invoicing_Test_Data.xlsx";

	private static final String STRING = null;

	private static final String NUMERIC = null;

	private static final String BOOLEAN = null;

	private static final String BLANK = null;

    private String sheetName;
    private Map<String, List<Object>> recordsTable;
    private boolean isHeaderEnabled;

    public ExcelUtils()
    {
    	
    }
    public ExcelUtils(String filePath, String sheetName) throws Exception {
        this(filePath, sheetName, true);
    }

    public ExcelUtils(String filePath, String sheetName, boolean isHeaderEnabled) throws Exception {
        this.sheetName = sheetName;
        this.isHeaderEnabled = isHeaderEnabled;
        recordsTable = new LinkedHashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            int firstRowNumber = sheet.getFirstRowNum();
            int lastRowNumber = sheet.getLastRowNum();

            int headerCount = sheet.getRow(firstRowNumber).getFirstCellNum();
            Row firstRow = sheet.getRow(firstRowNumber);

            if (this.isHeaderEnabled) {
                while (headerCount < sheet.getRow(firstRowNumber).getLastCellNum()) {
                    this.recordsTable.put(cellToString(firstRow.getCell(headerCount)), new ArrayList<>());
                    headerCount++;
                }
            } else {
                String tempColumnName = "Column-";
                while (headerCount < sheet.getRow(firstRowNumber).getLastCellNum()) {
                    this.recordsTable.put(tempColumnName + headerCount, new ArrayList<>());
                    headerCount++;
                }
            }

            if (this.isHeaderEnabled) {
                for (int i = firstRowNumber + 1; i <= lastRowNumber; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        int firstColumnNumber = row.getFirstCellNum();
                        int lastColumnNumber = row.getLastCellNum();
                        for (int j = firstColumnNumber; j < lastColumnNumber; j++) {
                            this.recordsTable.get(cellToString(firstRow.getCell(j))).add(cellToString(row.getCell(j)));
                        }
                    }
                }
            } else {
                for (int i = firstRowNumber; i <= lastRowNumber; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        int firstColumnNumber = row.getFirstCellNum();
                        int lastColumnNumber = row.getLastCellNum();
                        String tempColName = "Column-";
                        for (int j = firstColumnNumber; j < lastColumnNumber; j++) {
                            this.recordsTable.get(tempColName + j).add(cellToString(row.getCell(j)));
                        }
                    }
                }
            }
        }
    }

    public Object[][] getData(String xlsFilePath, String sheetName) throws Exception {
        logger.info("Reading xlsx file: " + xlsFilePath + " for factory method.");
        Object[][] tabArray;
        try (FileInputStream file = new FileInputStream(new File(xlsFilePath))) {
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
            int noOfRow = sheet.getLastRowNum();
            int noOfCols = workbook.getSheet(sheetName).getRow(0).getLastCellNum();
            tabArray = new Object[noOfRow][noOfCols];
            for (int ci = 0; ci < noOfRow; ci++) {
                for (int c = 0; c < noOfCols; c++) {
                    cell = sheet.getRow(ci).getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    tabArray[ci][c] = getCellValueAsString(cell);
                }
            }
        }
        return tabArray;
    }

    private String getCellValueAsString(Cell cell) {
        String strCellValue = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    strCellValue = cell.toString();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        strCellValue = dateFormat.format(cell.getDateCellValue());
                    } else {
                        Double value = cell.getNumericCellValue();
                        Long longValue = value.longValue();
                        strCellValue = longValue.toString();
                    }
                    break;
                    
                case BOOLEAN:
                	
                    strCellValue = Boolean.toString(cell.getBooleanCellValue());
                    break;
                    
                case BLANK:
                    strCellValue = "";
                    break;
                default:
                    strCellValue = "";
            }
        } else {
            strCellValue = "";
        }
        return strCellValue;
    }

    public static Object getCellValueByRowColumn(String sheetName, int row, String columnTitle) {
        ExcelUtils readexcel = null;
        Object[][] data = null;
        int columnPosition = 0;

        if (row < 0 && columnTitle.isEmpty() || columnTitle == null) {
            throw new SkipException("Row and column title is not as expected");
        } else {
            try {
                readexcel = new ExcelUtils(path, sheetName);
                data = readexcel.getData(path, sheetName);
                if (data == null) {
                    throw new SkipException("Data is null");
                }
                columnPosition = Arrays.asList(data[0]).indexOf(columnTitle.trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data[row][columnPosition] != null ? data[row][columnPosition] : null;
        }
    }

    public static Object[][] getExcelData(String filePath, String sheetName) throws EncryptedDocumentException, InvalidFormatException {
        List<Object[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Object[] rowData = new Object[row.getLastCellNum()];
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        if (row.getCell(j) != null) {
                            rowData[j] = row.getCell(j).toString();
                        } else {
                            rowData[j] = "";
                        }
                    }
                    data.add(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "orderdata")
    public Object[][] leaseData() throws EncryptedDocumentException, InvalidFormatException {
        String filePath = "src/main/resources/Excel/Orders.xlsx";
        String sheetName = "OrderDetails";
        return ExcelUtils.getExcelData(filePath, sheetName);
    }

    public String getCellValue(String columnName, int rowIndex) {
        return String.valueOf(this.recordsTable.get(columnName).get(rowIndex));
    }

    public String getCellValue(int rowIndex, int columnIndex) {
        int count = 0;
        String columnHeader = null;
        for (String key : this.recordsTable.keySet()) {
            if (count == columnIndex) {
                columnHeader = key;
                break;
            }
            count++;
        }
        return String.valueOf(this.recordsTable.get(columnHeader).get(rowIndex));
    }

    public String getCellValue(String cellReference) {
        Matcher m = Pattern.compile("^([A-Z]+)([0-9]+)$").matcher(cellReference);
        if (m.matches()) {
            String columnReference = m.group(1);
            int rowNumber = this.isHeaderEnabled ? Integer.parseInt(m.group(2)) - 2 : Integer.parseInt(m.group(2)) - 1;
            int columnIndex = CellReference.convertColStringToIndex(columnReference);
            return getCellValue(rowNumber, columnIndex);
        }
        return null;
    }

    private static String cellToString(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue()).trim();
                case STRING:
                    return cell.getStringCellValue().trim();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.toString() + "";
                    } else {
                        Double doubleValue = cell.getNumericCellValue();
                        DecimalFormat format = new DecimalFormat("#.##");
                        format.setDecimalSeparatorAlwaysShown(false);
                        return format.format(doubleValue).trim();
                    }
                default:
                    return "";
            }
        }
        return "";
    }

    public void writeDataToExcel(int rowcount, int columncount, String filepath, String sheetname, String value) {
        try (FileInputStream fis = new FileInputStream(filepath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetname);
            Row row = sheet.getRow(rowcount);
            Cell cell = row.getCell(columncount);
            cell.setCellValue(value);

            try (FileOutputStream fos = new FileOutputStream(filepath)) {
                workbook.write(fos);
            }
            System.out.println("END OF WRITING DATA IN EXCEL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public int checkAvailableRow( String filepath, String Sheetname) throws IOException  {
		FileInputStream fis = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(Sheetname);
		boolean check=true;
		int i=0;
		do {
			i++;
			 Row row =sheet.getRow(i);
			 if(row==null) {
				 check=false;
			 }
			 
		}
		while(check);
		return i;
	}
	
}
