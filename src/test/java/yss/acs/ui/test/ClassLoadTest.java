package yss.acs.ui.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import myutils.common.Tools;
import yss.acs.ui.config.ConfigWeb;
import yss.acs.ui.pages.PageAcs;

public class ClassLoadTest {

	public static void main(String[] args) {
		
		 try {
				Class<?> class1 = Class.forName("yss.acs.ui.pages.PageHome");
				Constructor<?> c = class1.getDeclaredConstructor(new Class[]{String.class,String.class});
				PageAcs page = null;
				try {
					page = (PageAcs) c.newInstance(new Object[]{ConfigWeb.pageObjectFilePath,"读数页"});
				} catch (IllegalArgumentException | InvocationTargetException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

	}

}
