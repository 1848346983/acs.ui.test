package yss.acs.ui.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;

public class MyEventListener implements WebDriverEventListener {
	
	private static Logger _logger = Logger.getLogger(MyEventListener.class);
	
	

	@Override
	public void onException(Throwable ex, WebDriver driver) {
		
//		for (LogEntry log : driver.manage().logs().get(LogType.DRIVER).getAll()) {
//            System.out.println("Level:" + log.getLevel().getName());
//            System.out.println("Message:" + log.getMessage());
//            System.out.println("Time:" + log.getTimestamp());
//            System.out.println("-----------------------------------------------");
//	     }
		//_logger.info("Message >>>>>>>>>>>>>>>>>>> :" + ex.getMessage());
		 
		if(ex.getMessage().contains("no such element")){
			String msg = ex.getMessage();
			_logger.info("等待元素展示...xpath = " + msg.substring(msg.indexOf("selector\":")+11, msg.indexOf("}")));
			return;
		}
		if(driver instanceof TakesScreenshot){
			SimpleDateFormat formatter_mm = new SimpleDateFormat("HH-mm-ss");
			SimpleDateFormat formatter_day = new SimpleDateFormat("yyyy-MM-dd");
			String directoryName = formatter_day.format(new Date());
			String fileName = formatter_mm.format(new Date())
							  + "_" + generateFileNameByEx(ex);
			String filePath = "test-output/ScreenShot/"+ directoryName+ "/"
							+ fileName + ".png";
			File tmpfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 try {
	              _logger.info(">>>>>>>>>>LOGS: ScreenShot Path : " + filePath); //日志打印完整文件路径
	            //文件名太长无法保存，只保存30位
	              if(fileName.length()>30){ 
	            	  filePath = "test-output/ScreenShot/"+ directoryName+ "/"
								+ fileName.substring(0,30) + ".png";
	              }
	              FileUtils.copyFile(tmpfile, new File(filePath));
//	              Reporter.log(">>>>>>>>>>LOGS: " + filePath);
//	              Reporter.log("<img src=\"../" + filePath + "\"/>");
	          } catch (IOException e) {
	            e.printStackTrace();
	          }
		}

//		Throwable cause = ex.getCause();
//		if (cause instanceof ScreenshotException) {
//			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-hh-mm");
//			String dateString = formatter.format(new Date());
//			String fileName = dateString + " " + generateRandomFilename(ex);
//			Log.logInfo("文件路径 : "+ fileName);
//			File of = new File(fileName);
//			FileOutputStream out = null;
//
//			try {
//				out = new FileOutputStream(of);
//				out.write(new Base64Encoder().decode(((ScreenshotException) cause).getBase64EncodedScreenshot()));
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}

		// String filename = generateRandomFilename(ex);
		// try {
		// byte[] btDataFile =
		// Base64.decodeBase64(extractScreenShot(ex).getBytes());
		// File of = new File(filename);
		// FileOutputStream osf = new FileOutputStream(of);
		// osf.write(btDataFile);
		// osf.flush();
		// osf.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}
	
	private String generateFileNameByEx(Throwable ex) {
		// Calendar c = Calendar.getInstance();
		String filename = ex.getMessage();
		int i = filename.indexOf('\n');
		filename = filename.substring(0, i).replaceAll("\\s", "_").replaceAll(":", "");
//		if(filename.length()>25){
//			filename = filename.substring(0,25) + ".png";
//		}else{
//			filename = filename + ".png";
//		}
		return filename;
	}

	// private String extractScreenShot(Throwable ex) {
	// Throwable cause = ex.getCause();
	// if (cause instanceof ScreenshotException) {
	// return ((ScreenshotException) cause).getBase64EncodedScreenshot();
	// }
	// return null;
	// }

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

}
