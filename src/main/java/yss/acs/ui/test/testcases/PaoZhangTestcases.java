package yss.acs.ui.test.testcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import myutils.common.DateUtils;
import myutils.common.ExcelAdapter;
import myutils.common.MailUtils;
import myutils.common.MailUtils.MailModel;
import myutils.common.Tools;
import myutils.common.ZipUtils;

import org.apache.log4j.Logger;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;
import yss.acs.ui.test.datasource.DataSourceFileXml;
import yss.acs.ui.test.datasource.DataSourceFileXml.ProductModel;

public class PaoZhangTestcases extends TestCasesBase {

	private static final Logger logger = Logger.getLogger(PaoZhangTestcases.class);
	private static final String today = DateUtils.getNowString("yyyyMMdd");
	private static final String zipFilePath = "test-output/excelReports/TestResult_" + today + ".zip";

	@Test(dataProvider = "dp")
	public void f(Integer n, ProductModel productModel) {

		List<MTestcase> testcases = generateTestcases("产品回归"); // -对应testcases文件中的sheet页名称

		// inputProduct元素的参数值改为由dataprovider动态提供
		for (int i = 0; i < testcases.size(); i++) {
			if (testcases.get(i).getElementName().equals("inputProduct")) {
				testcases.get(i).setParams(productModel.productName);
				// Tools.logInConsole(testcases.get(i).toString());
			}
		}
                                           
		String xlsfilePath = "";
		try {
			super.testcaseExecute(testcases, logger);
			String productID = productModel.productID;
			xlsfilePath = "test-output/excelReports/TestResult_" + today + "_" + productID + ".xlsx";
			String startDay = "20170328";
			for (int i = 0; i <= 2; i++) {
				startDay = DateUtils.getTomorrow(startDay);
				writeDiffDataToExcel(xlsfilePath, startDay, productID);
			}
			if (n == 0) {
				ZipUtils.compress(xlsfilePath, zipFilePath);
				logger.info(" Zip压缩包创建完成!");
			} else {
				ZipUtils.addFileToZip(new File(zipFilePath), new File(xlsfilePath), new File(xlsfilePath).getName());
				logger.info(" 压缩包添加第 " + (n + 1) + " 个文件完成!");
			}

		} catch (WaitTimeOutException | NoThisElementInPOException | IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		Tools.sleep(2);
	}

	@org.testng.annotations.AfterClass
	public void AfterClass() {

		if (new File(zipFilePath).exists()) {
			String date = DateUtils.getNowString("yyyy年MM月dd日");
			MailModel model = new MailModel();
			model.userName = "yangxiaodong@ysstech.com";
			model.from = "yangxiaodong@ysstech.com";
			model.to = "yangxiaodong@ysstech.com";
			model.passWord = "ysstech123!@#";
			model.smtpHost = "smtp.ysstech.com";
			model.smtpPort = "25";
			model.messageText = "你好! \r\n 本邮件是 " + date + " 的跑账差异数据结果,请下载附件查看...";
			model.subject = "跑账测试结果 -- " + date;
			MailUtils.sendMessage(model, zipFilePath);
		} else {
			logger.error("没有找到对应的测试结果文件,邮件不发送...");
		}
	}

	@DataProvider
	public Object[][] dp(ITestContext context) {

		List<ProductModel> prolist = getProductIdList(context);
		Object[][] oarr = new Object[prolist.size()][2];
		for (int i = 0; i < prolist.size(); i++) {
			oarr[i][0] = i;
			// prolist.get(i).context = context;
			// prolist.get(i).context.setAttribute("parameter",
			// prolist.get(i).productID);
			// prolist.get(i).context.setAttribute("driver", driver);
			oarr[i][1] = prolist.get(i);
		}
		// for(Object[] os : oarr){
		// Tools.logInConsole(((ProductModel)os[1]).context.getAttribute("parameter").toString());
		// }

		return oarr;
		// return new Object[][] { new Object[] { 1, "my name is testng" } };
	}

	private List<ProductModel> getProductIdList(ITestContext context) {

		List<ProductModel> list = DataSourceFileXml.productModels;
		return list;
	}

	private void writeDiffDataToExcel(String filePath, String date, String productID) {
		DBI dbi = new DBI("jdbc:oracle:thin:@192.168.1.113:1521:orcl", "acshx", "acshx");
		Handle h = dbi.open();

		logger.info("开始执行SQL,查询日期为 " + date + " 的估值表差异数据");

		StringBuilder sb = new StringBuilder();
		sb.append("select *  from (");
		sb.append("(select 'OLD' tag, b.fproduct_id, b.fdate, b.fsubject_code, b.fsubject_name, b.fprice, b.famount, b.fcost, b.fmarket_value, b.fvaluation, b.fycost, b.fymarket_value, b.fyvaluation  ");
		sb.append("from t_fa_valuation_1223 b where b.fdate >= to_date(:fromDate, 'yyyymmdd') and b.fdate <= to_date(:toDate, 'yyyymmdd')");
		sb.append("and b.fproduct_id = :productID minus ");
		sb.append("select 'OLD' tag, a.fproduct_id, a.fdate, a.fsubject_code, a.fsubject_name, a.fprice, a.famount, a.fcost, a.fmarket_value, a.fvaluation, a.fycost, a.fymarket_value, a.fyvaluation  ");
		sb.append("from t_fa_valuation a where a.fdate >= to_date(:fromDate, 'yyyymmdd') and a.fdate <= to_date(:toDate, 'yyyymmdd')and a.fproduct_id = :productID) ");
		sb.append("union all  (select 'NEW' tag, b.fproduct_id, b.fdate, b.fsubject_code, b.fsubject_name, b.fprice, b.famount, b.fcost, b.fmarket_value, b.fvaluation, b.fycost, b.fymarket_value, b.fyvaluation  ");
		sb.append("from t_fa_valuation b where b.fdate >= to_date(:fromDate, 'yyyymmdd') and b.fdate <= to_date(:toDate, 'yyyymmdd')");
		sb.append("and b.fproduct_id = :productID minus ");
		sb.append("select 'NEW' tag, a.fproduct_id, a.fdate, a.fsubject_code, a.fsubject_name, a.fprice, a.famount, a.fcost, a.fmarket_value, a.fvaluation, a.fycost, a.fymarket_value, a.fyvaluation  ");
		sb.append("from t_fa_valuation_1223 a where a.fdate >= to_date(:fromDate, 'yyyymmdd') and a.fdate <= to_date(:toDate, 'yyyymmdd')and a.fproduct_id = :productID)) tt ");
		sb.append("order by tt.fsubject_code,tt.fdate");

		List<Map<String, Object>> list = h.createQuery(sb.toString()).bind("fromDate", date).bind("toDate", date).bind("productID", productID).list();

		// if (true) {
		// CollectionUtils.filter(list, new Predicate() {
		//
		// @Override
		// public boolean evaluate(Object rowMap) {
		// Map<String, Object> map = (Map<String, Object>) rowMap;
		// return map.get("FSUBJECT_CODE").toString().indexOf(".") > 0;
		// }
		// });
		// }

		h.close();

		String[] headers = { "TAG", "FPRODUCT_ID", "FDATE", "FSUBJECT_CODE", "FSUBJECT_NAME", "FPRICE", "FAMOUNT", "FCOST", "FMARKET_VALUE",
				"FVALUATION" };
		List<String> headerList = new ArrayList<>(Arrays.asList(headers));
		ArrayList<List<String>> datalist = new ArrayList<List<String>>();
		datalist.add(headerList);
		for (Map<String, Object> map : list) {
			List<String> list2 = new ArrayList<String>();
			for (String header : headerList) {
				String value = map.get(header) == null ? "" : map.get(header).toString();
				list2.add(value);
			}
			datalist.add(list2);
		}
		ExcelAdapter ea = new ExcelAdapter(filePath, "估值表" + date);
		ea.setSheetData(datalist);
		ea.writeExcel(filePath);
		logger.info("执行完毕!,结果文件路径  : " + filePath + " sheet名 : " + date);
	}

}
