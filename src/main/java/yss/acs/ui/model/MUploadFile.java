package yss.acs.ui.model;

public class MUploadFile {
	
	private String pNo; //步骤编号
	private String ifExec; //是否执行
	private String serverIP; //服务器IP
	private String ftpPath; //ftp文件路径
	private String localPath; //本地文件路径
	private String fileDate; //文件日期
	
	public String getpNo() {
		return pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = pNo;
	}
	public String getIsExec() {
		return ifExec;
	}
	public void setIsExec(String isExec) {
		this.ifExec = isExec;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getFtpPath() {
		return ftpPath;
	}
	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	
}
