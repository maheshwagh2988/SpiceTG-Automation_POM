package com.SpiceTG.Utility;

public class TestExcel {

	public static void main(String[] args) throws Exception {
		Updated_Excel_RW_Utility reader= new Updated_Excel_RW_Utility();
		//Excel_ReadWrite_Utility reader=new Excel_ReadWrite_Utility();
//		String FistName=read.getCellData("Sheet1", "Test_Case_Name", 2);//Sheetnam,e Columnname, and which row data you need
//		System.out.println(FistName);

		int rowcount=reader.getColumnCount("LoginScreen");
		System.out.println("Total no of ColumnCount are: "+rowcount);
		String Test=reader.getUpdaceCellData("LoginScreen", "Test_Case_Name", 2);
		System.out.println(Test);
		String Test1=reader.getUpdaceCellData("LoginScreen", "Username", 2);
		System.out.println(Test1);
		String Test2=reader.getUpdaceCellData("LoginScreen", "Password", 2);
		System.out.println(Test2);
		String Test3=reader.getUpdaceCellData("LoginScreen", "Expected_Results", 2);
		System.out.println(Test3);
		String Test4=reader.getUpdaceCellData("LoginScreen", "Actual_Results", 2);
		System.out.println(Test4);
		String Test5=reader.getUpdaceCellData("LoginScreen", "Result", 2);
		System.out.println(Test5);
		
		
		boolean write=reader.setCellData("LoginScreen", "MyResult", 2, "Pass");
		System.out.println(write);
		
		
		
		
		
		
	}

}
