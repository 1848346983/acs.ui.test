package yss.acs.ui.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myutils.common.ExcelAdapter;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import yss.acs.ui.base.Page;
import yss.acs.ui.config.ConfigWeb;
import yss.acs.ui.model.MPageObject;

public abstract class PageAcs extends Page {
	
	private ExcelAdapter ea;
	
	public PageAcs(String pageObjectFilePath,String sheetName){
		ea = new ExcelAdapter(pageObjectFilePath, sheetName);
	}
	
	public Map<String, MPageObject> getPageObjects(){	
		ArrayList<List<String>> datalist =  ea.getSheetData();
		Map<String, MPageObject> map = new HashMap<String, MPageObject>();
		for(List<String> rowList : datalist){
			MPageObject mPageObject = new MPageObject();
			mPageObject.setNo(rowList.get(0));
			mPageObject.setName(rowList.get(1));
			mPageObject.setType(rowList.get(2));
			mPageObject.setValue(rowList.get(3));
			mPageObject.setRemark(rowList.get(4));
			map.put(rowList.get(1), mPageObject);
//			System.out.println(map);
		}
		return map;
	} 
	
//	public Map<String, String>  getNameValuePairs(String nameColName,String valueColName) {
//		return ea.getKeyValueMap(nameColName, valueColName);
//	}
	
	public WebElement getWebElement(WebDriver driver,String xPath,Logger logger ,TimerModel... timerModel){
		return waitForElement(driver, By.xpath(xPath),logger,timerModel);
	}
	
	public List<WebElement> getWebElements(WebDriver driver,String xPath,Logger logger){
		return waitForElements(driver, By.xpath(xPath), logger);	
	}
	
	private WebElement waitForElement(WebDriver driver, final By by,Logger logger,TimerModel... timerModel) {
		WebElement element = null;
		
		int waitTime = ConfigWeb.waitTime;
		int sleepTime = 1000;
		
		if(timerModel.length>0){
			waitTime = timerModel[0].waitTime;
			sleepTime = timerModel[0].sleepTime;
		}
		
		try {
			element = new WebDriverWait(driver, waitTime,sleepTime).until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver d) {
					return d.findElement(by);
				}
			});
		} catch (Exception e) {
			logger.info("寻找页面元素 : " + by.toString() + " 超时!");
		}
		return element;
	}
	
	private List<WebElement> waitForElements(WebDriver driver, final By by,Logger logger) {
		List<WebElement> element = null;
		int waitTime = ConfigWeb.waitTime;

		try {
			element = new WebDriverWait(driver, waitTime).until(new ExpectedCondition<List<WebElement>>() {

				@Override
				public List<WebElement> apply(WebDriver d) {
					return d.findElements(by);
				}
			});
		} catch (Exception e) {
			logger.info("寻找页面元素 : " + by.toString() + " 超时!");
		}
		return element;
	}
	
	//内部类,用于设置查找超时时间和轮询间隔时间
	public static class TimerModel{
		//超时时间
		public Integer waitTime=10;
		//间隔时间
		public Integer sleepTime=1000;
	}

}
