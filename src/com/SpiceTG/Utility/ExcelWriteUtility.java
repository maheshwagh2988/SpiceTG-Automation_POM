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
	public String src;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
//	private XSSFWorkbook SheetName = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	
	public void Openfile(String path ) throws Exception{
		this.path = path;
			
		try {
			File src= new File(path);
			FileInputStream fis= new FileInputStream(src);
			XSSWb= new XSSFWorkbook(fis);
			SheetName=  XSSWb.getSheetAt(0);
			//int rowcount=SheetName.getLastRowNum();
			//System.out.println("Total Row Count is::: " +rowcount);
			
			
//			SheetName.getRow(1).createCell(5).setCellValue("Pass");
//			SheetName.getRow(2).createCell(5).setCellValue("fails");
//			FileOutputStream fout=new FileOutputStream(src);
//			XSSWb.write(fout);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
				
//		int rowcount=SheetName.getLastRowNum();
//		rowcount=rowcount+1;
//		System.out.println("Total row count is "+rowcount);
//		for(int i=0; i<rowcount;i++){
//			
//			String data0 =SheetName.getRow(i).getCell(0).getStringCellValue();
//			String data1 =SheetName.getRow(i).getCell(1).getStringCellValue();
//			System.out.println(data0);
//			System.out.println(data1);
//		}
		XSSWb.close();
}
	
	public int readData(){
		
		int rowcount=SheetName.getLastRowNum();
		rowcount=rowcount+1;
		//row = SheetName.getRow(0);
		System.out.println("Total row count is "+rowcount);
		for(int i=0; i<rowcount;i++){
			
			System.out.println("********Excel file Rows Data are given blow********");
			String data0 =SheetName.getRow(i).getCell(0).getStringCellValue();
			String data1 =SheetName.getRow(i).getCell(1).getStringCellValue();
			String data2 =SheetName.getRow(i).getCell(2).getStringCellValue();
			String data3 =SheetName.getRow(i).getCell(3).getStringCellValue();
			String data4 =SheetName.getRow(i).getCell(4).getStringCellValue();
			String data5 =SheetName.getRow(i).getCell(5).getStringCellValue();
			System.out.println(data0);
			System.out.println(data1);
			System.out.println(data2);
			System.out.println(data3);
			System.out.println(data4);
			System.out.println(data5);
		}
		
		return rowcount;
		
	}
	
	public int writeData() throws Exception{
	SheetName.getRow(1).createCell(5).setCellValue("Pass");
	SheetName.getRow(2).createCell(5).setCellValue("fails");
	fileOut = new FileOutputStream(path);
	XSSWb.write(fileOut);
	fileOut.close();
	return 0;
	}
	//Modifed WriteDatainto cell
	public boolean WriteNewData(String NameSheet, int NameCol, int NumRow, String Setdata) throws Exception{
		
		try{
			
			fis = new FileInputStream(path);
			XSSWb = new XSSFWorkbook(fis);
			
	//	int Shetname=XSSWb.getSheetIndex(NameSheet);
		int index = XSSWb.getSheetIndex(NameSheet);
		sheet = XSSWb.getSheetAt(index);
		int NameColu = -1;
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			 System.out.println(row.getCell(i).getStringCellValue().trim());
			if (row.getCell(i).getStringCellValue().trim().equals(NameCol))
				NameColu = i;
		}
		sheet.autoSizeColumn(NameColu);
		row = sheet.getRow(NumRow - 1);
		row = sheet.createRow(NumRow - 1);


		cell = row.getCell(NameCol);
		cell = row.createCell(NameCol);
		cell.setCellValue(Setdata);
		fileOut = new FileOutputStream(path);
		XSSWb.write(fileOut);
		fileOut.close();
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
		return true;
	}
	
	
	
	public  String getdata(int  sheetnumber,int rown ,int column){
		SheetName=  XSSWb.getSheetAt(sheetnumber);
		String Data=SheetName.getRow(rown).getCell(column).getStringCellValue();
		return Data;
		//return "row "+row+" or column "+column +" does not exist  in Excel";
		
		//Modified code
		/*
		try{
		
		//SheetName=  XSSWb.getSheetAt(sheetnumber);
		int index = XSSWb.getSheetIndex(SheetName);
		sheet = XSSWb.getSheetAt(index);
		row = sheet.getRow(rown - 1);
		cell = row.getCell(rown);
	
		return cell.getStringCellValue();
	}catch (Exception e) {

		e.printStackTrace();
		return "row " + rown + " or column " + column + " does not exist  in xls";
	}  */
	}
	
	
	public  String getNewdata(String sheetName, String colName, int rowNum){
		try
		{
		int col_Num=-1;
		SheetName = XSSWb.getSheet(sheetName);
        row = SheetName.getRow(0);
        for(int i = 0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
            	col_Num = i;
        }
        row = SheetName.getRow(rowNum - 1);
        cell = row.getCell(col_Num);
       	return cell.getStringCellValue();
		}catch(Exception e)
        {
               System.out.println(e.getMessage());
        }
		return "row "+rowNum+" or column "+colName +" does not exist  in Excel";
	}
	
	
	//public  int getRowcount(int SheetIndex){
		//int row=XSSWb.getSheetAt(SheetIndex).getLastRowNum();
		//row=row+1;
		//return row;
	public  int getRowcount(String Shet_Name){
		/*
			int index=XSSWb.getSheetIndex(Shet_Name);
			//System.out.println("Name of Sheet is Print ::: "+index);
			
			
			SheetName=XSSWb.getSheetAt(index);
			int number =SheetName.getLastRowNum();
			return number;
			------------------*/
			int index = XSSWb.getSheetIndex(Shet_Name);
			if (index == -1)
				return 0;
			else {
				SheetName = XSSWb.getSheetAt(index);
				int number = SheetName.getLastRowNum() + 1;
				return number;
			}
			
		}

	}
	
	


