import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProviderIntegration {
	
	DataFormatter formatter=new DataFormatter();
	@Test(dataProvider="driveset")
	public void testcasedata(String greeting,String communication,String id)
	{
		System.out.println(greeting+communication+id);
		
	}
	@DataProvider(name="driveset")
	public Object[][] getData() throws IOException
	{
		FileInputStream fis =new FileInputStream("G:\\RahulShetty\\DemoData1.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		int rowcount=sheet.getPhysicalNumberOfRows();
		XSSFRow row=sheet.getRow(0);
		int colcount=row.getLastCellNum();
		Object data[][]=new Object [rowcount-1][colcount];
		for(int i=0;i<rowcount-1;i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<colcount;j++)
			{
				XSSFCell cell=row.getCell(j);
				data[i][j]=formatter.formatCellValue(cell);
			}
		}
		return data;
		
		
		
		
	}

}
