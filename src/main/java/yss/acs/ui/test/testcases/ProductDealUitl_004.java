package yss.acs.ui.test.testcases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myutils.common.Tools;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class ProductDealUitl_004 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(ProductDealUitl_004.class);
	
	
	@Test
	public void productDealUitl(){
		
		List<MTestcase> testcases = generateTestcases("产品交易关联");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException |WaitTimeOutException e){
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}
