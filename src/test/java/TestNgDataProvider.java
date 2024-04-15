import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNgDataProvider {
	
	//multiple sets of data array
	//5 sets of data as 5 arrays from data provider to your set then it wil run 5 times wit 5 separates of data
	
	@Test(dataProvider="driveset")
	public void testcasedata(String greeting,String communication,int id)
	{
		System.out.println(greeting+communication+id);
		
	}
	@DataProvider(name="driveset")
	public Object[][] getData()
	{
		//why return type as object is selected here?because object is super of all data type (it can be hold integer array/string array ,.....)
		Object[][] data={{"hello","test",1},{"bye","message",143},{"solo","call",453}};
		return data;
	}

}
