package com.test.SamplePractice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class DataproviderWithExcelData {
	
	WebDriver driver;
    String driverPath = "D:\\chromedriver.exe";

	@BeforeTest
    public void setup(){
        
        System.setProperty("webdriver.chrome.driver",driverPath) ;
		driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.get("https://google.com");
    }
	
	
    @Test(dataProvider="SearchProvider")
	public void testMethod(String author,String searchKey) throws InterruptedException{
	    {
	        WebElement searchText = driver.findElement(By.name("q"));
	        //search value in google searchbox
	        searchText.sendKeys(searchKey);
	        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
	        Thread.sleep(3000);
	        String testValue = searchText.getAttribute("value");
	        System.out.println(testValue +"::::"+searchKey);
	        searchText.clear();
	        //Verify if the value in google search box is correct
	        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	    }
	    }
    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider() throws BiffException{
    /*return new Object[][] 
    	{
            { "Akshaya", "India" },
            { "Satya", "UK" },
            { "Bhupesh", "USA" }
        };*/
    	Object[][] arrayObject = getExcelData("D:/sampledoc.xls","Sheet1");
		return arrayObject;

    }
    public String[][] getExcelData(String fileName, String sheetName) throws BiffException {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);
			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		
	}
		return arrayExcelData;
    }
    
    
   
}