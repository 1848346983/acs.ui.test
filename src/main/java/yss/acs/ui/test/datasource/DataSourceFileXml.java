package yss.acs.ui.test.datasource;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.testng.ITestContext;

import yss.acs.ui.base.DataFile;
import yss.acs.ui.model.MTestParams;
import yss.acs.ui.utils.ParseXml;

public class DataSourceFileXml extends DataFile {


	private static List<Element> proElements;
	public static List<ProductModel> productModels = new ArrayList<ProductModel>();
	
	static {
		ParseXml px = new ParseXml("raw/datapool.xml");
		proElements = px.getElementObjects("//data/products/product");
		for(Element ele : proElements){
			ProductModel m = new ProductModel();
			m.productID = ((Element)ele.selectObject(".//productID")).getTextTrim();
			m.productName = ((Element)ele.selectObject(".//productName")).getTextTrim();
			productModels.add(m);
		}
	}
	
	public static class ProductModel extends MTestParams{
		public String productID;
		public String productName;
		//public ITestContext context;
		@Override
		public String toString() {
			return "[productID=" + productID + "]";
		}
	}
}
