package yss.acs.ui.test;

import java.util.List;

import myutils.common.Tools;
import yss.acs.ui.test.datasource.DataSourceFileXml;
import yss.acs.ui.test.datasource.DataSourceFileXml.ProductModel;

public class Test1 {

	public static void main(String[] args) {
		
		List<ProductModel> list = DataSourceFileXml.productModels;
		Tools.logInConsole(list.size());
	}
	

}
