package yss.acs.ui.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import yss.acs.ui.model.MTestParams;
import yss.acs.ui.test.datasource.DataSourceFileXml.ProductModel;


public class TestNGListener extends TestListenerAdapter {
	
	private Logger logger = Logger.getLogger(TestNGListener.class);
	private WebDriver driver;
	

	
	@Override
	public void onTestFailure(ITestResult tr) {
		
		super.onTestFailure(tr);
		driver = (WebDriver)tr.getTestContext().getAttribute("driver");
		try {
			logger.info(tr.getName() + "failure");
		} catch (Exception e) {
			e.printStackTrace();
		}
		MTestParams parameter = (MTestParams)tr.getParameters()[1];
		if(parameter instanceof ProductModel){
			String id = ((ProductModel)parameter).productID;
			if(!((ProductModel)parameter).productID.isEmpty()){
				ScreenShot.takeScreenshot(driver,id);
				logger.info(tr.getName() + " " + id + " 测试失败! -- ");
			}
		
		}	 
	}
	
	@Override
	public void onTestSkipped(ITestResult tr) {
		//ScreenShot.takeScreenshot(testCaseBase.driver);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO 自动生成的方法存根
		super.onTestSuccess(tr);
		String parameter = ((ProductModel)tr.getParameters()[1]).productID;
		if(!parameter.isEmpty()){
			logger.info(tr.getTestClass().getName() + " " + parameter + " 已完成!");
		}
	}

	@Override
	public void onTestStart(ITestResult tr) {
		String parameter = ((ProductModel)tr.getParameters()[1]).productID;
		if(!parameter.isEmpty()){
			logger.info(tr.getTestClass().getName()+ " " + tr.getName() + " "+ parameter+ " 测试开始!");
		}
		
		
	}
}
