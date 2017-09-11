package com.WhatFix.test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.WhatFix.common.WhatFixUtil;
import com.WhatFix.pages.WhatFixverify;
import com.WhatFix.util.BaseTestObject;
import com.WhatFix.util.ExcelutilObject;

public class WhatFixverifyTest extends BaseTestObject {

	WhatFixverify whtObj = null;
	boolean flag = false;
	boolean land_flag=false;
	boolean error_flag=false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="BasicTests")
	//Verification of Valid user
	public void verifyLoginValidUser() throws Exception {
		try {
			whtObj = new WhatFixverify(driver);
			flag = whtObj.isloginPageDisplayed();
			WhatFixUtil.explicitWait(3000);
			String user =getValFromExcel(1,2);
			String pwd =getValFromExcel(1,3);
			whtObj.LoginAsUser(user, pwd);
			land_flag = whtObj.islandingPageDisplayed();
			Assert.assertTrue(land_flag);
		}

		catch (Exception e) {
			throw new Exception("Failed to Login"+ "\n " + e.getLocalizedMessage());
		}
	}
	@Test(priority = 1, enabled = true,groups="BasicTests")
	//Verification of InValid user
		public void verifyLoginInvalidUser() throws Exception {
			try {
				whtObj = new WhatFixverify(driver);
				flag = whtObj.isloginPageDisplayed();
				WhatFixUtil.explicitWait(3000);
				String user =getValFromExcel(2,2);
				String pwd =getValFromExcel(2,3);
				whtObj.LoginAsUser(user, pwd);
				error_flag = whtObj.isErrorPageDisplayed();
				Assert.assertTrue(error_flag);
			}

			catch (Exception e) {
				throw new Exception("Failed to Login"+ "\n " + e.getLocalizedMessage());
			}
		}
		
	public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "LoginTestData");
	return ExcelutilObject.getCellData(row, col);
}

}