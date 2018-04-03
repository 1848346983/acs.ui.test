package yss.acs.ui.test.testcases;

import java.util.List;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class ProductAssor_011 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(ProductAssor_011.class);
	
	
	@Test
	public void productAssor(){
		
		List<MTestcase> testcases = generateTestcases("产品关联业务方案管理");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException | WaitTimeOutException e){
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}
