package yss.acs.ui.utils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import mx4j.log.Log;
import myutils.common.LogUtils;
import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty7.security.authentication.LoginAuthenticator;

import yss.acs.ui.config.ConfigWeb;
import yss.acs.ui.test.testcases.PaoZhangTestcases;

/**
 * 
 * @author YXD
 *
 */
public class SeleniumUtils {
	
	private static final Logger logger = Logger.getLogger(SeleniumUtils.class);

	/**
	 * 切换窗口
	 * 
	 * @param driver
	 * @param windowTitle
	 * @return
	 */
	public static boolean switchToWindow(WebDriver driver, String windowTitle) {
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String s : handles) {
				if (s.equals(currentHandle))
					continue;
				else {
					driver.switchTo().window(s);
					if (driver.getTitle().contains(windowTitle)) {
						flag = true;
						logger.info("Switch to window: " + windowTitle + " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window: " + windowTitle + " cound not found! \n" + e.fillInStackTrace());
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 切换窗口
	 * 
	 * @param driver
	 * @param HandleTitle
	 * @return
	 */
	
	public static boolean switchToHandles(WebDriver driver, String HandleTitle) throws InterruptedException {
		boolean flag = false;
		try {
//			String currentHandle = driver.getWindowHandle();		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Set<String> handles = driver.getWindowHandles();
//			System.out.println(handles);
			for (String s : handles) {
//				System.out.println(s);
				if (s.equals(s) == true)
//					continue;
				/*else*/ {
					driver.switchTo().window(s);
					if (driver.getTitle().contains(HandleTitle)) {
						flag = true;
						logger.info("Switch to Handle: " + HandleTitle + " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Handle: " + HandleTitle + " cound not found! \n" + e.fillInStackTrace());
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断元素是否存在
	 * 
	 * @param driver
	 * @param locator
	 *            eg. By b = By.id("id1")
	 * @param timeOut
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean isElementExsit(WebDriver driver, By locator, int timeOut) throws InterruptedException {
		boolean flag = false;
		Thread.sleep(1000 * timeOut);
		try {
			WebElement element = driver.findElement(locator);
			flag = null != element;
		} catch (NoSuchElementException e) {
			System.out.println("Element:" + locator.toString() + " is not exsit!");
		}
		return flag;
	}

	/**
	 * 判断页面上是否出现指定内容
	 * 
	 * @param driver
	 * @param content
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean isContentAppeared(WebDriver driver, String content) {
		boolean status = false;
		try {
			driver.findElement(By.xpath("//*[contains(.,'" + content + "')]"));
			System.out.println(content + " is appeard!");
			status = true;
		} catch (NoSuchElementException e) {
			status = false;
			System.out.println("'" + content + "' doesn't exist!");
		} finally {
			return status;
		}
	}

	/**
	 * 切换frame
	 * 
	 * @param driver
	 * @param ele
	 *            可选参数，带ele则切换到ele中，不带则切换到默认frame
	 */
	public static void switchFrame(WebDriver driver, WebElement... ele) {
		if (ele.length > 0) {
			driver.switchTo().frame(ele[0]);
		} else {
			driver.switchTo().defaultContent();
		}
	}
	
	
	public static void refush(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	/**
	 * 切换frame
	 * 
	 * @param driver
	 * @param ele
	 *            可选参数，带ele则切换到ele中，不带则切换到默认frame
	 */
	public static void switchTo(WebDriver driver) {
		try{
			driver.switchTo().alert().accept();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 等待元素展示，超时时间为默认值
	 * 
	 * @param driver
	 * @param element
	 * @return
	 */
	public static boolean waitElementToBeDisplayed(WebDriver driver, final WebElement element) {
		if (element == null)
			return false;
		boolean ret = false;
		try {
			ret = new WebDriverWait(driver, ConfigWeb.waitTime).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver d) {
					return element.isDisplayed();
				}
			});
		} catch (Exception e) {
			System.out.println(element.toString() + "is not displayed");
		}
		return ret;
	}

	/**
	 * 等待URL变为指定的地址，
	 * 
	 * @param driver
	 * @param str
	 * @return
	 */
	public static boolean waitUrl(final WebDriver driver, final String urlPattern, Logger logger) {

		boolean ret = false;
		try {
			ret = new WebDriverWait(driver, ConfigWeb.waitTime).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver d) {
					// _logger.info("URL : " + driver.getCurrentUrl());
					return driver.getCurrentUrl().contains(urlPattern);
				}
			});
		} catch (Exception e) {
			logger.error("没有找到包含  " + urlPattern + " 的URL");
		}
		return ret;
	}

	/**
	 * 从select下拉列表中选择一项
	 * 
	 * @param driver
	 * @param by
	 *            select元素的by
	 * @param no
	 *            选择select列表中的第几个option，从0开始
	 */
	public static void selectValue(WebDriver driver, By by, int no) {
		WebElement selectElement = driver.findElement(by);
		if (selectElement != null) {
			List<WebElement> optionList = selectElement.findElements(By.tagName("option"));
			if (optionList.size() > no) {
				optionList.get(no).click();
			}
		}
	}

	public static void clickNative(WebElement ele) {
		ele.click();
	}

	public static void clickByJavaScript(WebDriver driver, WebElement ele) {
		JsHelper.click(driver, ele);
	}

	public static void typeInNative(WebElement ele, String content) {
		ele.sendKeys(content);
	}

	public static void typeInByJavaScript(WebDriver driver, WebElement ele, String content) {
		JsHelper.setValue(driver, ele, content);
	}

	public static String getTextNative(WebElement ele) {
		return ele.getText();
	}

	/**
	 * 不能点击时候重试点击操作
	 * 
	 * @param ele
	 *            元素实例
	 * @param startTime
	 *            开始时间 System.currentTimeMillis()
	 * @param timeOut
	 *            超时时长 毫秒
	 * @param logger
	 *            日志实例
	 */
	public static void clickTheClickable(WebElement ele, long startTime, int timeOut, Logger logger) {
		try {
			ele.click();
		} catch (Exception e) {
			if (System.currentTimeMillis() - startTime > timeOut) {
				logger.warn(ele.getText() + " is unclickable");
			} else {
				Tools.sleep(0.5f);
				logger.warn(ele.getText() + " is unclickable, try again");
				clickTheClickable(ele, startTime, timeOut, logger);
			}
		}
	}

	/**
	 * 模拟键盘组合键操作,比如Ctrl+A,Ctrl+C等
	 * 
	 * @param element
	 *            要被操作的元素
	 * @param key
	 *            键盘上的功能键 比如ctrl ,alt等
	 * @param keyword
	 *            键盘上的字母
	 */
	public static void pressKeysOnKeyboard(WebElement element, Keys key, String keyword) {
		element.sendKeys(Keys.chord(key, keyword));
	}

	/**
	 * 模拟手动输入
	 * @param e
	 * @param str
	 * @throws InterruptedException
	 */
	public static void fingerTypeIn(WebElement e, String str){
		String[] singleCharacters = str.split("");
		for (int i = 0; i < singleCharacters.length; i++) {
			if (singleCharacters[i] != "") {
				e.sendKeys(singleCharacters[i]);
				Tools.sleep(0.4f);
			}
		}
		Tools.sleep(2);
	}
}
