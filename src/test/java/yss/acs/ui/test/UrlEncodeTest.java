package yss.acs.ui.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import myutils.common.Tools;

public class UrlEncodeTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		
//		URLDecoder decoder = new URLDecoder();
//		String s = decoder.decode("%E4%BA%A7%E5%93%81%E6%95%B0%E6%8D%AE%E8%AF%BB%E5%8F%96");
		
		String s = "";
		 try {
			s = URLEncoder.encode("产品数据读取","utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Tools.logInConsole(s);

	}

}
