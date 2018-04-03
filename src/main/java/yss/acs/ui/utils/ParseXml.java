package yss.acs.ui.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ParseXml {
	
	private String filePath;
	private Document document;
	
	/**
	 * 解析xml文件
	 * @param filePath XML�ļ�·��
	 */
	public ParseXml(String filePath){
		this.filePath = filePath;
		this.load(this.filePath);
	}
	
	/**
	 * ����XML�ļ������document����
	 * @param filePath
	 */
	private void load(String filePath) {
		
		File file = new File(filePath);
		if(file.exists()){
			SAXReader saxReader = new SAXReader();
			try {
				document = saxReader.read(file);
			} catch (DocumentException e) {
				System.out.println("xml文件读取失败!" + filePath);
			}
		}else{
			System.out.println("xml文件没找到!" + filePath);
		}
	}
	
	/**
	 * 
	 * @param elementPath xml�нڵ��xpath·�� �� /config/driver
	 * @return
	 */
	private Element getElementObject(String elementPath){
		return (Element)document.selectSingleNode(elementPath);
	}
	
	
	private List<Element> getElementsObject(String xpath){
		List<Element> list = (List<Element>) document.selectNodes(xpath);
		return list;
	}
	
	
	
	/**
	 * 判断节点是否存在
	 * @param elementPath
	 * @return
	 */
	public boolean isExist(String elementPath){
		boolean flag = false;
		Element element = this.getElementObject(elementPath);
		if(element!=null){
			flag = true;
		}
		return flag;
	}
	
	/**
	 *获取节点文本
	 * @param elementPath
	 * @return
	 */
	public String getElementText(String elementPath){
		Element element = getElementObject(elementPath);
		if(element!=null){
			return element.getText().trim();
		}else{
			return null;
		}
	}
	
	/**
	 * 获取节点 对象
	 * @param elementPath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Element> getElementObjects(String elementPath){
		return document.selectNodes(elementPath);
	}
	
	
	/**
	 * 将document对象保存为XML文档
	 * @param doc
	 * @param filePath
	 */
	public void writeXml(Document doc,String filePath) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
			writer.write(doc);
			writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	

}
