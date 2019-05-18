package in.airtel.generic;

import java.io.File;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class LoginDataProvider 
{
	@DataProvider(name="LoginData")
	public Object[][] getData() throws Exception
	{
		File file = new File("./testdata/testData.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		Object[][] username = new Object[rowCount][2];
		for(int i=0; i<rowCount; i++)
		{
			Row row = sheet.getRow(i);
			for(int j=0; j< row.getLastCellNum(); j++)
			{
				username[i][j] = ""+row.getCell(j);
			}
		}
		for(int i =0; i<username.length; i++)
		{
			System.out.println(Arrays.toString(username[i]));
		}
		return username;
	}
}
