package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;



public class BasePage {
	
	public static WebDriver driver;
	public static CarBase1 car;
	
	
	public BasePage(WebDriver driver) {
		
	BasePage.driver =driver;
	car = new CarBase1(driver);
	PageFactory.initElements(driver, this);
	}

	
	public void click(WebElement element) {
		element.click();
		ExtentListeners.test.log(Status.INFO, "Clicked on the element - " + element);
		}
	}

