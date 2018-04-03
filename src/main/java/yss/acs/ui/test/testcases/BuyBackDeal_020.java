package yss.acs.ui.test.testcases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myutils.common.Tools;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class BuyBackDeal_020  extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(BuyBackDeal_020.class);
	
	@Test
	public void buyBackDeal(){
		
		List<MTestcase> testcases = generateTestcases("回购交易");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException | WaitTimeOutException E){
			E.printStackTrace();
		}
		Tools.sleep(2);
	}

}
