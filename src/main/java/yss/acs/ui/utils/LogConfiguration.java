package yss.acs.ui.utils;

import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;

public class LogConfiguration {

	public static void initLog(String fileName) {
		// 获取到模块名字
		//String functionName = getFunctionName(fileName);
		// 声明日志文件存储路径以及文件名、格式
		final String logFilePath = "./test-output/excelReports/log/" + fileName + ".log";
		Properties prop = new Properties();
		prop.setProperty("log4j.rootLogger", "info, toConsole, toFile");
		prop.setProperty("log4j.appender.file.encoding", "UTF-8");
		prop.setProperty("log4j.appender.toConsole", "org.apache.log4j.ConsoleAppender");
		prop.setProperty("log4j.appender.toConsole.Target", "System.out");
		prop.setProperty("log4j.appender.toConsole.layout", "org.apache.log4j.PatternLayout ");
		//设置日志输出格式  参考http://www.cnblogs.com/angleBlue/p/4769234.html
		prop.setProperty("log4j.appender.toConsole.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%c][行:%L] %m%n");
		prop.setProperty("log4j.appender.toFile", "org.apache.log4j.DailyRollingFileAppender");
		prop.setProperty("log4j.appender.toFile.file", logFilePath);
		prop.setProperty("log4j.appender.toFile.append", "true");
		prop.setProperty("log4j.appender.toFile.Threshold", "info");
		prop.setProperty("log4j.appender.toFile.layout", "org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.toFile.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%c][行:%L] %m%n");
//		prop.setProperty("log4j.appender.fileout.layout","org.apache.log4j.PatternLayout");
//		prop.setProperty("log4j.appender.stdout.layout","org.apache.log4j.PatternLayout");
		// 使配置生效
		PropertyConfigurator.configure(prop);
	}

//	/** 取得模块名字 */
//	private static String getFunctionName(String fileName) {
//		String functionName = null;
//		int firstUndelineIndex = fileName.indexOf("_Cases");
//		functionName = fileName.substring(0, firstUndelineIndex);
//		return functionName;
//
//	}

}
