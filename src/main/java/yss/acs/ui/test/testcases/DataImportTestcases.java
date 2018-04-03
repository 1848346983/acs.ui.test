package yss.acs.ui.test.testcases;

import java.util.List;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class DataImportTestcases extends TestCasesBase {

	private static Logger logger = Logger.getLogger(DataImportTestcases.class);

	@Test(dataProvider = "dp")
	public void f(Integer n, String s) {

		List<MTestcase> testcases = generateTestcases("ETF套利"); // 读数 -对应testcases文件中的sheet页名称 以后改为配置项
		
		try {
			testcaseExecute(testcases, logger);
		} catch (WaitTimeOutException | NoThisElementInPOException e) {
			e.printStackTrace();
		}
		Tools.sleep(1);
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" },
		// new Object[] { 2, "b" },
		};
	}
}
