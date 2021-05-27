package base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarBase1 {
	
	WebDriver driver;
	
	public CarBase1(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div[2]/div/h1")
	public WebElement pageTitle;
	
	public String getPageTitle() {
		return pageTitle.getText();
		
	}

	@FindBy(xpath="//ul/li/div/div[1]/div/div[1]/span[1]")
	public List<WebElement> carPrices;
	
	
	@FindBy(xpath="//div[1]/ul/li/div/div[1]/div/a/h2")
	public List<WebElement> carNames;


	public ArrayList<String> list = new ArrayList<String>();
	
	public void CarNamesAndPrices() {
		
		for(int i = 0;i<carNames.size();i++) {
		String text = carNames.get(i).getText() + " and the price is - " + carPrices.get(i).getText();
		list.add(text);
		}
		
		for(String li :list)
			System.out.println(li);
	}
	
   public List<String> getCarNamesAndPrices() {
		
		for(int i = 0;i<carNames.size();i++) {
		String text = carNames.get(i).getText() + " and the price is - " + carPrices.get(i).getText();
		list.add(text);
		}
		
		for(String li :list)
			System.out.println(li);
	
		return list;
   }
   
   
   public void updatecarDetailsInFile(String fileName) {
	   
	   String info = fileName + " Car details are listed below   -";
	   File file = new File(fileName);
	   try {
		   FileWriter fw = new FileWriter(file, true);
		   fw.write(info);
		   String lineSeparator = System.getProperty("line.separator");
		   
		   for(int i =0 ;i<list.size();i++) {
			   fw.write(list.get(i));
			   fw.write(lineSeparator);
			   }
		   fw.flush();
		   fw.close();
		   FileUtils.copyFile(file, new File(".\\carPrices\\"+fileName+".txt"));
		   FileUtils.deleteQuietly(file);
	   }catch(IOException e) {
		   System.out.println(e.getMessage());
	   }
	   
   }
	
   
	
}



