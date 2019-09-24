package com.SpiceTG.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Index;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;


public  class ExcelWriteUtility extends SpiceTG_GlobalVariables {
	
	
	static XSSFWorkbook XSSWb; 
	static XSSFSheet SheetName;
	
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
//	private XSSFWorkbook SheetName = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	
	public void Openfile() throws Exception{
	
	
		
		try {
			File src= new File(Filepahts);
			FileInputStream fis= new FileInputStream(src);
			XSSWb= new XSSFWorkbook(fis);
			SheetName=  XSSWb.getSheetAt(0);
			
			
		
			SheetName.getRow(1).createCell(5).setCellValue("Pass");
			SheetName.getRow(2).createCell(5).setCellValue("fails");
			
			
			FileOutputStream fout=new FileOutputStream(src);
			XSSWb.write(fout);
			
			
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
}	
	public  String getdata(int  sheetnumber,int row ,int column){
		SheetName=  XSSWb.getSheetAt(sheetnumber);
		String Data=SheetName.getRow(row).getCell(column).getStringCellValue();
		return Data;
	}
	public  int getRowcount(int SheetIndex){
		int row=XSSWb.getSheetAt(SheetIndex).getLastRowNum();
		row=row+1;
		return row;
//	public  int getRowcount(String Shet_Name){
//		int index=XSSWb.getSheetIndex(Shet_Name);
//		if(index==1)
//			return 0;
//		else{
//			SheetName=XSSWb.getSheetAt(index);
//			int number =SheetName.getLastRowNum();
//			return number;
//		}

	}
	}
	


