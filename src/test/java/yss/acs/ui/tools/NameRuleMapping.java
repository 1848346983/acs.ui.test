package yss.acs.ui.tools;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myutils.common.Tools;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class NameRuleMapping {
	
	private static String impGroupConfigFilepath = "D:/ACS工作文档/xml/ImpGroupConfig.xml";
	private static String xmlFolderPath = "D:/ACS工作文档/xml/";

	public static void main(String[] args) {
		try {
			test1(impGroupConfigFilepath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void test1(String filePath) throws DocumentException{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(filePath));
		Element root = doc.getRootElement();
		List<Element> group0List = (List<Element>)doc.selectNodes("//ImpGroupConfigs/group");
		for(Element e : group0List){
			Tools.logInConsole(e.attributeValue("name")+"	一级分类");
			List<Element> group1List = (List<Element>)e.selectNodes("./group");
			for(Element e1 : group1List){
				List<Element> group2List = (List<Element>)e1.selectNodes("./group");
				if(group2List.size()>0){
					Tools.logInConsole(e1.attributeValue("name") + "	二级分类");
					for(Element e2:group2List){
						Element dixml = (Element)e2.selectSingleNode("./dixml");
						Map<String , String> map = getNameRuleFromXml(dixml.getText());
						String nameRule = map!=null?map.get("namerule"):"无数据";
						String tableName = map!=null?map.get("tablename"):"无数据";
						String xmlPath = "=HYPERLINK(\".\\xml\\"+dixml.getText()+".xml\",\""+dixml.getText()+".xml\")";
						Tools.logInConsole(e2.attributeValue("name") + "	子节点	" + xmlPath + "	"+nameRule+"	"+tableName);
					}
				}else{
					Element dixml = (Element)e1.selectSingleNode("./dixml");
					Map<String , String> map = getNameRuleFromXml(dixml.getText());
					String nameRule = map!=null?map.get("namerule"):"无数据";
					String tableName = map!=null?map.get("tablename"):"无数据";
					String xmlPath = "=HYPERLINK(\".\\xml\\"+dixml.getText()+".xml\",\""+dixml.getText()+".xml\")";
					Tools.logInConsole(e1.attributeValue("name") + "	子节点	" + xmlPath + "	"+nameRule+"	"+tableName);
				}
			}
		}
	}
	
	private  static Map<String, String> getNameRuleFromXml(String xmlName){
		String xmlDir = xmlFolderPath;
		String xmlFilePath = xmlDir+xmlName+".xml";
		SAXReader reader = new SAXReader();
		Document doc=null;
		try {
			doc = reader.read(new File(xmlFilePath));
		} catch (DocumentException e) {
			return null;
		}
		Element element = (Element)doc.selectSingleNode("//ImpConfig/FileExplainConfig/nameRule");
		String nameRule = "";
		if(element!=null){
			nameRule = element.getText();
		}
		String tableName = "";
		Element element2 = (Element)doc.selectSingleNode("//ImpConfig/ImpDbConfig/tableName");
		if(element2!=null){
			tableName = element2.getText();
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("namerule", nameRule);
		map.put("tablename", tableName);
		return map;
	}

}
