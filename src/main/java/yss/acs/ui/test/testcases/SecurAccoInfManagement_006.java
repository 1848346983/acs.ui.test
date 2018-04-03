package yss.acs.ui.test.testcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import myutils.common.Tools;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;

public class SecurAccoInfManagement_006 extends TestCasesBase{
	
	private static Logger logger = Logger.getLogger(SecurAccoInfManagement_006.class);
	
	@Test
	public void SecurAccoInfoManagement(){
		
		List<MTestcase> testcases = generateTestcases("证券账户信息管理");
		try {
			testcaseExecute(testcases, logger);
		} catch (NoThisElementInPOException | WaitTimeOutException e) {
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}