package yss.acs.ui.test.testcases;

import java.util.List;

import myutils.common.Tools;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisElementInPOException;
import yss.acs.ui.exception.WaitTimeOutException;
import yss.acs.ui.model.MTestcase;
import yss.acs.ui.test.base.TestCasesBase;




public class AssetValuTable_025 extends TestCasesBase{

	private static final Logger logger = Logger.getLogger(AssetValuTable_025.class);

	@Test
	public void AssetValuTable() {

		List<MTestcase> testcases = generateTestcases("资产估值表"); // -对应testcases文件中的sheet页名称
		try {
			testcaseExecute(testcases, logger);
		} catch (WaitTimeOutException | NoThisElementInPOException e) {
			e.printStackTrace();
		}
		Tools.sleep(2);
	}

}
