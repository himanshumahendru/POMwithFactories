package testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;
import utilities.DataUtil;

public class CarPriceTest extends BaseTest{


	@Test(dataProviderClass = DataUtil.class, dataProvider="dp")
	public void findCarTest(String brandName,String browserName,String runmode) {
		
		if(runmode.equals("N")) {
			
			throw new SkipException("Skipping the test as the run mode is NO");
		}
		
		
		setup(browserName);
		if(brandName.equals("Hyundai")) {
			
			HomePage home = new HomePage(driver);
			home.findNewCars().selectHyundaiCar();
			System.out.println(BasePage.car.getPageTitle());
			BasePage.car.CarNamesAndPrices();
			System.out.println(BasePage.car.getCarNamesAndPrices());
			BasePage.car.updatecarDetailsInFile("Hyundai");
		
		}else if(brandName.equals("Maruti")) {
			
			HomePage home = new HomePage(driver);
			home.findNewCars().selectMarutiCar();
			System.out.println(BasePage.car.getPageTitle());
			BasePage.car.CarNamesAndPrices();
			System.out.println(BasePage.car.getCarNamesAndPrices());
			BasePage.car.updatecarDetailsInFile("Maruti");
	
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

		
		
	}
	

