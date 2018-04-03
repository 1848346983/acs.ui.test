package yss.acs.ui.test;

import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverTest {

	public static void main(String[] args) {
//		WebDriver driver1 = new WebDriverFactory().getDriver();
//		//WebDriver driver2 = new WebDriverFactory().getDriver();
//		driver1.navigate().to(ConfigWeb.host);
//		//driver2.navigate().to("http://www.baidu.com");
//		Tools.sleep(1);
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setBrowserName("chrome");
		dc.setVersion("58");
		
	}

}
