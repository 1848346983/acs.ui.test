package yss.acs.ui.tools;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import yss.acs.ui.exception.NoThisFtpServerException;
import yss.acs.ui.utils.FtpUtils;
import yss.acs.ui.utils.LogConfiguration;

public class FileUpload {
	@Test
	public void f() {

		try {
			FtpUtils ftp = new FtpUtils("D:\\ACS工作文档\\测试文档\\41778政策类  -- 营改增\\营改增驱动数据.xlsx", "数据上传");
			ftp.uploadFiles();
		} catch (NoThisFtpServerException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void BeforeClass(ITestContext context) {
		LogConfiguration.initLog(this.getClass().getSimpleName());
	}
}
