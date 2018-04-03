package yss.acs.ui.test.datasource;

import java.util.ArrayList;
import java.util.List;

import myutils.common.ExcelAdapter;
import yss.acs.ui.base.DataFile;

public class DataSouceFileXls extends DataFile {
	
	private ExcelAdapter ea;
	protected String _xlsFilePath;
	protected String _sheetName;
	
	public DataSouceFileXls(String xlsFilePath,String sheetName){
		_xlsFilePath = xlsFilePath;
		_sheetName = sheetName;
		ea = new ExcelAdapter(_xlsFilePath, _sheetName);
	}
	
	public  ArrayList<List<String>>  getSourceData(){
		 ArrayList<List<String>> sheetData =  ea.getSheetData();
		return sheetData;
	}
	
	public ArrayList<String> getColumnData(String headerName){
		return ea.getColData(headerName);
	}
}
