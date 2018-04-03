package yss.acs.ui.test.testcases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myutils.common.Tools;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class ProductBasic_001  extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(ProductBasic_001.class);
	
	@Test
	public void productDasic(){
		
		List<MTestcase> testcases = generateTestcases("基本信息");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException |WaitTimeOutException e){
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}
