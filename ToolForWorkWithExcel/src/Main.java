import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;

public class Main {

	private static final String WORKBOOK_FILENAME = "workbook.xls";
	private static final String INPUT_FILENAME = "input.xls";

	public static void main(String[] args) throws IOException {
		
		//createNew();
		//load();
		extractText(INPUT_FILENAME);
		
	}
	
	public static void createNew() throws IOException {

		Workbook wb = new HSSFWorkbook(); // or new XSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();

		Sheet sheet1 = wb.createSheet("new sheet");
		Sheet sheet2 = wb.createSheet("second sheet");

		// Note that sheet name is Excel must not exceed 31 characters
		// and must not contain any of the any of the following characters:
		// 0x0000
		// 0x0003
		// colon (:)
		// backslash (\)
		// asterisk (*)
		// question mark (?)
		// forward slash (/)
		// opening square bracket ([)
		// closing square bracket (])

		// You can use
		// org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String
		// nameProposal)}
		// for a safe way to create valid names, this utility replaces invalid
		// characters with a space (' ')
		String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
		Sheet sheet3 = wb.createSheet(safeName);

		Row row = sheet1.createRow((short) 0);
		// Create a cell and put a value in it.
		Cell cell = row.createCell(0);
		cell.setCellValue(1);

		// Or do it on one line.
		row.createCell(1).setCellValue(1.2);
		row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
		row.createCell(3).setCellValue(true);
		
		
		
		// Create a row and put some cells in it. Rows are 0 based.
	    Row row2 = sheet1.createRow(1);

	    // Create a cell and put a date value in it.  The first cell is not styled
	    // as a date.
	    Cell cell2 = row2.createCell(0);
	    cell2.setCellValue(new Date());

	    // we style the second cell as a date (and time).  It is important to
	    // create a new cell style from the workbook otherwise you can end up
	    // modifying the built in style and effecting not only this cell but other cells.
	    CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(
	        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
	    cell2 = row2.createCell(1);
	    cell2.setCellValue(new Date());
	    cell2.setCellStyle(cellStyle);

	    //you can also set date as java.util.Calendar
	    cell2 = row2.createCell(2);
	    cell2.setCellValue(Calendar.getInstance());
	    cell2.setCellStyle(cellStyle);

	    
	   
	    Row row3 = sheet1.createRow(2);
	    row3.createCell(0).setCellValue(1.1);
	    row3.createCell(1).setCellValue(new Date());
	    row3.createCell(2).setCellValue(Calendar.getInstance());
	    row3.createCell(3).setCellValue("a string");
	    row3.createCell(4).setCellValue(true);
	    row3.createCell(5).setCellType(Cell.CELL_TYPE_ERROR);
	    
	    
	    

		FileOutputStream fileOut = new FileOutputStream(WORKBOOK_FILENAME);
		wb.write(fileOut);
		fileOut.close();

	}

	public static void load() throws IOException {
		
		//Workbook wb = WorkbookFactory.create(new File("MyExcel.xls"));
		 
		NPOIFSFileSystem fs = new NPOIFSFileSystem(new File(WORKBOOK_FILENAME));
		HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);

		Sheet sheet1 = wb.getSheetAt(0);
	    for (Row row : sheet1) {
	        for (Cell cell : row) {
	            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            System.out.print(cellRef.formatAsString());
	            System.out.print(" - ");

	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                    System.out.println(cell.getRichStringCellValue().getString());
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        System.out.println(cell.getDateCellValue());
	                    } else {
	                        System.out.println(cell.getNumericCellValue());
	                    }
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                    System.out.println(cell.getBooleanCellValue());
	                    break;
	                case Cell.CELL_TYPE_FORMULA:
	                    System.out.println(cell.getCellFormula());
	                    break;
	                default:
	                    System.out.println();
	            }
	        }
	    }
		
		fs.close();
		
	}
	
	public static void extractText(String fileName) throws IOException {
		
	    InputStream inp = new FileInputStream(fileName);
	    HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
	    ExcelExtractor extractor = new org.apache.poi.hssf.extractor.ExcelExtractor(wb);

	    extractor.setFormulasNotResults(true);
	    extractor.setIncludeSheetNames(false);
	    String text = extractor.getText();
	    
	    System.out.println(text);

	}
	
}
