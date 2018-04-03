package yss.acs.ui.test.testcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;




import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class AccIntRateInfo_007 extends TestCasesBase {

	private static final Logger logger = Logger.getLogger(AccIntRateInfo_007.class);


	@Test
	public void AccIntRateInfo() {

		List<MTestcase> testcases = generateTestcases("账户利率信息"); // -对应testcases文件中的sheet页名称
		try {
			testcaseExecute(testcases, logger);
		} catch (WaitTimeOutException | NoThisElementInPOException e) {
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}