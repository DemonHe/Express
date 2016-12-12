//package express.dataService_Driver;
//
//import java.rmi.RemoteException;
//
//import express.dataService.statisticsDataService.OperateFormDataService;
//import express.dataService.statisticsDataService.ProfitFormDataService;
//
//
//public class StatisticsDataService_Driver {
//
//	public void drive(ProfitFormDataService profitFormDataService,OperateFormDataService operateFormDataService){
//		try {
//			if(operateFormDataService.createOperateForm(null))
//				System.out.println("Create operate form");
//			if(profitFormDataService.createProfitForm(null))
//				System.out.println("Create profit form");
//			operateFormDataService.getOperateForm(0);
//			operateFormDataService.getOperateFormList();
//			profitFormDataService.getProfitForm(0);
//			profitFormDataService.getProfitFormList();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//}
