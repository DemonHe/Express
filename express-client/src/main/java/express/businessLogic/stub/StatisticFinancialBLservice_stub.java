//package express.businessLogic.stub;
//
//import express.businesslogicService.financialBLService.StatisticFinancialBLService;
//import express.vo.DateAvailableVO;
//import express.vo.OperateFormListVO;
//import express.vo.OperateFormVO;
//import express.vo.ProfitFormListVO;
//import express.vo.ProfitFormVO;
//
//public class StatisticFinancialBLservice_stub implements StatisticFinancialBLService{
//
//	ProfitFormVO profitForm;
//	ProfitFormListVO profitFormList;
//	OperateFormVO operateForm;
//	OperateFormListVO operateFormList;
//	
//	public StatisticFinancialBLservice_stub(ProfitFormVO profitForm,ProfitFormListVO profitFormList,
//			OperateFormVO operateForm,OperateFormListVO operateFormList){
//		this.profitForm=profitForm;
//		this.profitFormList=profitFormList;
//		this.operateForm=operateForm;
//		this.operateFormList=operateFormList;
//
//	}
//	
//	public ProfitFormVO addProfitForm(){
//		System.out.println("Add operate form");
//		return profitForm;
//	}
//	
//	public OperateFormVO addOperateForm(String beginDate,String endDate){
//		if(checkDateAvailable(beginDate,endDate)==DateAvailableVO.Found){
//			System.out.println("Add operate form");
//			return operateForm;
//		}	
//		else{
//			System.out.println("Date invalid");
//			return null;
//		}
//	}
//	
//	public DateAvailableVO checkDateAvailable(String beginDate,String endDate){
//		if(beginDate.compareTo(endDate)<=0)
//			return DateAvailableVO.Found;
//		else
//			return DateAvailableVO.NotFound;
//		
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
