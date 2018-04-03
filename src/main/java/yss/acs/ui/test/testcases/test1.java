package yss.acs.ui.test.testcases;

import java.io.File;

import myutils.common.DateUtils;
import myutils.common.MailUtils;
import myutils.common.MailUtils.MailModel;
import yss.acs.ui.utils.CreateZip;

public class test1 {

//	private static final String  FilePath = "./test-output/excelReports";
//	private  final static String saveFilePath ="./test-output/excelReports/log" ;
	private static final String zipFilePath="./test-output/20170801.zip" ;	
	
	public static void main(String[] args) throws Exception{
		
		
		
		CreateZip.fileToZip();
		Thread.sleep(5000);

			if (new File(zipFilePath).exists()) {
				String date = DateUtils.getNowString("yyyy年MM月dd日");
				MailModel model = new MailModel();
				model.userName = "chendaiwu@ysstech.com";
				model.from = "chendaiwu@ysstech.com";
				model.to = "chendaiwu@ysstech.com;liuwenjuan@ysstech.com";
				model.passWord = "cdw520DW0614";
				model.smtpHost = "smtp.ysstech.com";
				model.smtpPort = "25";
				model.messageText = "你好! \n 本邮件是 " + date + " 的跑账差异数据结果,请下载附件查看...";
				model.subject = "跑账测试结果 -- " + date;
				MailUtils.sendMessage(model, zipFilePath);
				System.out.println("邮件已发送，请注意查收！...");
			} else {
				System.out.println("没有找到对应的测试结果文件,邮件不发送...");
			}
		}
	

}
