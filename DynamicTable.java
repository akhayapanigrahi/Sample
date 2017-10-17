package com.test.SamplePractice;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DynamicTable {
	
	@Test
	public void getRowsAndColumns() {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe") ;
		driver = new ChromeDriver();
		driver.get("http://money.rediff.com/gainers/bsc/dailygroupa?");
		
		List<WebElement> columns= driver.findElements(By.xpath("//table/thead/tr/th"));
		//for individual Row xpath is //table/thead/tr/th[contains(text(),'Company')]
		List<WebElement> rows=driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
		System.out.println(columns.size());
		System.out.println(rows.size());
		
		//finding the 3rd row of the table and its second cell's data
		
		WebElement ele1=driver.findElement(By.xpath("//a[contains(text(),'Bombay Dyeing')]"));
		for(int i=0;i<rows.size();i++){
		if(rows.get(i).getText().equals(ele1.getText())){
			System.out.println("Element found");
		WebElement Prev_Close=	driver.findElement(By.xpath("//a[contains(text(),'BEML Ltd.')]/../following-sibling::td[2]"));
		//Prev_Close.getText().replace(",", "");
		/*List<String> newList = new ArrayList<String>();
		newList.add(Prev_Close.getText().replace(",", ""));
		System.out.println(newList);*/
		//Get all rows data and find out maximum data	
		String actual_rowname="Prev Close (Rs)";
		WebElement closeRow=driver.findElement(By.xpath("//table/thead/tr/th[contains(text(),'Prev Close (Rs)')]"));
		if(closeRow.getText().equals(actual_rowname)){
			List<WebElement> close_values=driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[3]"));
			List<String> newList = new ArrayList<String>();
			
			for(WebElement ele : close_values) {
				if(ele.getText().contains(","))
				newList.add(ele.getText().replaceAll(",", ""));
				
			}
			ArrayList<Double> numbers = new ArrayList<Double>();
			for(int k = 0; k < newList.size(); k++) {
			   numbers.add(Double.parseDouble(newList.get(k)));   
			}
			Double maxno=Collections.max(numbers);
			System.out.println("Maximum no is "+maxno);
			DecimalFormat df = new DecimalFormat("###,###.####", new DecimalFormatSymbols(Locale.US));
			String resultStr = df.format(Double.valueOf(maxno));
			//driver.findElement(By.xpath("//td[contains(text(),'290.20')]/preceding-sibling::td[2]"));
			WebElement maxElement=driver.findElement(By.xpath("//td[contains(text(),'" +resultStr+ "')]/preceding-sibling::td[2]"));
			System.out.println(maxElement);
			System.out.println(maxElement.getText());

		}
		
		}
		
		}
		
		
				
	}

	}

