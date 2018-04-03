package yss.acs.ui.config;

public class MappingPageNameToClassName {
	
	/**
	 * 将页面名称(PO中的sheet页名称)映射为对应的页面类名
	 * @param pageName
	 * @return
	 */
	public static String getClassNameFromPageName(String pageName){
		
		String className = "";
		switch (pageName) {
		case "首页":
			className = "yss.acs.ui.pages.PageHome";
			break;
		case "数据读取页":
			className = "yss.acs.ui.pages.PageDataImport";
			break;
		case "登录页":
			className = "yss.acs.ui.pages.PageLogin";
			break;
		case "核算交易流水页":
			className = "yss.acs.ui.pages.PageAccountingFlow";
			break;
		case "生成凭证页":
			className = "yss.acs.ui.pages.PageVoucherProduce";
			break;
		case "凭证维护页":
			className = "yss.acs.ui.pages.PageVoucherMaintaince";
			break;
		case "核算交易流水":
			className = "yss.acs.ui.pages.PageDataChange";
			break;
		case "场内证券流水":
			className = "yss.acs.ui.pages.PageChangNeiZhengQuan";
			break;
		case "交易单元页":
			className = "yss.acs.ui.pages.PageJiyiDanyuan";
			break;
		case "公共债券页":
			className = "yss.acs.ui.pages.PagePublicBonds";
			break;
		case "产品债券页":
			className = "yss.acs.ui.pages.PageProductBonds";
			break;
		case "回购交易页":
			className = "yss.acs.ui.pages.PageBuyBackDeal";
			break;
		case "产品基本信息页":
			className = "yss.acs.ui.pages.PageProductBasic";
			break;
		case "产品扩展信息页":
			className = "yss.acs.ui.pages.PageProductExtends";
			break;
		case "资金账户页":
			className = "yss.acs.ui.pages.PageCapitalAccount";
			break;
		case "产品交易单元":
			className = "yss.acs.ui.pages.PageProductDealUitl";
			break;
		case "公共元素":
			className = "yss.acs.ui.pages.PagePublicElements";
			break;
		case "账户利率信息新增页":
			className = "yss.acs.ui.pages.PageAccIntRateInf";
			break;
		case "一级清算信息管理页":
			className = "yss.acs.ui.pages.PagePriClearInfManagement";
			break;
		case "友好账户信息管理页":
			className = "yss.acs.ui.pages.PageFrienAccountInf";
			break;
		case "证券账户信息管理":
			className = "yss.acs.ui.pages.PageSecurAccoInfManagement";
			break;
		case "业务方案管理":
			className = "yss.acs.ui.pages.PageBusiProManag";
			break;
		case "资产估值表":
			className = "yss.acs.ui.pages.PageAssetValuTable";
			break;
		case "场外债券页":
			className = "yss.acs.ui.pages.PageChangHui";
			break;
		case "开放式基金业务页":
			className = "yss.acs.ui.pages.PageOpenBusinessManage";
			break;
		case "证券流通信息管理页":
			className = "yss.acs.ui.pages.PageZhengQuanLiuTong";
			break;
		case "证券转换业务信息管理页":
			className = "yss.acs.ui.pages.PageZhengQuanZhuanHuan";
			break;
		case "核算模板方案页":
			className = "yss.acs.ui.pages.PageAccountStencilScheme";
			break;
		case "产品关联业务方案管理":
			className = "yss.acs.ui.pages.PageProductAssor";
			break;
		case "科目方案管理":
			className = "yss.acs.ui.pages.PageSubjectPro";
			break;
		case "公共证券估值方案管理":
			className = "yss.acs.ui.pages.PagePublicZhengquanGuzhi";
			break;
		case "产品证券估值方案管理":
			className = "yss.acs.ui.pages.PageChanPinZhengQuanGuZhi";
			break;
		case "资产余额表":
			className = "yss.acs.ui.pages.PageZiChan";
			break;
		case "核算处理":
			className = "yss.acs.ui.pages.PageHeSuanChuLi";
			break;
		case "凭证维护":
			className = "yss.acs.ui.pages.PagePingZhengWeiHu";
			break;
		case "日记账簿信息":
			className = "yss.acs.ui.pages.PageRiJiZhangBuXinXi";
			break;
		case "核算账务锁定":
			className = "yss.acs.ui.pages.PageHeSuanZhangWuSuoDing";
			break;
		case "产品读数页":
			className = "yss.acs.ui.pages.PageProductDataImport";
			break;
		case "产品计息信息管理":
			className = "yss.acs.ui.pages.PageProductControlinfo";
			break;
		default:
			break;
		}
		
		return className;
		
	}

}
