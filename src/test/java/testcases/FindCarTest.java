package testcases;


import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import utilities.DataUtil;

public class FindCarTest extends BaseTest{
	
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void findCarTest(String brandName , String browserName , String runmode) {
		if(runmode.equals("N"))
			throw new SkipException("Skipped the test as the run mode was selected as N");
		
		setup(browserName);
		
		if(brandName.equals("Hyundai")) {
			HomePage home = new HomePage(driver);
			home.findNewCars().selectHyundaiCar();
			Assert.fail("failed intentionally");
			
		} else if(brandName.equals("Maruti")) {
			HomePage home = new HomePage(driver);
			home.findNewCars().selectMarutiCar();
			Assert.fail("failed intentionally");
			
		} else if(brandName.equals("Kia")) {
			HomePage home = new HomePage(driver);
			home.findNewCars().selectKiaCar();
			Assert.fail("failed intentionally");
			
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
