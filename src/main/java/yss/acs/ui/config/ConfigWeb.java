package yss.acs.ui.config;

import yss.acs.ui.utils.ParseXml;



public class ConfigWeb {

	public static String browser;
	public static int waitTime;
	public static String host;
	public static String pageObjectFilePath;
	public static String testcasesFilePath;
	public static String ftpPath;
	//public static List<Element> productIdList;
	
	static {
		ParseXml px = new ParseXml("config/config.xml");
		browser = px.getElementText("/config/browser");
		waitTime = Integer.valueOf(px.getElementText("/config/waitTime"));
		host = px.getElementText("/config/host");
		pageObjectFilePath = px.getElementText("/config/pageObjectFilePath");
		testcasesFilePath = px.getElementText("/config/testcasesFilePath");
		ftpPath = px.getElementText("/config/ftpPath");
		//productIdList = px.getElementObjects("//config/productIds/productId");
	}

}
