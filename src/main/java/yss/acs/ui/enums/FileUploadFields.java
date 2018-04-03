package yss.acs.ui.enums;

public enum FileUploadFields {

    ServerIP("上传服务器", 1), 
    FtpFilePath("FTP文件路径", 2),
    LocalFilePath("本地文件路径", 3),
    FileDate("文件日期",4),
    IfExecute("是否执行",5);
    
    
    
   private String name ;
   private int index ;
    
   private FileUploadFields( String name , int index ){
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
