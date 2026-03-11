package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="logindata")
	public String [][] getData() throws IOException //return type- 2D array (it will collect data from excel utility file and store in 2d array format)
	{
		ExcelUtilityFile xl=new ExcelUtilityFile(".//testData//login_data.xlsx");//give xcel utility file path
		int total_rows=xl.getRowCount("Sheet1");
		int total_clmns=xl.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String[total_rows][total_clmns]; //created 2D array whihc can store data in array format
		for(int i=1;i<=total_rows;i++)
		{
			for(int j=0;j<total_clmns;j++)
			{
				loginData[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
				
		
		return loginData;
		
	}

}
