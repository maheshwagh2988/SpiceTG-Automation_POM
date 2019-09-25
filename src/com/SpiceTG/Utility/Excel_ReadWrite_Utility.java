package com.SpiceTG.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_ReadWrite_Utility extends SpiceTG_GlobalVariables {

	
	static XSSFWorkbook XWorkbook; 
	static XSSFSheet Sheet;
	
	//public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
//	private XSSFWorkbook SheetName = null;
//	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	public   Excel_ReadWrite_Utility() throws Exception{
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
		
	}
	
	//This Method will get the data from cell we have to pass "LoginScreen", "Test_Case_Name", rowNum)
	public String getCellData(String sheetName, String colName, int rowNum){
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
       	return cell.getStringCellValue();
		}catch(Exception e)
        {
               System.out.println(e.getMessage());
        }
		return "row "+rowNum+" or column "+colName +" does not exist  in Excel";
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
		
		//This method will returns number of columns in a sheet
		//"Total Number of Column in the excel is : 
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
		//This Method will return the Total Number of Rows Count in the given excel Sheet :
		public  int getRowCount(String  sheetName){
			
			int index = XWorkbook.getSheetIndex(sheetName);
			if (index == -1)
				return 0;
			else {
				Sheet = XWorkbook.getSheetAt(index);
				int number = Sheet.getLastRowNum() + 1;
				return number;
			}
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

	


