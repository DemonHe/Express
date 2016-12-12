//package express.businessLogic.stub;
//
//import express.businesslogicService.financialBLService.StatisticManagerBLService;
//import express.vo.OperateFormListVO;
//import express.vo.OperateFormVO;
//import express.vo.ProfitFormListVO;
//import express.vo.ProfitFormVO;
//
//public class StatisticManagerBLService_stub implements StatisticManagerBLService{
//
//	ProfitFormVO profitForm;
//	ProfitFormListVO profitFormList;
//	OperateFormVO operateForm;
//	OperateFormListVO operateFormList;
//	
//	public StatisticManagerBLService_stub(ProfitFormVO profitForm,ProfitFormListVO profitFormList,
//			OperateFormVO operateForm,OperateFormListVO operateFormList){
//		this.profitForm=profitForm;
//		this.profitFormList=profitFormList;
//		this.operateForm=operateForm;
//		this.operateFormList=operateFormList;
//	}
//	
//	public ProfitFormListVO getProfitFormList(){
//		System.out.println("Get profit form list");
//		return profitFormList;
//	}
//	
//	public OperateFormListVO getOperateFormList(){
//		System.out.println("Get operate form list");
//		return operateFormList;
//	}
//	
//	public ProfitFormVO getProfitForm(int index){
//		System.out.println("Get profit form");
//		return profitForm;
//	}
//	
//	public OperateFormVO getOperateForm(int index){
//		System.out.println("Get operate form");
//		return operateForm;
//	}
//}
