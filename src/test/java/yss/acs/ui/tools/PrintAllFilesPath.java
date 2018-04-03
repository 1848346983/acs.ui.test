package yss.acs.ui.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myutils.common.Tools;
import myutils.iotools.Filetools;

public class PrintAllFilesPath {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		Filetools.getAllFilesInDir("D:\\ACS工作文档\\测试文档\\41778政策类  -- 营改增\\联网测试用例\\股票和基金\\股票测试数据",true);
		list = Filetools.getFileList();
		for(String s : list){
			String dirName = "";
			Pattern pattern = Pattern.compile("201[0-9]-{0,1}[0-1][0-9]-{0,1}[0-3][0-9]");
			Matcher matcher = pattern.matcher(s);
			if(matcher.find()){
				dirName = matcher.group(0);
			}
			Tools.logInConsole(s+"	"+dirName.replace("-", ""));
		}
	}

}
