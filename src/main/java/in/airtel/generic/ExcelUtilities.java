package in.airtel.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * @author Arsh Gupta
 * Created on 3rd February at 8:50 PM
 *
 */
public class ExcelUtilities 
{
	@SuppressWarnings("resource")
	public static String readData(String filepath, String sheet, int row, int cell)
	{
		String value = null;
		try {
			File file = new File(filepath);
			FileInputStream fis = null;

			fis = new FileInputStream(file);
			Workbook wb;


			wb = new XSSFWorkbook(fis);

			Cell cl = wb.getSheet(sheet).getRow(row).getCell(cell);
			switch(cl.getCellType())
			{
			case STRING:
				value = cl.getStringCellValue();
				break;

			case NUMERIC:
				if(DateUtil.isCellDateFormatted(cl))
				{
					Date date = cl.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					value= sdf.format(date);
				}
				else
				{
					long num = (long) cl.getNumericCellValue();
					value= Long.toString(num);
				}
				break;

			case BOOLEAN:
				boolean bn = cl.getBooleanCellValue();
				value= Boolean.toString(bn);
				break;
			default:
				System.out.println("Cell Format is not matching");
				break;

			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return value;
	}
}