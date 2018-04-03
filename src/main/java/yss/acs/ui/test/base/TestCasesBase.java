package yss.acs.ui.test.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import myutils.common.DateUtils;
import myutils.common.ExcelAdapter;
import myutils.common.MailUtils;
import myutils.common.MailUtils.MailModel;
import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import yss.acs.ui.base.WebDriverFactory;
import yss.acs.ui.config.ConfigWeb;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MPageObject;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.pages.PageAcs;
import yss.acs.ui.pages.PageAcs.TimerModel;
import yss.acs.ui.utils.ActionHelper;
import yss.acs.ui.utils.CreateZip;
import yss.acs.ui.utils.JsHelper;
import yss.acs.ui.utils.LogConfiguration;
import yss.acs.ui.utils.ScreenShot;
import yss.acs.ui.utils.SeleniumUtils;

public class TestCasesBase {

	public static WebDriver driver;
	private Logger logger = Logger.getLogger(TestCasesBase.class);
	private List<String> winHandleList;
	private Map<String, PageAcs> mapPages = null;
	private Map<String, MTestcase> mapTestcases = null;
	
//	private static final String today = DateUtils.getNowString("yyyyMMdd");
//	private static final String  FilePath = "./test-output/excelReports/log";
//	private static final String saveFilePath="./test-output/excelReports" ;
//	private static final String zipFilePath="./test-output/excelReports/" + today +".zip" ;

	public TestCasesBase() {
//		killProcess();
		LogConfiguration.initLog(this.getClass().getSimpleName());
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = new WebDriverFactory().getDriver();
		driver.manage().window().maximize();
//		driver.manage().window().setPosition(new Point(0, 0));
		driver.navigate().to(ConfigWeb.host);
		List<MTestcase> testcasesLogin = generateTestcases("登录");

		try {
			testcaseExecute(testcasesLogin, logger);
		} catch (WaitTimeOutException | NoThisElementInPOException e) {
			e.printStackTrace();
		}

		Tools.sleep(2);
		Set<String> winHandels = driver.getWindowHandles(); // 得到当前窗口的set集合
		winHandleList = new ArrayList<String>(winHandels); // 将set集合存入list对象
		SeleniumUtils.switchToHandles(driver, "赢时胜金融应用");
		driver.manage().window().maximize();
//		driver.manage().window().setSize(new Dimension(1200, 850));
		Tools.sleep(1);
	}

	@AfterMethod
	public void teardown() throws Exception {
		
		if (driver != null) {
//			driver.navigate().refresh();
			driver.close();
//			driver.quit();
			driver.switchTo().window(winHandleList.get(0));
			driver.close();
			driver.quit();
		}
	}
//	@AfterSuite
//	public void afterSuite() {
//			
//		CreateZip.fileToZip();
//		
//		if (new File(zipFilePath).exists()) {
//			String date = DateUtils.getNowString("yyyy年MM月dd日");
//			MailModel model = new MailModel();
//			model.userName = "chendaiwu@ysstech.com";
//			model.from = "chendaiwu@ysstech.com";
//			model.to = "chendaiwu@ysstech.com";
//			model.passWord = "cdw520DW0614";
//			model.smtpHost = "smtp.ysstech.com";
//			model.smtpPort = "25";
//			model.messageText = "你好! \r\n 本邮件是 " + date + " 的跑账差异数据结果,请下载附件查看...";
//			model.subject = "跑账测试结果 -- " + date;
//			MailUtils.sendMessage(model, zipFilePath);
//			logger.error("邮件已发送，请注意查收！...");
//		} else {
//			logger.error("没有找到对应的测试结果文件,邮件不发送...");
//		}
//	}

	/**
	 * 测试用例执行
	 * 
	 * @param testcases
	 * @param logger
	 * @throws WaitTimeOutException
	 * @throws NoThisElementInPOException
	 */
	public void testcaseExecute(List<MTestcase> testcases, Logger logger) throws WaitTimeOutException, NoThisElementInPOException {
		
		if(mapPages==null){
			mapPages = TestCaseLibs.getPageMapFromTestcases(testcases, logger);
			mapTestcases = TestCaseLibs.getTestcasesMapFromTestcases(testcases);
		}
		
		//判断传入的用例列表所属的用例名与当前的用例的Map集合中的用例名称是否一致,不同则根据testcases对用例集合重新赋值
		MTestcase case0 = mapTestcases.get("P001");
		if(case0 !=null){
			if(!case0.getTestcaseName().equals(testcases.get(0).getTestcaseName())){
				mapPages = TestCaseLibs.getPageMapFromTestcases(testcases, logger);
//				System.out.println(mapPages);
				mapTestcases = TestCaseLibs.getTestcasesMapFromTestcases(testcases);
//				System.out.println(mapTestcases);
			}
		}
		
		

		tag: for (MTestcase testcase : testcases) {

			if (testcase.getIfExecute().equals("否"))
				continue;

			// 子过程,进入单独的处理通道
			if (testcase.getElementName().equals("sub")) {
				subProcess(testcase, mapTestcases);
				continue;
			}

			PageAcs page = null;
			Map<String, MPageObject> poMap = null;
			if (!testcase.getPageName().isEmpty()) {
				page = mapPages.get(testcase.getPageName());
				poMap = page.getPageObjects();
//				System.out.println(poMap);
			}

			String xPath = "";
//			int num = '';

			// 如果找不到用户名输入框,则直接跳过登录步骤,以应对chrome提示未正常关闭而直接跳过登陆的问题
			if (testcase.getElementName().equals("username")) {
				xPath = poMap.get(testcase.getElementName()).getValue();
				WebElement ele = page.getWebElement(driver, xPath, logger);
				if (ele == null) {
					return;
				}
			}

			List<String> noEleOperation = new ArrayList<>(Arrays.asList(new String[] { "backToDefault", "wait" }));
			if (!noEleOperation.contains(testcase.getElementName())) {
				try {
					xPath = poMap.get(testcase.getElementName()).getValue();
//					System.out.println(xPath);
				} catch (Exception ex) {
					throw new NoThisElementInPOException("页面对象文件 : " + testcase.getPageName() + "中没有此对象 : " + testcase.getElementName() + ",请检查!! ");
				}

				// 判断元素类型,如果是wildcard则需要根据testcase中的params对xpath模板中的通配符进行替换
				if (poMap.get(testcase.getElementName()).getType().equals("wildcard")) {
					String[] params = testcase.getParams().split(",");
					for (String param : params) {
						String tmpXpath = xPath;
						tmpXpath = xPath.replace("#placeholder#", param);
//						System.out.println(tmpXpath);
						execute(logger, testcase, page, tmpXpath);
					}
					continue;
				}
			}

			// 执行条件不为无则需要对条件进行判断再决定是否执行，根据判断结果决定是否跳过此条case
			String condition = testcase.getExeCondition();
			if (!condition.equals("无") && !condition.isEmpty()) {
				switch (condition) {
				case "confirmBox":
					String xPath1 = poMap.get("confirmBox").getValue();
					TimerModel timerModel = new TimerModel();
					timerModel.waitTime = 2;
					WebElement eleConfirmBox = page.getWebElement(driver, xPath1, logger, timerModel);
					if (eleConfirmBox == null || !eleConfirmBox.getAttribute("style").contains("visible")) {
						logger.info("页面没有出现该元素，操作跳过");
						continue tag;
					}
				}
			}
			execute(logger, testcase, page, xPath);
		}
	}

	/**
	 * 子过程,递归处理
	 * 
	 * @param testcase
	 * @param mapTestcases
	 * @throws WaitTimeOutException
	 * @throws NoThisElementInPOException
	 */
	private void subProcess(MTestcase testcase, Map<String, MTestcase> mapTestcases) throws WaitTimeOutException, NoThisElementInPOException {

		String[] stepNos = testcase.getParams().split(",");
		if (testcase.getStepNo().equals("P052")) {
			Tools.sleep(1);
		}
		if (stepNos.length > 0) {
			logger.info(">>>>开始执行子过程>>>> " + testcase.getStepNo());
			List<MTestcase> list = new ArrayList<MTestcase>();
			for (String stepNo : stepNos) {
				MTestcase nextTestcase = mapTestcases.get(stepNo);
				if (nextTestcase.getElementName().equals("sub")) {
					if (!testcase.getIfExecute().equals("否")) {
						subProcess(nextTestcase, mapTestcases);
					}
				} else {
					list.add(mapTestcases.get(stepNo));
				}
				if (list.size() > 0) {
					testcaseExecute(list, logger);
					list.clear();
				}
			}
			logger.info("<<<<子过程<<<< " + testcase.getStepNo() + " 执行完毕 !");
		} else {
			logger.error("子过程 " + testcase.getStepNo() + " 的参数列表为空,请检查!");
			return;
		}
	}

	protected void execute(Logger logger, MTestcase testcase, PageAcs page, String xPath) throws WaitTimeOutException, NoThisElementInPOException {

		WebElement ele = null;
		By by = null;
		List<String> noEleOperation = new ArrayList<>(Arrays.asList(new String[] {"Refush", "backToDefault", "waitToAppear", "wait", "waitToDisappear",
				"positiveCheck" }));

		// 不在列表中的操作类型可以统一获取webelement实例
		if (!noEleOperation.contains(testcase.getOperationType())) {
			ele = page.getWebElement(driver, xPath, logger);
			// Tools.logInConsole("ele对象类型 " +ele.getClass().toString());
		}

		switch (testcase.getOperationType()) {
		case "click":
			JsHelper.click(driver, ele);
			Tools.sleep(1);
			break;
		case "rawClick":
			ele.click();
			Tools.sleep(1);
			break;
		case "setValue":
			JsHelper.setValue(driver, ele, testcase.getParams());
			Tools.sleep(1);
			break;
		case "switchFrame":
			SeleniumUtils.switchFrame(driver, ele);
			Tools.sleep(1);
			break;
		case "ENTER":
			SeleniumUtils.switchTo(driver);
			Tools.sleep(1);
		case "Refush":
			SeleniumUtils.refush(driver);
			Tools.sleep(1);
		case "removeTager":
			JsHelper.removeReadOnly(driver, ele);
			Tools.sleep(1);
			break;
		case "duobleclick":
			ActionHelper.doubleClick(driver, ele);
			Tools.sleep(1);
			break;
		case "sendKeys":
			ele.clear();
			String value = testcase.getParams();
			SeleniumUtils.fingerTypeIn(ele, value);
//			ele.sendKeys(value.substring(0, value.length() - 3));
//			Tools.sleep(1.5f);
//			ele.sendKeys(value.substring(value.length() - 3, value.length()));
//			Tools.sleep(1.5f);
			break;
		case "backToDefault":
			SeleniumUtils.switchFrame(driver);
			Tools.sleep(1);
			break;
		case "waitToAppear":
			logger.info("开始等待元素" + xPath + "出现，超时时间为 : " + testcase.getParams() + "秒");
			TimerModel timerModel = new TimerModel();
			timerModel.sleepTime = 3000; // 轮询间隔时间,以减少log记录条数 单位毫秒
			timerModel.waitTime = Integer.parseInt(testcase.getParams()); // 超时时间,单位秒

			ele = page.getWebElement(driver, xPath, logger, timerModel); // 自定义等待时间
			if (ele != null) {
				logger.info("等待操作完成，元素XPath = " + xPath);
				Tools.sleep(2);
			} else {
				ScreenShot.takeScreenshot(driver, xPath);
				throw new WaitTimeOutException(">>>>>>>>>>> 等待指定元素超时 " + xPath + 
						" 截图路径  test-output/ScreenShot/"+DateUtils.getNowString("yyyy-MM-dd")+"/" + xPath + ".png");
			}
			break;
		case "waitToDisappear":
			int timeout = Integer.valueOf(testcase.getParams());
			timeout = timeout > 0 ? timeout : 5;
			for (int i = 0; i < timeout; i++) {
				try {
					ele = driver.findElement(By.xpath(xPath));
					Tools.sleep(1);
					Tools.logInConsole("等待元素消失  ： " + xPath);
				} catch (Exception e) {
					Tools.sleep(2);
					break;
				}
			}
			break;
		case "wait":
			Tools.sleep(Integer.parseInt(testcase.getParams()));
			break;
		case "getTableData":
			// 拖到最下方，否则数据展示不全
			logger.info("获取页面表格数据中，请稍候...");
			List<List<String>> datalist = TestCaseLibs.getTableData(driver, ele, logger);

			String sheetName = "sheet1";
			String excelFileName = "";
			if (testcase.getParams().split(",").length > 1) {
				String[] params = testcase.getParams().split(",");
				excelFileName = params[0];
				sheetName = params[1];
			} else {
				excelFileName = testcase.getParams();
			}
			ExcelAdapter ea = new ExcelAdapter(excelFileName, sheetName);
			ea.setSheetData((ArrayList<List<String>>) datalist);
			ea.writeExcel(excelFileName);
			logger.info("列表数据获取成功,文件保存路径 : " + excelFileName + "  sheet名 : " + sheetName);
			break;
		// 正向检查  -> 找到的所有元素的Text值 符合参数值则返回 true 否则返回false   格式 -> text:已读取;onException:P009,P010,P011
		case "positiveCheck": 
			String[] params = testcase.getParams().split(";");
			Map<String, String> mapParams = new HashMap<String, String>(2);
			mapParams.put(params[0].split(":")[0].trim(), params[0].split(":")[1].trim());
			mapParams.put(params[1].split(":")[0].trim(), params[1].split(":")[1].trim());

			List<WebElement> eleList = page.getWebElements(driver, xPath, logger);
			if (eleList != null && eleList.size() > 0) {
				for (WebElement ele1 : eleList) {
					try {
						Assert.assertEquals(ele1.getText(), mapParams.get("text"), testcase.getElementName() + "正向检查 : ");
					} catch (AssertionError e) {
						// 发生异常,执行参数中的onException步骤
						ScreenShot.takeScreenshot(driver, e);
						String[] stepNos = mapParams.get("onException").split(",");
						List<MTestcase> testcases = new ArrayList<MTestcase>();
						for (String stepNo : stepNos) {
							testcases.add(mapTestcases.get(stepNo));
						}
						if (testcases.size() > 0) {
							testcaseExecute(testcases, logger);
						}
					}
				}
			}
			break;
//		case "selectValue":
//			by = 
//			SeleniumUtils.selectValue(driver, by, Integer.parseInt(testcase.getParams()));
//			Tools.sleep(2);
//			break;
		default:
			break;
		}
		String loginfo = testcase.getStepNo() + " : " + testcase.getStepName();
		logger.info("步骤  " + loginfo + "  执行完毕！");
	}

	/**
	 * 读取用例驱动xlsx，生成测试用例列表
	 * 
	 * @param testcasesSheetName
	 * @return
	 */
	public List<MTestcase> generateTestcases(String testcasesSheetName) {
		ExcelAdapter ea = new ExcelAdapter(ConfigWeb.testcasesFilePath, testcasesSheetName);
		List<List<String>> testcases = ea.getSheetData();
//		System.out.println(testcases.size());
		testcases = testcases.subList(1, testcases.size());

		ArrayList<MTestcase> testcaseList = new ArrayList<MTestcase>();
		for (List<String> testcase : testcases) {
//			System.out.println(testcasesSheetName);
			if (testcase.size() > 0 && !testcase.get(0).isEmpty()) {
				MTestcase mTestcases = new MTestcase();
				mTestcases.setStepNo(testcase.get(0).toUpperCase()); // 步骤编号
				mTestcases.setStepName(testcase.get(1)); // 步骤说明
				mTestcases.setIfExecute(testcase.get(2)); // 是否执行
				mTestcases.setPageName(testcase.get(3));// 页面名称
				mTestcases.setTestcaseName(testcase.get(4));// 用例名称
				mTestcases.setElementName(testcase.get(5)); // 元素名
				mTestcases.setOperationType(testcase.get(6));// 操作类型
				mTestcases.setParams(testcase.get(7));// 参数
				mTestcases.setExeCondition(testcase.get(8)); // 执行条件

				testcaseList.add(mTestcases);
			}
		}
//		System.out.println(testcaseList);
		return testcaseList;
	}

	public static void killProcess() {
		Runtime rt = Runtime.getRuntime();
		//Process p = null;

		try {
			rt.exec("cmd.exe /C start wmic process where name='chromedriver229.exe' call terminate");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
