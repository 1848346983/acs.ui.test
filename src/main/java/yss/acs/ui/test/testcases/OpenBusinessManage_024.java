package yss.acs.ui.test.testcases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myutils.common.Tools;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class OpenBusinessManage_024  extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(OpenBusinessManage_024.class);
	
	
	@Test
	public void  openBusinessManage(){
		
		List<MTestcase> testcases = generateTestcases("开放式基金业务");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException |WaitTimeOutException e){
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}
