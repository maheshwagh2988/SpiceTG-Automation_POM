package com.SpiceTG.Utility;

public class ParametarizedData {

	public static void main(String[] args) throws Exception {
		Updated_Excel_RW_Utility reader= new Updated_Excel_RW_Utility();
		
		int rowcount=reader.getRowCount("LoginScreen");//to get total no of rows
		System.out.println("Total Number of Rows in the excel is : "+rowcount);
		
		int ColumnCount=reader.getColumnCount("LoginScreen");
		System.out.println("Total Number of Column in the excel is : "+ColumnCount);
		
		
		//this will add the column with Name "TestResult"
		reader.addColumn("LoginScreen", "TestResult");
		
		for(int rowNum=2;rowNum<=rowcount;rowNum++){
			System.out.println("*****************************************");
			String TC_Name=reader.getUpdaceCellData("LoginScreen", "Test_Case_Name", rowNum);
			System.out.println(TC_Name);
			
			String URName=reader.getUpdaceCellData("LoginScreen", "Username", rowNum);
			System.out.println(URName);
			
			String Pwd=reader.getUpdaceCellData("LoginScreen", "Password", rowNum);
			System.out.println(Pwd);
			
			String Exp_Ruslt=reader.getUpdaceCellData("LoginScreen", "Expected_Results", rowNum);
			System.out.println(Exp_Ruslt);
			
			String Act_Ruslt=reader.getUpdaceCellData("LoginScreen", "Actual_Results", rowNum);
			System.out.println(Act_Ruslt);
			
			String TestRuslt=reader.getUpdaceCellData("LoginScreen", "Result", rowNum);
			System.out.println(TestRuslt);
			
			//this will add the column with Name "TestResult" and Print the data with name "Pass"
			reader.setCellData("LoginScreen", "TestResult", rowNum, "Pass");
			
			
			
			
		}

	}

}
