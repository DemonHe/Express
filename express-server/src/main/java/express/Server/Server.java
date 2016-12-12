//package express.Server;
//
//import java.util.ArrayList;
//
//import express.data.stub.AdminUserDataService_stub;
//import express.data.stub.BankAccountDataService_stub;
//import express.data.stub.BusinessSaleArrivalDocumentDataService_stub;
//import express.data.stub.BusinessSaleDeliverDocumentDataService_stub;
//import express.data.stub.BusinessSaleReceiveDocumentDataService_stub;
//import express.data.stub.BusinessSaleShipmentDocumentDataService_stub;
//import express.data.stub.DriverDataService_stub;
//import express.data.stub.ExamdocumentDataService_stub;
//import express.data.stub.InnerAccountDataService_stub;
//import express.data.stub.LogDataService_stub;
//import express.data.stub.OrganizationDataService_stub;
//import express.data.stub.PaymentDataService_stub;
//import express.data.stub.RepoDataService_stub;
//import express.data.stub.StatisticsDataService_stub;
//import express.data.stub.StrategyDataService_stub;
//import express.data.stub.VehicleDataService_stub;
//import express.dataService.bankAccountDataService.BankAccountDataService;
//import express.dataService.documentDataService.BusinessSaleArrivalDocumentDataService;
//import express.dataService.documentDataService.BusinessSaleDeliverDocumentDataService;
//import express.dataService.documentDataService.BusinessSaleReceiveDocumentDataService;
//import express.dataService.documentDataService.BusinessSaleShipmentDocumentDataService;
//import express.dataService.documentDataService.ExamdocumentDataService;
//import express.dataService.documentDataService.PaymentDocDataService;
//import express.dataService.innerAccountDataService.InnerAccountDataService;
//import express.dataService.logDataService.LogDataService;
//import express.dataService.organizationDataService.OrganizationDataService;
//import express.dataService.repoDataService.RepoDataService;
//import express.dataService.statisticsDataService.OperateFormDataService;
//import express.dataService.statisticsDataService.ProfitFormDataService;
//import express.dataService.userDataService.AdminUserDataService;
//import express.dataService.vehicleAndDriverDataService.DriverDataService;
//import express.dataService.vehicleAndDriverDataService.VehicleDataService;
//import express.dataService_Driver.AdminUserDataService_Driver;
//import express.dataService_Driver.BankAccountDataService_Driver;
//import express.dataService_Driver.BusinessSaleArrivalDocumentDataService_Driver;
//import express.dataService_Driver.BusinessSaleDeliverDocumentDataService_Driver;
//import express.dataService_Driver.BusinessSaleReceiveDocumentDataService_Driver;
//import express.dataService_Driver.BusinessSaleShipmentDocumentDataService_Driver;
//import express.dataService_Driver.DriverDataService_Driver;
//import express.dataService_Driver.ExamdocumentDataService_Driver;
//import express.dataService_Driver.InnerAccountDataService_Driver;
//import express.dataService_Driver.LogDataService_Driver;
//import express.dataService_Driver.OrganizationDataService_Driver;
//import express.dataService_Driver.PaymentDataService_Driver;
//import express.dataService_Driver.RepoDataService_Driver;
//import express.dataService_Driver.StatisticsDataService_Driver;
//import express.dataService_Driver.VehicleDataService_Driver;
//import express.po.DocumentPO;
//import express.po.LogPO;
//
//public class Server {
//	public static void main(String[] args) {
//		AdminUserDataService admindataservice = new AdminUserDataService_stub();
//		AdminUserDataService_Driver admindriver = new AdminUserDataService_Driver();
//		admindriver.drive(admindataservice);
//		
//		ArrayList<DocumentPO> dpo = new ArrayList<DocumentPO>();
//		ExamdocumentDataService examservice = new ExamdocumentDataService_stub(dpo);
//		ExamdocumentDataService_Driver examdriver = new ExamdocumentDataService_Driver();
//		examdriver.drive(examservice);
//		
//		ArrayList<LogPO> logpo = new ArrayList<LogPO>();
//		LogDataService logservice = new LogDataService_stub(logpo);
//		LogDataService_Driver logdriver = new LogDataService_Driver();
//		logdriver.drive(logservice);
//		
//		OrganizationDataService orgservice = new OrganizationDataService_stub();
//		OrganizationDataService_Driver orgdriver = new OrganizationDataService_Driver();
//		orgdriver.drive(orgservice);
//		
//		BankAccountDataService bankAccountDataService=new BankAccountDataService_stub();
//		BankAccountDataService_Driver bankAccount=new BankAccountDataService_Driver();
//		bankAccount.drive(bankAccountDataService);
//		
//		InnerAccountDataService innerAccountDataService=new InnerAccountDataService_stub(null,null,null,null,null,null);
//		InnerAccountDataService_Driver innerAccount=new InnerAccountDataService_Driver();
//		innerAccount.drive(innerAccountDataService);
//		
//		PaymentDocDataService paymentDataService=new PaymentDataService_stub(null);
//		PaymentDataService_Driver payment=new PaymentDataService_Driver();
//		payment.drive(paymentDataService);
//		
//		RepoDataService repoDataService=new RepoDataService_stub(null);
//		RepoDataService_Driver repoData=new RepoDataService_Driver();
//		repoData.drive(repoDataService);
//		
//		ProfitFormDataService profitFormDataService=new StatisticsDataService_stub(null, null, null, null);
//		OperateFormDataService operateFormDataService=new StatisticsDataService_stub(null, null, null, null);
//		StatisticsDataService_Driver statisticsData=new StatisticsDataService_Driver();
//		statisticsData.drive(profitFormDataService,operateFormDataService);
//		
//		
//		//Lu Hailong:
//		BusinessSaleArrivalDocumentDataService  arrivalDocservice=new BusinessSaleArrivalDocumentDataService_stub("","","","",null);
//		BusinessSaleArrivalDocumentDataService_Driver arrivalDocdriver=new BusinessSaleArrivalDocumentDataService_Driver();
//		arrivalDocdriver.drive(arrivalDocservice);
//		
//		BusinessSaleDeliverDocumentDataService deliverDocservice=new BusinessSaleDeliverDocumentDataService_stub(null, null, null);
//		BusinessSaleDeliverDocumentDataService_Driver deliverDocdriver=new BusinessSaleDeliverDocumentDataService_Driver();
//	    deliverDocdriver.drive(deliverDocservice);
//	    
//	    BusinessSaleReceiveDocumentDataService receiveDocservice=new BusinessSaleReceiveDocumentDataService_stub(null, 0, null, null);
//	    BusinessSaleReceiveDocumentDataService_Driver receiveDocdriver=new BusinessSaleReceiveDocumentDataService_Driver();
//	    receiveDocdriver.drive(receiveDocservice);
//	    
//	    BusinessSaleShipmentDocumentDataService shipmentDocservice=new BusinessSaleShipmentDocumentDataService_stub(null, null, null, null, null, null, null, null, 0);
//	    BusinessSaleShipmentDocumentDataService_Driver shipmentDocdriver =new BusinessSaleShipmentDocumentDataService_Driver();
//	    shipmentDocdriver.drive(shipmentDocservice);
//	    
//	    DriverDataService driverservice=new DriverDataService_stub(null, null, null, null, null, null, null, null, false, 0);
//	    DriverDataService_Driver driverdriver=new DriverDataService_Driver();
//	    driverdriver.drive(driverservice);
//	    
//	    VehicleDataService vehicleservice =new VehicleDataService_stub(null, null);
//	    VehicleDataService_Driver  vehicledriver=new VehicleDataService_Driver();
//        vehicledriver.drive(vehicleservice); 	    
//	    
//
//	
//        
//        
//		
//	}
//
//}
