package yss.acs.ui.test.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import yss.acs.ui.config.ConfigWeb;
import yss.acs.ui.config.MappingPageNameToClassName;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.pages.PageAcs;
import yss.acs.ui.utils.JsHelper;

public class TestCaseLibs {
	
	/**
	 * 生成testcases中用到的页面对象集合
	 * @param testcases
	 * @return
	 */
	public static Map<String, PageAcs> getPageMapFromTestcases(List<MTestcase> testcases,Logger logger) {

		Set<String> pageNameSet = new HashSet<String>();
		for (MTestcase testcase : testcases) {
			String pageName = testcase.getPageName();
//			System.out.println(pageName);
			if(!pageName.isEmpty()){
				pageNameSet.add(testcase.getPageName());
			}
		}
		Map<String, PageAcs> mapPage = new HashMap<String, PageAcs>(pageNameSet.size());
		for (String s : pageNameSet) {
			try {
				mapPage.put(s, getPageInstanceByPageName(s,logger));
//				System.out.println(s);
			} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return mapPage;

	}
	
	
	
	public static Map<String, MTestcase> getTestcasesMapFromTestcases(List<MTestcase> testcases) {
		
		Map<String, MTestcase> map = new LinkedHashMap<>(testcases.size());
		for(MTestcase ca : testcases){
			map.put(ca.getStepNo(), ca);
		}
		
		return map;
	}
	
	public static List<List<String>> getTableData(WebDriver driver ,WebElement tableDiv,Logger logger) {

		// 滚动条向下滚动到底，否则数据展示不全
		WebElement ele = driver.findElements(By.xpath(".//div[@class='x-grid3-scroller']")).get(1);

		// 不能一次滚动幅度太大,否则中间的数据加载不出来,先获取滚动条高度,再决定要滚动几次
		String eleStyle = ele.getAttribute("style");
		Pattern pattern = Pattern.compile("height: (\\d+)px");
		Matcher matcher = pattern.matcher(eleStyle);
		int height = 0;
		if (matcher.find()) {
			height = Integer.parseInt(matcher.group(1));
			Tools.logInConsole("滚动条高度  " + height);
		}
		
		for (int i = 1; i <= 5; i++) {
			JsHelper.scrollTop(driver, ele, String.valueOf(400*i));
			Tools.sleep(1);
		}



		List<WebElement> tableList = tableDiv.findElements(By.xpath(".//table"));
		logger.info("记录总条数 : " + tableList.size());

		// 取列头列表
		WebElement tableHeader = tableList.get(0);
		List<WebElement> headerTdList = tableHeader.findElements(By.xpath(".//td/div"));
		List<String> headerList = new ArrayList<String>();
		for (WebElement div : headerTdList) {
			headerList.add(div.getAttribute("title"));
		}

		List<List<String>> wholeDataList = new ArrayList<List<String>>();
		wholeDataList.add(headerList);

		for (int i = 1; i < tableList.size(); i++) {
			List<WebElement> dataTdList = tableList.get(i).findElements(By.xpath(".//td/div"));
			List<String> dataList = new ArrayList<String>();

			for (WebElement div : dataTdList) {
				dataList.add(div.getText());
			}
			wholeDataList.add(dataList);
		}

		return wholeDataList;
	}

	/**
	 * 通过页面名称生成页面类的实例
	 * @param pageName
	 * @return
	 */
	private static PageAcs getPageInstanceByPageName(String pageName,Logger logger) throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
			IllegalAccessException {
		//logger.info("当前实例化页面对象为  ：  " + pageName);
		Class<?> class1 = Class.forName(getClassNameFromPageName(pageName));
		Constructor<?> c = class1.getDeclaredConstructor(new Class[] { String.class, String.class });
		PageAcs page = null;
		try {
			page = (PageAcs) c.newInstance(new Object[] { ConfigWeb.pageObjectFilePath, pageName });
		} catch (IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return page;
	}

	private static String getClassNameFromPageName(String pageName) {
		return MappingPageNameToClassName.getClassNameFromPageName(pageName);
	}

}
