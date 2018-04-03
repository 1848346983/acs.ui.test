package yss.acs.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelper {
	
	
	/**
	 * 移动到ele中心
	 * @param driver
	 * @param ele
	 */
	public static void moveToElement(WebDriver driver, WebElement ele) {  
	    Actions action = new Actions(driver);  
	    action.moveToElement(ele).perform();  
	}
	
	/**
	 * 移动到ele,指定偏移量 . 这里的 (xOffset, yOffset) 是以元素 toElement 的左上角为 (0,0) 开始的 (x, y) 坐标轴。
	 * @param driver
	 * @param ele
	 * @param xOffset
	 * @param yOffset
	 */
	public static void moveToElement(WebDriver driver, WebElement ele,int xOffset, int yOffset) {  
	    Actions action = new Actions(driver);  
	    action.moveToElement(ele, xOffset, yOffset).perform();  
	}
	
	/**
	 * 以鼠标当前位置或者 (0,0) 为中心开始移动到 (xOffset, yOffset)
	 * @param driver
	 * @param xOffset
	 * @param yOffset
	 */
	public static void moveByOffset(WebDriver driver,int xOffset, int yOffset) {  
	    Actions action = new Actions(driver);  
	    action.moveByOffset(xOffset, yOffset).perform();  
	}
	
	public static void doubleClick(WebDriver driver,WebElement ele){
		Actions actions = new Actions(driver);
		actions.doubleClick(ele).perform();
	}
	
	public static void click(WebDriver driver,WebElement ele){
		Actions actions = new Actions(driver);
		actions.click(ele).perform();
	}
	
	public static void contextClick(WebDriver driver,WebElement ele){
		Actions actions = new Actions(driver);
		actions.contextClick(ele).perform();
	}
	
	public static void clickAndHold(WebDriver driver,WebElement ele){
		Actions actions = new Actions(driver);
		actions.clickAndHold(ele).perform();
	}
	
	public static void dragAndDrop(WebDriver driver,WebElement ele,int xOffset, int yOffset){
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(ele, xOffset, yOffset).perform();
	}
	
	public static void dragAndDrop(WebDriver driver,WebElement ele,WebElement eleTarget){
		Actions actions = new Actions(driver);
		actions.dragAndDrop(ele, eleTarget).perform();
	}

	public static void keyDown(WebDriver driver,Keys key){
		Actions actions = new Actions(driver);
		actions.keyDown(key).perform();
	}
	
	public static void keyDown(WebDriver driver,WebElement ele,Keys key){
		Actions actions = new Actions(driver);
		actions.keyDown(ele, key).perform();
	}
	
	public static void keyUp(WebDriver driver,Keys key){
		Actions actions = new Actions(driver);
		actions.keyUp(key).perform();
	}
	
	public static void keyUp(WebDriver driver,WebElement ele,Keys key){
		Actions actions = new Actions(driver);
		actions.keyUp(ele, key).perform();
	}

}
















