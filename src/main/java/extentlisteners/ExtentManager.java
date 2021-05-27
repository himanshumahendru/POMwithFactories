package extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BasePage;


public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setReportName(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Automation Tester", "Himanshu Mahendru");
		extent.setSystemInfo("Build Number", "1234567");
		
		return extent;
		
		
	}
	
	public static String srcFileName;
	
	public static void captureScreenshot() throws IOException {
		
		Date d = new Date();
		srcFileName = d.toString().replace(" ", "_").replace(":","_")+".jpg";
		
		File screenshot = ((TakesScreenshot) BasePage.driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot, new File(".\\target\\reports\\"+srcFileName));
		FileUtils.copyFile(screenshot, new File(".\\target\\surefire-reports\\html\\"+ srcFileName));
		
	}


	

}
