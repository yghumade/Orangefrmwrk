package utility;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetDataReader
{
	 
		private static XSSFWorkbook ExcelWBook;
		
		private static XSSFSheet ExcelWSheet;

		private static XSSFCell Cell;

		private static XSSFRow Row;

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   

	   String[][] tabArray = null;

	   try {

		   FileInputStream ExcelFile = new FileInputStream(FilePath);

		   // Access the required test data sheet

		   ExcelWBook = new XSSFWorkbook(ExcelFile);

		   ExcelWSheet = ExcelWBook.getSheet(SheetName);

		   int startRow = 1;

		   int startCol = 1;

		   int ci,cj;

		   int totalRows = ExcelWSheet.getLastRowNum();

		   // you can write a function as well to get Column count

		   int totalCols;
		   totalCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
		   tabArray=new String[totalRows][totalCols-1];
		   

		   ci=0;

		   for (int i=startRow;i<=totalRows;i++, ci++) {           	   

			  cj=0;

			   for (int j=startCol;j<totalCols;j++, cj++){

				   tabArray[ci][cj]=getCellData(i,j);
				  //  cell = =getCellData(i,j);
				   
				   System.out.println(tabArray[ci][cj]);  

					}

				}

			}

		catch (FileNotFoundException e){

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

			}

		catch (IOException e){

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

			}

		return(tabArray);

		}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		String cellValue="";
		try{

			Cell cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			
			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			{
				try{
	
				 cellValue =cell.toString(); 
				} catch (Exception e) {
		            e.printStackTrace();
		    }
		
			}
			else if (DateUtil.isCellDateFormatted(cell))
			{
			   try {

			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  cellValue =  sdf.format(cell.getDateCellValue());
			  	
			    } catch (Exception e) {
			            e.printStackTrace();
			    }
			}

		}
		catch(Exception e)
		{
			}
		return cellValue;
		}
	}

