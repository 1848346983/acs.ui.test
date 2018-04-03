package yss.acs.ui.test.testcases;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import myutils.common.DateUtils;
import myutils.common.MailUtils;
import myutils.common.Tools;
import myutils.common.MailUtils.MailModel;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;
import yss.acs.ui.utils.CreateZip;

public class DataChangeTestcases extends TestCasesBase {
	
	private static Logger logger = Logger.getLogger(DataChangeTestcases.class);
//	private static final String today = DateUtils.getNowString("yyyyMMdd");
//	private static final String  FilePath = "./test-output/excelReports/log";
//	private static final String saveFilePath="./test-output/excelReports" ;
//	private static final String zipFilePath="./test-output/excelReports/" + today +".zip" ;
	
	
	@Test
	public void DC(){
		
		List<MTestcase> testcases = generateTestcases("陈代武");
		
		try{
			testcaseExecute(testcases, logger);
//			CreateZip.fileToZip();
		}catch(WaitTimeOutException | NoThisElementInPOException e) {
			e.printStackTrace();
		}
		Tools.sleep(1);
	}
		
	
//	@DataProvider
//	public Object[][] dp(){
//		return new Object[][] { new Object[]{ 1, "a"},
//		};
//	}
//	@org.testng.annotations.AfterClass
//	public void AfterClass() {
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

}
