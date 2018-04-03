package yss.acs.ui.utils;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.print.attribute.Size2DSyntax;

import myutils.common.LogUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenShot {
	
	
	/**
	 * 截图 文件路径 test-output/ScreenShot/MM-dd-HH-mm.png
	 * @param driver
	 */
//	public static void takeScreenshot(WebDriver driver){
//		
//		//String screenName = String.valueOf(new Date().getTime()) + ".png";
//		Date today = new Date(System.currentTimeMillis());
//		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-HH-mm");
//		String screenName = formatter.format(today) + ".png";
//		//File dir = new File("test-output/ScreenShot");
//		File dir = new File("test-output/ScreenShot/" + formatter.format(today));
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
//		String screenPathString = dir.getAbsolutePath() + "/" + screenName;
//		saveScreenShot(driver, screenPathString);
//	}
	
	/** 
	 * 截图 文件名自定义   路径 test-output/ScreenShot/yyyy-MM-dd/HH-mm-ss + _nameArgs.jpg
	 * @param driver
	 * @param nameArgs
	 */
	public static void takeScreenshot(WebDriver driver,String nameArgs){
		
		
		nameArgs = nameArgs.replace(":", "").replace("/", "");
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat formatterFile = new SimpleDateFormat("HH-mm-ss");
		String picName = formatterFile.format(today) +"_"+nameArgs+ ".png";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		File dir = new File("test-output/ScreenShot/" + formatter.format(today));
		if(!dir.exists()){
			dir.mkdirs();
		}
		String screenPathString = dir.getAbsolutePath() + "\\" + picName;
		//saveScreenShot(driver, screenPathString,new Point(300, 50),new Dimension(800, 800));
		saveScreenShot(driver, screenPathString);
		//LogUtils.logInfo("截图路径~~: " + screenPathString);
		Reporter.log("截图路径~~: " + screenPathString);
		Reporter.log("<img src=\"../" + screenPathString + "\"/>");
		//saveScreenShot(driver, screenPathString);
	}
	
	/**
	 * 截图 路径 test-output/ScreenShot/exmessage文件名
	 * @param driver
	 * @param ex
	 */
	public static void takeScreenshot(WebDriver driver,Throwable ex){
		
		String screenName = generateFilename(ex);
		File dir = new File("test-output/ScreenShot");
		if(!dir.exists()){
			dir.mkdirs();
		}
		//String screenPathString = dir.getAbsolutePath() + "/" + screenName;
		String screenPathString = dir.getAbsolutePath() + screenName;
		saveScreenShot(driver, screenPathString);
	}
	

	private static void saveScreenShot(WebDriver driver,String filePath) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			org.apache.commons.io.FileUtils.copyFile(file,new File(filePath));
		} catch (IOException e) {
			LogUtils.logInfo("保存截图出错！  " + filePath);
		}
	}
	
	/**
	 * 指定区域截图
	 * @param driver
	 * @param filePath
	 * @param p 起点坐标
	 * @param size 长 宽
	 */
	public static void takeScreenShot(WebDriver driver,String filePath,Point p,Dimension size) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage originalImage = null;
		BufferedImage desImage = null;
		try {
			originalImage = ImageIO.read(file);
			//desImage = originalImage.getSubimage(p.x, p.y, size.width, size.height);
			desImage = originalImage.getSubimage(p.x, p.y, size.width, size.height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ImageIO.write(desImage, "png", new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	private static String generateFilename(Throwable ex) {
		String filename = ex.getMessage();
		int i = filename.indexOf('\n');
		filename = filename.substring(0, i).replaceAll("\\s", "_").replaceAll(":", "") + ".png";
		return filename;
	}

}
