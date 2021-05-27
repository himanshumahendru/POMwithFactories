package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;

public class BaseTest {
	
	public WebDriver driver;
	public Logger log = Logger.getLogger(BaseTest.class.getName());
	public Properties config = new Properties();
	public ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public FileInputStream fis;
	public WebDriverWait wait;
	public static WebElement dropdown;
	
	
	public void setup(String browserName) {
		
		PropertyConfigurator.configure(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\log4j.properties");
		
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(browserName.equals("chrome")) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			
		} else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			}
		
		driver.get(config.getProperty("testsiteurl"));
		log.info("Navigated to TestSite URL - "+ config.getProperty("testsiteurl"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
		wait= new WebDriverWait(driver, Integer.parseInt(config.getProperty("implicit.wait")));
		}
	
    @AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("test case execution completed!!");
	}
	
}
