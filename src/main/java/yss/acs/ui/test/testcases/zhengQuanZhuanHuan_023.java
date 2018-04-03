package yss.acs.ui.test.testcases;

import java.util.List;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class zhengQuanZhuanHuan_023 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(zhengQuanZhuanHuan_023.class);
	
	@Test
	public void zhengQuanZhuanHuan(){
		
		List<MTestcase> testcases = generateTestcases("证券转换业务信息管理");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException | WaitTimeOutException e){
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}
