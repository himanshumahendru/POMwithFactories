package extentlisteners;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener {

	static Date d = new Date();
	static String filename = "Extent_"+d.toString().replace(" ", "_").replace(":", "")+".html";
	public static ExtentReports extent = ExtentManager.createInstance(".\\target\\reports\\"+filename);
	
	public static ExtentTest test;
	
	
	
	public void onTestStart(ITestResult result) {
		String param = (String) result.getParameters()[0];
		test = extent.createTest("Test Class-  " + result.getTestClass().getName() + "   Test Case - " + result.getMethod().getMethodName() + "  Params - "+ param);
		}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>TestCase Passed - "+ methodName +"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
		
		
	}

	public void onTestFailure(ITestResult result) {
		try {
			ExtentManager.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///////ReportNG
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<a href=" + ExtentManager.srcFileName + " target=\"_blank\">Screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a href=" + ExtentManager.srcFileName + " target=\"_blank\"><img src=" + ExtentManager.srcFileName + " height=200 width=200></a>");
        ///////
		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>TestCase Failed - "+ methodName +"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.fail(m);
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(exceptionMessage);
		
		String SS = ExtentManager.srcFileName;
		try {
			test.fail("<b><font color = red> SS of the failed testcase </font></b> <br>", MediaEntityBuilder.createScreenCaptureFromPath(SS).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>TestCase Skipped - "+ methodName +"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		if(extent!= null)
			extent.flush();
		
	}

}
