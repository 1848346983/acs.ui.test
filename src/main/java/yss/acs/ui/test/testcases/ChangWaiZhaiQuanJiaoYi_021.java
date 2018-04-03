package yss.acs.ui.test.testcases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myutils.common.Tools;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class ChangWaiZhaiQuanJiaoYi_021 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(ChangWaiZhaiQuanJiaoYi_021.class);
	
	@Test
	public void publicBonds(){
		
		List<MTestcase> testcases = generateTestcases("场外债券");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(WaitTimeOutException | NoThisElementInPOException e){
			e.printStackTrace();
		}
		Tools.sleep(5);
	}

}