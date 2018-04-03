package yss.acs.ui.enums;

public enum PagesName {

    LoginPage("登录页", 1), 
    DataImportPage("读数页", 2),
    HomePage("首页", 3),
    VoucherGeneratePage("生成凭证页",4),
    VoucherMaintaincePage("凭证维护页",5);
    
    
    
   private String name ;
   private int index ;
    
   private PagesName( String name , int index ){
       this.name = name ;
       this.index = index ;
   }
    
   public String getName() {
       return name;
   }
   public void setName(String name) {
       this.name = name;
   }
   public int getIndex() {
       return index;
   }
   public void setIndex(int index) {
       this.index = index;
   }
}
