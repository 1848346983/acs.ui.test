package yss.acs.ui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import myutils.ftp.CommonFtp;
import myutils.ftp.FtpConfig;
import myutils.ftp.FtpConfig.FtpModel;

import org.apache.log4j.Logger;

import yss.acs.ui.config.ConfigWeb;
import yss.acs.ui.enums.FileUploadFields;
import yss.acs.ui.exception.NoThisFtpServerException;
import yss.acs.ui.test.datasource.DataSouceFileXls;

public class FtpUtils {
	
	private static Logger logger = Logger.getLogger(FtpUtils.class);
	private CommonFtp _ftp;
	//private List<List<String>> _rawDataList;
	private DataSouceFileXls _dataSouceFile;
	
	public FtpUtils(String xlsFilePath,String sheetName) throws NoThisFtpServerException{
		_ftp = this.getFtp(xlsFilePath, sheetName);
		//_rawDataList = _dataSouceFile.getSourceData();
	}
	
	private CommonFtp getFtp(String xlsFilePath,String sheetName) throws NoThisFtpServerException{
		_dataSouceFile = new DataSouceFileXls(xlsFilePath, sheetName);
		//ArrayList<String> serverIPList = _dataSouceFile.getColumnData(FileUploadFields.ServerIP.getName());
		
		FtpConfig.FtpModel ftpModel = new FtpModel();
		switch (ConfigWeb.host) {
		case "http://192.168.1.231:7004/acs":
			ftpModel.ftpType = "sftp";
			ftpModel.host = "192.168.1.231";
			ftpModel.password = "acs2";
			ftpModel.port = 22;
			ftpModel.username = "acs2";
			break;
		case "http://192.168.1.17:7001/acs":
			ftpModel.ftpType = "sftp";
			ftpModel.host = "192.168.1.17";
			ftpModel.password = "acs2";
			ftpModel.port = 22;
			ftpModel.username = "acs2";
			break;
		default:
			throw new NoThisFtpServerException("暂时没有 "+ ConfigWeb.host+" ftp服务器的信息！");
		}
		
		CommonFtp ftp  = null;
		try {
			ftp = FtpConfig.connect(ftpModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp;
	}
	
	/**
	 * 读取excel驱动数据，上传文件
	 */
	public void uploadFiles(){
		ArrayList<String> ftpPathList = _dataSouceFile.getColumnData(FileUploadFields.FtpFilePath.getName());
		ArrayList<String> localPathList = _dataSouceFile.getColumnData(FileUploadFields.LocalFilePath.getName());
		ArrayList<String> dateList = _dataSouceFile.getColumnData(FileUploadFields.FileDate.getName());
		ArrayList<String> ifExecList = _dataSouceFile.getColumnData(FileUploadFields.IfExecute.getName());
		if(ftpPathList.size() == localPathList.size() && ftpPathList.size() == dateList.size()){
			for(int i=0;i<ftpPathList.size();i++){
				if(ifExecList.get(i).equals("是")){
					String realFtpPath = getPath(ConfigWeb.ftpPath, ftpPathList.get(i)); 
					if(this.uploadFile(realFtpPath,localPathList.get(i),dateList.get(i))){
						logger.info("文件 : " + localPathList.get(i) + " 上传成功！ \n  "
								+ "上传目录 ： " + ftpPathList.get(i) + dateList.get(i));
					}else{
						logger.error("文件上传失败！  "  + localPathList.get(i));
					}
				}
			}
		}else {
			logger.error("excel驱动数据各列的行数不相等！");
		}
	}

	private boolean uploadFile(String ftpFilePath,String localFilePath,String date) {
		
		//String realFtpPath = ftpFilePath +"/"+ date;
		ftpFilePath = ftpFilePath.endsWith("/") ? ftpFilePath : ftpFilePath+"/";
		try {
			List<String> dirList = _ftp.listFileInDir(ftpFilePath);
			if(!dirList.contains(date)){
				if(_ftp.createDirectory(ftpFilePath+date)){
					logger.info("ftp目录 ： "+ftpFilePath+date+ " 创建成功");
				}
			}
			localFilePath = localFilePath.replace("\\", "/");
			File localFile = new File(localFilePath);
			InputStream is = new FileInputStream(localFile);
			if(_ftp.uploadFile(is, ftpFilePath+date+"/"+localFile.getName())){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getPath(String path1,String path2){
		if(!path1.startsWith("/")){
			path1 = "/" + path1;
		}
		if(!path1.endsWith("/")){
			path1 = path1 + "/";
		}
		if(path2.startsWith("/")){
			path2 = path2.substring(1);
		}
		if(!path2.endsWith("/")){
			path2 = path2+"/";
		}
		return path1+path2;
			
	}
}




