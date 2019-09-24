package com.SpiceTG.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_ReadWrite_Utility extends SpiceTG_GlobalVariables {

	
	static XSSFWorkbook XWorkbook; 
	static XSSFSheet Sheet;
	
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
//	private XSSFWorkbook SheetName = null;
//	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	public   Excel_ReadWrite_Utility() throws Exception{
		try {
			File src= new File(Filepahts);
			FileInputStream fis= new FileInputStream(src);
			XWorkbook= new XSSFWorkbook(fis);
			Sheet=  XWorkbook.getSheetAt(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		XWorkbook.close();
	}
	public String getdata(int  sheetnumber,int row ,int column){
		Sheet=  XWorkbook.getSheetAt(sheetnumber);
		String Data=Sheet.getRow(row).getCell(column).getStringCellValue();
		return Data;
		
	}
	
	// returns the row count in a sheet
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
	public String getCellData(String sheetName, String colName, int rowNum){
		int index = XWorkbook.getSheetIndex(sheetName);
		Sheet = XWorkbook.getSheetAt(index);
		row = Sheet.getRow(0);
		
		for (int i = 0; i < row.getLastCellNum(); i++) {
		
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()));
			System.out.println(row.getCell(i).getStringCellValue().trim()+ "\t");
			 
		}
		return "";
		
	}
	
	
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
			fileOut = new FileOutputStream(path);
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
				fileOut = new FileOutputStream(path);
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
		
		
		public int getCellRowNum(String sheetName, String colName, String cellValue) {

			for (int i = 2; i <= getRowCount(sheetName); i++) {
				if (getUpdaceCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
					return i;
				}
			}
			return -1;

		}
		
	}

	


