package yss.acs.ui.test.testcases;



import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myutils.common.Tools;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class ProductBonds_017 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(ProductBonds_017.class);
	
	
	@Test
	public void productBonds(){
		
		List<MTestcase> testcases = generateTestcases("产品债券");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException | WaitTimeOutException e){
			e.printStackTrace();
		}
		Tools.sleep(5);
	}

}
