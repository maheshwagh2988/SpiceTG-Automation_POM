package com.SpiceTG.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
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

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

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
	public boolean WriteNewData(String NameSheet, int Colnumbr, int NumRow, String Setdata) throws Exception{

		try{

			fis = new FileInputStream(path);
			XSSWb = new XSSFWorkbook(fis);
			if (NumRow <= 0)
				return false;
			int index = XSSWb.getSheetIndex(NameSheet);

			int colNum = -1;
			if (index == -1)
				return false;

			sheet = XSSWb.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(Colnumbr))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(NumRow - 1);
			if (row == null)
				row = sheet.createRow(NumRow - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);
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
		String CellData=null;
		try{
		SheetName=  XSSWb.getSheetAt(sheetnumber);
		String cellData = "";
		cell = SheetName.getRow(rown).getCell(column);
        cell.setCellType(CellType.STRING);
        cellData = cell.getStringCellValue();
        /*
        if(cellData==""){
        	ScreenshotUtility.FullScreenCapture("Invalid User");
        }
        */
        return cellData;
		
	//	CellData=SheetName.getRow(rown).getCell(column).getStringCellValue();
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return CellData;
	}
	// Check Row or Column is empty 
	/*
public static boolean isRowEmpty(Row row) {
	boolean isEmpty=true;
	DataFormatter dataFormatter = new DataFormatter();
	if(row != null){
		for(Cell cell: row){
			if(dataFormatter.formatCellValue(cell).trim().length() > 0) {
                isEmpty = false;
                System.out.println("UserName or Password Field is empty");
                oDriver.close();
                break;
            }
		}
	} return isEmpty;
	
	*/
//    String data="";
//    for(Cell cell:row) {
//        data=data.concat(cell.getStringCellValue());
//    }
//    if(!data.trim().isEmpty()) {
//        isEmpty=false;
//        System.out.println("UserName or Password Field is empty");
//        oDriver.close();
//    }
//    return isEmpty;
//}
	
	//
	public String getStringData(String sheet_Name, String colName, int rowNum){
		SheetName=  XSSWb.getSheet(sheet_Name);
		int index = XSSWb.getSheetIndex(sheet_Name);
		row = SheetName.getRow(0);
		return colName;

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
	
	public int getColumnCount(String sheetName){
		sheet = XSSWb.getSheet(sheetName);
		row = sheet.getRow(0);
		if (row == null)
		return -1;
		return row.getLastCellNum();
		
	}

}




