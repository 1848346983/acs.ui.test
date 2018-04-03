package yss.acs.ui.test.testcases;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import myutils.common.Tools;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class ZhengQaunLiuTong_022 extends TestCasesBase {
	
private static Logger logger = Logger.getLogger(ZhengQaunLiuTong_022.class);
	
	@Test
	public  void zhengQuanLiuTong_022(){
		
		List<MTestcase> testcases = generateTestcases("证券流通信息管理");
		
		try{
			testcaseExecute(testcases, logger);
		}catch(NoThisElementInPOException | WaitTimeOutException e){
			e.printStackTrace();
		}
		Tools.sleep(2);
	}


}
