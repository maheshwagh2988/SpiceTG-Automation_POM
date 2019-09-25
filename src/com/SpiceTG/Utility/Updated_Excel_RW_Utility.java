package com.SpiceTG.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Updated_Excel_RW_Utility extends SpiceTG_GlobalVariables {

	
	static XSSFWorkbook XWorkbook; 
	static XSSFSheet Sheet;
	
	//public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
//	private XSSFWorkbook SheetName = null;
//	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	public   Updated_Excel_RW_Utility() throws Exception{
		try {
			File src= new File(Filepahts);
			fis= new FileInputStream(src);
			XWorkbook= new XSSFWorkbook(fis);
			Sheet=  XWorkbook.getSheetAt(0);
			XWorkbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	//	XWorkbook.close();
	}
	public String getdata(int  sheetnumber,int row ,int column){
		Sheet=  XWorkbook.getSheetAt(sheetnumber);
		String Data=Sheet.getRow(row).getCell(column).getStringCellValue();
		return Data;
	}
	
	// returns the row count in a sheet
	public  int getRowCount(String  sheetName){
		
		int index = XWorkbook.getSheetIndex(sheetName);
		if (index == 1)
			return 0;
		else {
			Sheet = XWorkbook.getSheetAt(index);
			int number = Sheet.getLastRowNum() + 1;
			//System.out.println("Total Number of Rows in the excel is : "+number);
			return number;
			
	}
		       //OR
		
//		   Sheet = XWorkbook.getSheet(sheetName);
//	       row = Sheet.getRow(0);
//	       int rowCount = Sheet.getLastRowNum() + 1;
//	        return rowCount;
		
	}
/*	public String getCellData(String sheetName, String colName, int rowNum){
		int index = XWorkbook.getSheetIndex(sheetName);
		Sheet = XWorkbook.getSheetAt(index);
		row = Sheet.getRow(0);
		
		for (int i = 0; i < row.getLastCellNum(); i++) {
		
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()));
			System.out.println(row.getCell(i).getStringCellValue().trim()+ "\t");
			 
		}
		return "";
		
	}*/
	
	
	//update code is here
	
	public String getCellData(String sheetName, String colName, int rowNum) {
		
		int index = XWorkbook.getSheetIndex(sheetName);
		Sheet = XWorkbook.getSheetAt(index);
		row = Sheet.getRow(0);
		int col_numm = -1;
		
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if(row.getCell(i).getStringCellValue().trim().equals("Test_Case_Name"))
				col_numm = i;
			 row = Sheet.getRow(0);
			 	cell = row.getCell(col_numm);
		        String value = cell.getStringCellValue();
		        System.out.println("Value of the Excel Cell is - "+ value);
				
			Row row1 = Sheet.getRow(i);
			 if (row1 != null) {
		            Cell cell = row1.getCell(i);
		            if (cell != null) {
		                System.out.println(cell.toString());
		            }
			 }
		}
		
		/*for (int rowIndex = 0; rowIndex <Sheet.getLastRowNum(); rowIndex++) {
			  row = Sheet.getRow(rowIndex);
			  if (row != null) {
				  cell = row.getCell(rowIndex);
			    if (cell != null) {
			      // Found column and there is value in the cell.
			    String  cellValueMaybeNull = cell.getStringCellValue();
			      // Do something with the cellValueMaybeNull here ...
			    System.out.println(cellValueMaybeNull);
			    }
			  }
			}
			
			*/


		return"";
		
		
	//return "row " + rowNum + " or column " + colName + " does not exist in xls";
	}
	//code 
	
	
	public String getUpdaceCellData(String sheetName, String colName, int rowNum){
		
		try
		{
			int col_Num=-1;
		
		Sheet = XWorkbook.getSheet(sheetName);
        row = Sheet.getRow(0);
        for(int i = 0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
            	col_Num = i;
        }
        row = Sheet.getRow(rowNum - 1);
        cell = row.getCell(col_Num);
     //   System.out.print(cell.toString() +"  ");
        //if(cell.getCellType().STRING != null)
        	return cell.getStringCellValue();
		}
		
        catch(Exception e)
        {
               System.out.println(e.getMessage());
            
        }
		return "row "+rowNum+" or column "+colName +" does not exist  in Excel";
	}
	
	// returns true if sheet is created successfully else false
	public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			XWorkbook.createSheet(sheetname);
			fileOut = new FileOutputStream(Filepahts);
			XWorkbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// returns true if sheet is removed successfully else false if sheet does
		// not exist
		public boolean removeSheet(String sheetName) {
			int index = XWorkbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			FileOutputStream fileOut;
			try {
				XWorkbook.removeSheetAt(index);
				fileOut = new FileOutputStream(Filepahts);
				XWorkbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		
		// find whether sheets exists
		public boolean isSheetExist(String sheetName) {
			int index = XWorkbook.getSheetIndex(sheetName);
			if (index == -1) {
				index = XWorkbook.getSheetIndex(sheetName.toUpperCase());
				if (index == -1)
					return false;
				else
					return true;
			} else
				return true;
		}
		
		
		// returns number of columns in a sheet
		public int getColumnCount(String sheetName) {
			// check if sheet exists
			if (!isSheetExist(sheetName))
				return -1;

			Sheet = XWorkbook.getSheet(sheetName);
			row = Sheet.getRow(0);

			if (row == null)
				return -1;

			return row.getLastCellNum();

		}
		
		//Writing Data to the Cell into specified row and column
		public boolean setCellData(String sheetName, String colName, int rowNum, String data){
			try
	        {	
				fis = new FileInputStream(Filepahts);
				XWorkbook = new XSSFWorkbook(fis);
				
	            int col_Num = -1;
	            Sheet = XWorkbook.getSheet(sheetName);
	 
	            row = Sheet.getRow(0);
	            for (int i = 0; i < row.getLastCellNum(); i++) {
	                if (row.getCell(i).getStringCellValue().trim().equals(colName))
	                {
	                    col_Num = i;
	                }
	            }
	 
	            Sheet.autoSizeColumn(col_Num);
	            row = Sheet.getRow(rowNum - 1);
	            if(row==null)
	                row = Sheet.createRow(rowNum - 1);
	 
	            cell = row.getCell(col_Num);
	            if(cell == null)
	                cell = row.createCell(col_Num);
	 
	            cell.setCellValue(data);
	 
	            fileOut = new FileOutputStream(Filepahts);
	            XWorkbook.write(fileOut);
	            fileOut.close();
	        }
	        catch (Exception e)
	        {
	             System.out.println(e.getMessage());
	            return  false;
	        }
	        return true;
						
		}
		
		//If you want to Print cell number then use this method
		//you have to pass SheetName then column name and that column value it will return the number of that cell
		public int getCellRowNum(String sheetName, String colName, String cellValue) {

			for (int i = 2; i <= getRowCount(sheetName); i++) {
				if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
					return i;
				}
			}
			return -1;

		}
		
		//code updated
		/*
		public boolean setUpdatedCellData(String sheetName, String colName, int rowNum, String data) {
			try {
				fis = new FileInputStream(Filepahts);
				XWorkbook = new XSSFWorkbook(fis);

				if (rowNum <= 0)
					return false;

				int index = XWorkbook.getSheetIndex(sheetName);
				int colNum = -1;
				if (index == -1)
					return false;

				Sheet = XWorkbook.getSheetAt(index);

				row = Sheet.getRow(0);
				for (int i = 0; i < row.getLastCellNum(); i++) {
					// System.out.println(row.getCell(i).getStringCellValue().trim());
					if (row.getCell(i).getStringCellValue().trim().equals(colName))
						colNum = i;
				}
				if (colNum == -1)
					return false;

				//Sheet.autoSizeColumn(colNum);
//				row = Sheet.getRow(rowNum - 1);
//				if (row == null)
//					row = Sheet.createRow(rowNum - 1);

				cell = row.getCell(colNum);
				if (cell == null)
					cell = row.createCell(colNum);

				// cell style
				// CellStyle cs = workbook.createCellStyle();
				// cs.setWrapText(true);
				// cell.setCellStyle(cs);
				cell.setCellValue(data);

				fileOut = new FileOutputStream(Filepahts);

				XWorkbook.write(fileOut);

				fileOut.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		*/
		// returns true if column is created successfully
		public boolean addColumn(String sheetName, String colName) {
			// System.out.println("**************addColumn*********************");

			try {
				fis = new FileInputStream(Filepahts);
				XWorkbook = new XSSFWorkbook(fis);
				int index = XWorkbook.getSheetIndex(sheetName);
				if (index == -1)
					return false;

				XSSFCellStyle style = XWorkbook.createCellStyle();

				Sheet = XWorkbook.getSheetAt(index);

				row = Sheet.getRow(0);
				if (row == null)
					row = Sheet.createRow(0);

				// cell = row.getCell();
				// if (cell == null)
				// System.out.println(row.getLastCellNum());
				if (row.getLastCellNum() == -1)
					cell = row.createCell(0);
				else
					cell = row.createCell(row.getLastCellNum());

				cell.setCellValue(colName);
				cell.setCellStyle(style);

				fileOut = new FileOutputStream(Filepahts);
				XWorkbook.write(fileOut);
				fileOut.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			return true;

		}
		// removes a column and all the contents
		public boolean removeColumn(String sheetName, int colNum) {
			try {
				if (!isSheetExist(sheetName))
					return false;
				fis = new FileInputStream(Filepahts);
				XWorkbook = new XSSFWorkbook(fis);
				Sheet = XWorkbook.getSheet(sheetName);
				for (int i = 0; i < getRowCount(sheetName); i++) {
					row = Sheet.getRow(i);
					if (row != null) {
						cell = row.getCell(colNum);
						if (cell != null) {
							row.removeCell(cell);
						}
					}
				}
				fileOut = new FileOutputStream(Filepahts);
				XWorkbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
	}

	


