package yss.acs.ui.test.testcases;

import java.util.List;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class ZiChanYuEBiao_026 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(ZiChanYuEBiao_026.class);
	
	
	@Test
	public void ziChanYuEBiao(){
		
		List<MTestcase> testcases = generateTestcases("资产余额表");
		try {
			testcaseExecute(testcases, logger);
		} catch (NoThisElementInPOException | WaitTimeOutException e) {
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}
