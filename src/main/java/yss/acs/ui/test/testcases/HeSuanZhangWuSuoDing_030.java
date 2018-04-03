package yss.acs.ui.test.testcases;

import java.util.List;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class HeSuanZhangWuSuoDing_030 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(HeSuanZhangWuSuoDing_030.class);
	
	@Test
	public void HeSuanZhangWuSuoDing(){
		
		List<MTestcase> testcases = generateTestcases("核算账务锁定");
		try {
			testcaseExecute(testcases, logger);
		} catch (NoThisElementInPOException | WaitTimeOutException e) {
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}