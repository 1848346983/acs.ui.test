package yss.acs.ui.test;

import java.util.Set;

import myutils.common.Tools;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import yss.acs.ui.base.WebDriverFactory;
import yss.acs.ui.utils.JsHelper;

public class CookiesTest {

	public static void main(String[] args) {

		WebDriver driver = new WebDriverFactory().getDriver();

		try {
			driver.navigate().to("https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fwww.jd.com%2F");
			Tools.sleep(1);
			driver.manage().window().maximize();
			WebElement tab = driver.findElement(By.xpath(".//div[@class='login-tab login-tab-r']"));
			JsHelper.click(driver, tab);
			Tools.sleep(2);
			WebElement username = driver.findElement(By.xpath(".//input[@id='loginname']"));
			WebElement pwd = driver.findElement(By.xpath(".//input[@id='nloginpwd']"));
			JsHelper.setValue(driver, username, "fisker123");
			JsHelper.setValue(driver, pwd, "yxd129000.");
			WebElement loginBtn = driver.findElement(By.xpath(".//a[@id='loginsubmit']"));
			JsHelper.click(driver, loginBtn);

			Set<Cookie> cookies = driver.manage().getCookies();
			for (Cookie c : cookies) {
				Tools.logInConsole(c.getName() + " -- " + c.getValue());
			}

			Tools.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}

	}
}
