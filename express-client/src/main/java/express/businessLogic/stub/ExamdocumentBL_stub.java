//package express.businessLogic.stub;
//
//import express.businesslogicService.managerBLService.ExamDocumentBLService;
//import express.vo.DocumentListVO;
//import express.vo.LogVO;
//
//public class ExamdocumentBL_stub implements ExamDocumentBLService{
//	DocumentListVO dlvo;
//	
//	public ExamdocumentBL_stub(DocumentListVO dl){
//		dlvo = dl;
//	}
//	
//	public DocumentListVO getDocumentList(){
//		return dlvo;	
//	}
//	
//	public boolean changeDocumentInfo(int index){
//		System.out.println("Change succeed.");
//		return true;	
//	}
//	
//	public boolean passDocument (int index){
//		System.out.println("Pass succeed.");
//		return true;
//	}
//	
//	public void endJudge(){
//		LogVO lp = new LogVO("2015年10月25日15点23分","审批单据");
//	}
//}
