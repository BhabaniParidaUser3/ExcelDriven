import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	
	public ArrayList<String> getData(String testcasename) throws IOException
	{
		ArrayList<String>a =new ArrayList<String>();
		//to read the file create fis object
		FileInputStream fis=new FileInputStream("G:\\RahulShetty\\DemoData.xlsx");
		//workbook object having the ability to go inside fis object
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int sheets=workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata"))
			{
				XSSFSheet sheet=workbook.getSheetAt(i);//sheet is collection of rows
				Iterator<Row> rows=sheet.iterator();
				Row firstrow =rows.next();//rows is collection of cell
				Iterator<Cell> ce=firstrow.cellIterator();
				int k=0;
				int column=0;
				
				while(ce.hasNext())
				{
				Cell value=	ce.next();
				//Identify Testcases column by scanning the entire 1st row
				if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
				{
					column =k;
				}
				k++;
					
				}
				System.out.println("column Number: "+column);
				//Once column is identified then scan the entire testcase column to identify purchase testcase row
				while(rows.hasNext())
				{
					Row r=rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						//after you grab purchase testcas e row pull all the data of that row and feed into the test
						Iterator<Cell> cv= r.cellIterator();
						while(cv.hasNext())
						{
							Cell c=cv.next();
							if(c.getCellType()==CellType.STRING)
							{
								a.add(c.getStringCellValue());
							}
							else
							{
								//a.add(cv.next().getNumericCellValue());
								
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
							
						}
					}
				}
			}
			
		}
		return a;
	}

	public static void main(String[] args) {
		
		
		

	}

}
