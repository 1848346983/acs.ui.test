package yss.acs.ui.test.testcases;

import java.util.List;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;




public class BusiProManag_010 extends TestCasesBase{

	private static final Logger logger = Logger.getLogger(BusiProManag_010.class);

	@Test
	public void BusiProManag() {

		List<MTestcase> testcases = generateTestcases("业务方案管理"); // -对应testcases文件中的sheet页名称
		try {
			testcaseExecute(testcases, logger);
		} catch (WaitTimeOutException | NoThisElementInPOException e) {
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}