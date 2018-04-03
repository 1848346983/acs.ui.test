package yss.acs.ui.model;

public class MTestcase {
	
	private String stepName; //步骤说明
	private String stepNo; //步骤编号
	private String ifExecute;
	private String pageName;
	private String elementName;
	private String testcaseName;
	private String operationType;
	private String params;
	private String exeCondition;
	
	
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getExeCondition() {
		return exeCondition;
	}
	public void setExeCondition(String exeCondition) {
		this.exeCondition = exeCondition;
	}

	public String getStepNo() {
		return stepNo;
	}
	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}
	public String getIfExecute() {
		return ifExecute;
	}
	public void setIfExecute(String ifExecute) {
		this.ifExecute = ifExecute;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getTestcaseName() {
		return testcaseName;
	}
	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
	@Override
	public String toString() {
		return "MTestcase [stepName=" + stepName + ", stepNo=" + stepNo + ", ifExecute=" + ifExecute + ", pageName=" + pageName + ", elementName="
				+ elementName + ", testcaseName=" + testcaseName + ", operationType=" + operationType + ", params=" + params + ", exeCondition="
				+ exeCondition + "]";
	}
	
	
}
