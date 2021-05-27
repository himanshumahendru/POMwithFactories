package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HomePage  extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div/nav/ul/li[1]/div")
	public WebElement newCars;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div/nav/ul/li[1]/div")
	public WebElement findNewCars;
	
	
	public NewCarsPage findNewCars() {
		
		new Actions(driver).moveToElement(newCars).perform();
		click(findNewCars);
		
		return  new NewCarsPage(driver);
		
	}
	
	
}
