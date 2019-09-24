package com.SpiceTG.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRWUtility extends SpiceTG_GlobalVariables {
	/*HSSFWorkbook This class has methods to read and write Microsoft Excel files in .xls format.
	 * */
	static HSSFWorkbook HssWb;
	/*XSSFWorkbook This class has methods to read and write Microsoft Excel and OpenOffice xml files in .xls or .xlsx format. 
	 * */
	static XSSFWorkbook XSSWb; 
	static XSSFSheet SheetName;
	
	public static void Openfile() throws Exception{
	}
	public static void main(String args[]) throws Exception{
		
		try {
			File src= new File(Filepahts);
			FileInputStream fis= new FileInputStream(src);
			XSSWb= new XSSFWorkbook(fis);
			//HssWb= new HSSFWorkbook(fis);
			SheetName=  XSSWb.getSheetAt(0);
			//HSSFSheet SheetNmae1=HssWb.getSheetAt(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		int rowcount=SheetName.getLastRowNum(); 
		
		rowcount=rowcount+1;
		System.out.println("Total row count is "+rowcount);
		for(int i=0; i<rowcount;i++){
			
			String data0 =SheetName.getRow(i).getCell(0).getStringCellValue();
			String data1 =SheetName.getRow(i).getCell(1).getStringCellValue();
			System.out.println(data0);
			System.out.println(data1);
			
		}

		
		XSSWb.close();
//		HssWb.close();
		
			
	}	
	public String getdata(int  sheetnumber,int row ,int column){
		SheetName=  XSSWb.getSheetAt(sheetnumber);
		String Data=SheetName.getRow(row).getCell(column).getStringCellValue();
		return Data;
	}
	public  int getRowcount(int SheetIndex){
		
		int row=XSSWb.getSheetAt(SheetIndex).getLastRowNum();
		row=row+1;
		row=row+1;
		return row;
		
	}
		
	}
	


