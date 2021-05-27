package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class NewCarsPage extends BasePage{

	public NewCarsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

	@FindBy(linkText = "Toyota")
	public WebElement toyotaCar;
	
	/////////
	
	@FindBy(linkText = "Maruti Suzuki")
	public WebElement marutiCar;
	
	public MarutiPage selectMarutiCar() {
		click(marutiCar);
		return new MarutiPage(driver);
		
	}
	
	/////////
	@FindBy(linkText = "Hyundai")
	public WebElement hyundaiCar;
	
	public HyundaiPage selectHyundaiCar() {
		click(hyundaiCar);
		return new HyundaiPage(driver);
		}
	
	
	/////////
	@FindBy(linkText = "Kia")
	public WebElement kiaCar;
	
	public KiaPage selectKiaCar() {
		click(kiaCar);
		return new KiaPage(driver);
	}
	
	
}
