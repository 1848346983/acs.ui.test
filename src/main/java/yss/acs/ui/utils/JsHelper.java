package yss.acs.ui.utils;

import java.awt.Point;

import myutils.common.Tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsHelper {
	
	
	/**
	 * 刷新页面（未测试）
	 * @param driver
	 */
	public static void refreshPage(WebDriver driver){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("history.go(0)");
	}
	
	/**
	 * 返回document的innertext （未测试）
	 * @param driver
	 * @return
	 */
	public static String getDocumentInnerText(WebDriver driver){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String html = executor.executeScript("return document.documentElement.innerText;").toString();
		return html;
	}
	
	/**
	 * 获取window.title （未测试）
	 * @param driver
	 * @return
	 */
	public static String getWindowTitle(WebDriver driver){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String html = executor.executeScript("return document.title;").toString();
		return html;
	}
	
	/**
	 * 页面上/下滚动Y个像素
	 * @param driver
	 * @param Y
	 */
	public static void scrollDown(WebDriver driver,String Y){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy(0,"+Y+")");
	}
	/**
	 * 滚动到最下方
	 * @param driver
	 */
	public static void scrollToBottom(WebDriver driver){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
//	/**
//	 * 移动到元素上 ,同action的moveToElement
//	 * @param driver
//	 * @param cssSeletor jquery使用的css选择器
//	 */
//	public static void hoverElement(WebDriver driver,String cssSeletor){
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//		
//	}
	
	/**
	 * 移动到元素element对象的“底端”与当前窗口的“底部”对齐  
	 * @param driver
	 * @param ele
	 */
	public static void scrollToElementBottom(WebDriver driver,WebElement ele){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		//移动到元素element对象的“底端”与当前窗口的“底部”对齐  
		executor.executeScript("arguments[0].scrollIntoView(false);", ele); 
		
	}
	
	
	
	/**
	 * 边框高亮
	 * @param driver
	 * @param ele
	 */
	public static void highLight(WebDriver driver,WebElement ele){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px groove blue'", ele);
		
	}
	
	/**
	 * 边框闪烁 
	 * @param driver
	 * @param ele
	 */
	public static void flashElement(WebDriver driver,WebElement ele){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for(int i=0;i<3;i++){
			js.executeScript("arguments[0].style.border='3px groove blue'", ele);
			Tools.sleep(0.5f);
			js.executeScript("arguments[0].style.border=''", ele);
			Tools.sleep(0.3f);
		}
	}
	
	
	/**
	 * 滚屏到指定元素
	 * @param driver
	 * @param ele
	 */
	public static void scrollToElement(WebDriver driver,WebElement ele){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", ele);
	}
	
	/**
	 * 滚屏到指定坐标
	 * @param driver
	 * @param p point X��Y
	 */
	public static void scrollToPosition(WebDriver driver,Point p){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("scrollTo("+p.x+","+p.y+");");
	}
	
	/**
	 * ִjs点击
	 * @param driver
	 * @param ele
	 */
	public static void click(WebDriver driver,WebElement ele){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	
	
	/**
	 * 去除只读属性
	 * @param driver
	 * @param ele
	 */
	public static void removeReadOnly(WebDriver driver,WebElement ele){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].removeAttribute('readonly');", ele);
	}
	
	/**
	 * 设置属性值ֵ
	 * @param driver
	 * @param ele Ԫ�ض���
	 * @param attr ������	
	 * @param attrValue ����ֵ
	 */
	public static void setAttr(WebDriver driver,WebElement ele,String attr,String attrValue){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('"+attr+"','"+attrValue+"');", ele);
	}
//	public static String getAttr(WebDriver driver,WebElement ele,String attr){
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//	}
	
	
	/**
	 * 去除target属性
	 * @param driver
	 * @param ele
	 */
	public static void removeTarget(WebDriver driver,WebElement ele){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].removeAttribute('target');", ele);
	}
	
	/**
	 * ֱ设置value
	 * @param driver
	 * @param ele
	 * @param value
	 */
	public static void setValue(WebDriver driver,WebElement ele,String value){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].value=\""+value+"\";", ele);
	}
	
	
	/**
	 * Js使用xpath获取元素对象
	 * @param driver
	 * @return
	 */
	private static WebElement getEleByXpath(WebDriver driver, String xPath) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return (WebElement) executor.executeScript("document.evaluate(\"" + xPath
				+ "\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);");

	}
	
	/**
	 * 页面内部Div滚动条的滚动，指定为找到元素的第二个滚动，acs凭证维护页专用
	 * @param driver
	 * @param xPath 带滚动条div内部元素的xpath
	 * @param offset 滚动距离
	 */
//	public static void scrollTop(WebDriver driver, String xPath, String offset) {
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		WebElement ele = getEleByXpath(driver, xPath);
//		executor.executeScript("arguments[0].snapshotItem(1).scrollTop=arguments[1]",ele,offset);
//	}
	
	public static void scrollTop(WebDriver driver, WebElement ele, String offset) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollTop=arguments[1]",ele,offset);
	}
}


//NUMBER_TYPE
//numbervalue 保存结果。
//STRING_TYPE
//stringvalue 保存结果。
//BOOLEAN_TYPE
//booleanValue 保存结果。
//UNORDERED_NODE_ITERATOR_TYPE
//这个结果是节点的无序集合，可以通过重复调用 iterateNext() 直到返回 null 来依次访问。在此迭代过程中，文档必须不被修改。
//ORDERED_NODE_ITERATOR_TYPE
//结果是节点的列表，按照文档中的属性排列，可以通过重复调用 iterateNext() 直到返回 null 来依次访问。在此迭代过程中，文档必须不被修改。
//UNORDERED_NODE_SNAPSHOT_TYPE
//结果是一个随机访问的节点列表。snapshotLength 属性指定了列表的长度，并且 snapshotItem() 方法返回指定下标的节点。节点可能和它们出现在文档中的顺序不一样。既然这种结果是一个“快照”，因此即便文档发生变化，它也有效。
//ORDERED_NODE_SNAPSHOT_TYPE
//这个结果是一个随机访问的节点列表，就像 UNORDERED_NODE_SNAPSHOT_TYPE，只不过这个列表是按照文档中的顺序排列的。
//ANY_UNORDERED_NODE_TYPE
//singleNodeValue 属性引用和查询匹配的一个节点，如果没有匹配的节点则为 null。如果有多个节点和查询匹配，singleNodeValue 可能是任何一个匹配节点。
//FIRST_ORDERED_NODE_TYPE
//singleNodeValue 保存了文档中的第一个和查询匹配的节点，如果没有匹配的节点，则为 null。
