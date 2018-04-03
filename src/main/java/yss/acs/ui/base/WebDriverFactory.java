package yss.acs.ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import yss.acs.ui.config.ConfigWeb;
import yss.acs.ui.utils.MyEventListener;



public class WebDriverFactory extends DriverFactory {

	@Override
	public WebDriver getDriver() {
		WebDriver driver = null;
		// TODO 自动生成的方法存根
		switch (ConfigWeb.browser) {
		case "firefox":
			driver = getFirefoxDriver();
			break;
		case "chrome":
			driver = getChromeDriver();
			break;
		case "ie":
			driver = getIEDriver();
			break;
		case "htmlunit":
			driver = getHtmlUnit();
		}

		return driver;
	}

	private static WebDriver getHtmlUnit() {
		HtmlUnitDriver ht = new HtmlUnitDriver();
		return ht;
	}

	private static WebDriver getChromeDriver() {
		
//		 try{
//		 WindowsUtils.tryToKillByName("chromedriver.exe");
//		 }
//		 catch(WindowsRegistryException e){
//		 System.out.println("没有残留的ChromeDriver进程，无需终止！");
//		 }

		// TODO Auto-generated method stub
		//String chromdriver = "E:\\chromedriver.exe";
		String chromdriver = "res/chromedriver229.exe";
		System.setProperty("webdriver.chrome.driver", chromdriver);
//		ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
//		File file = new File(chromdriver);
//		// int port=12643;
//		// ChromeDriverService
//		// service=builder.usingDriverExecutable(file).usingPort(port).build();
//		// try {
//		// service.start();
//		// } catch (IOException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
		
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("user-data-dir=C:\\Users\\lenovo\\AppData\\Local\\Google\\Chrome\\User Data");
		
//		options.addExtensions(new File(""));
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
//		options.addArguments("--test-type", "--start-maximized");
		WebDriver driver = new ChromeDriver();
		
//		WebDriver driver = new ChromeDriver();
		WebDriverEventListener we = new MyEventListener();
		//注册监听器
		driver = new EventFiringWebDriver(driver).register(we);
		return driver;
	}

	private static WebDriver getFirefoxDriver() {
		// try
		// {
		// WindowsUtils.tryToKillByName("firefox.exe");
		// }
		// catch(Exception e)
		// {
		// System.out.println("there is no firefox process, no need to kill");
		// }

//		File file = new File("C:\\MyDownloads\\firebug-2.0.13-fx.xpi");
//		FirefoxProfile profile = new FirefoxProfile();
		// profile.setPreference("network.proxy.type", 2);
		// profile.setPreference("network.proxy.autoconfig_url",
		// "http://proxy.successfactors.com:8083");
		// profile.setPreference("network.proxy.no_proxies_on", "localhost");
		//

		// profile.setPreference("network.proxy.http",
		// "proxy.domain.example.com");
		// profile.setPreference("network.proxy.http_port", 8080);
		// profile.setPreference("network.proxy.ssl",
		// "proxy.domain.example.com");
		// profile.setPreference("network.proxy.ssl_port", 8080);
		// profile.setPreference("network.proxy.ftp",
		// "proxy.domain.example.com");
		// profile.setPreference("network.proxy.ftp_port", 8080);
		// profile.setPreference("network.proxy.socks",
		// "proxy.domain.example.com");
		// profile.setPreference("network.proxy.socks_port", 8080);
		
//		try {
//			profile.addExtension(file);
//			profile.setPreference("extensions.firebug.currentVersion", "2.0.13");
//			profile.setPreference("extensions.firebug.allPagesActivation", "on");
//		} catch (IOException e3) {
//			e3.printStackTrace();
//		}

		System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"); 
		
//		DesiredCapabilities capability = null;
//		capability = DesiredCapabilities.firefox();
//		WebDriverEventListener eventListener = new MyEventListener();
//		WebDriver driver = null;
//		try {
//			driver = new EventFiringWebDriver(new RemoteWebDriver(new URL("http://www.hnmall.com/"), capability))
//					.register(eventListener);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}

		// WebDriver driver = new FirefoxDriver(profile);
		 WebDriver driver = new FirefoxDriver();
		return driver;
	}

	private static WebDriver getIEDriver() {
		String IEDriverServer = "D:\\IEDriverServer.exe";
		System.setProperty("IEDriverServer.ie.driver", IEDriverServer);
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}
}
