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

public class FrienAccountInfo_009 extends TestCasesBase {

	private static final Logger logger = Logger.getLogger(FrienAccountInfo_009.class);


	@Test
	public void FrienAccountInfo() {

		List<MTestcase> testcases = generateTestcases("友好账户信息管理"); // -对应testcases文件中的sheet页名称
		try {
			testcaseExecute(testcases, logger);
		} catch (WaitTimeOutException | NoThisElementInPOException e) {
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}