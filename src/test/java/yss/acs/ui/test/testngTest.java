package yss.acs.ui.test;

import myutils.common.Tools;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class testngTest {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	 // Tools.logInConsole(n + " " + s);
  }
  @BeforeMethod
  public void beforeMethod() {
	  
	  //Tools.logInConsole("BM");
  }

  @AfterMethod
  public void afterMethod() {
	 // Tools.logInConsole("AM");
  }


  @DataProvider
  public Object[][] dp(ITestContext con) {
	  
	  Tools.logInConsole(con.getHost());
	  Tools.logInConsole(con.getName());
	  Tools.logInConsole(con.getOutputDirectory());
	  Tools.logInConsole(con.toString());
	  con.setAttribute("123", "321");
	  
//	  ITestNGMethod[] ms =  con.getAllTestMethods();
//	  for(ITestNGMethod m : ms){
//		  m.ge
//	  }
	  
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
	  //Tools.logInConsole("BC");
  }

  @AfterClass
  public void afterClass() {
	 // Tools.logInConsole("AC");
  }

}
