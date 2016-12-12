//package express.Client;
//
//import java.util.ArrayList;
//
//import express.businessLogic.stub.AdjustRepoBLService_stub;
//import express.businessLogic.stub.AdminBLService_stub;
//import express.businessLogic.stub.BankAccountBLService_stub;
//import express.businessLogic.stub.BusinessSaleArrivalDocumentblService_stub;
//import express.businessLogic.stub.BusinessSaleDeliverDocumentblService_stub;
//import express.businessLogic.stub.BusinessSaleReceiveDocumentblService_stub;
//import express.businessLogic.stub.BusinessSaleShipmentDocumentblService_stub;
//import express.businessLogic.stub.DriverBusinessSaleblService_stub;
//import express.businessLogic.stub.ExamdocumentBL_stub;
//import express.businessLogic.stub.InnerAccountBLService_stub;
//import express.businessLogic.stub.InventoryRepoBLService_stub;
//import express.businessLogic.stub.PaymentBLService_stub;
//import express.businessLogic.stub.ScanRepoBLService_stub;
//import express.businessLogic.stub.StatisticFinancialBLservice_stub;
//import express.businessLogic.stub.StatisticManagerBLService_stub;
//import express.businessLogic.stub.SysLogBLService_stub;
//import express.businessLogic.stub.VehicleBusinessSaleblService_stub;
//import express.businessLogicService_Driver.AdjustRepoBLService_Driver;
//import express.businessLogicService_Driver.AdminBLService_Driver;
//import express.businessLogicService_Driver.BankAccountBLService_Driver;
//import express.businessLogicService_Driver.BusinessSaleArrivalDocumentblService_Driver;
//import express.businessLogicService_Driver.BusinessSaleDeliverDocumentblService_Driver;
//import express.businessLogicService_Driver.BusinessSaleReceiveDocumentblService_Driver;
//import express.businessLogicService_Driver.BusinessSaleShipmentDocumentblService_Driver;
//import express.businessLogicService_Driver.DriverBusinessSaleblService_Driver;
//import express.businessLogicService_Driver.ExamDocumentBLService_Driver;
//import express.businessLogicService_Driver.InnerAccountBLService_Driver;
//import express.businessLogicService_Driver.InventoryRepoBLService_Driver;
//import express.businessLogicService_Driver.PaymentBLService_Driver;
//import express.businessLogicService_Driver.ScanRepoBLService_Driver;
//import express.businessLogicService_Driver.StatisticFinancialBLService_Driver;
//import express.businessLogicService_Driver.StatisticManagerBLService_Driver;
//import express.businessLogicService_Driver.SyslogBLService_Driver;
//import express.businessLogicService_Driver.VehicleBusinessSaleblService_Driver;
//import express.businesslogicService.adminBLService.AdminBLService;
//import express.businesslogicService.businessSaleBLService.BusinessSaleArrivalDocumentblService;
//import express.businesslogicService.businessSaleBLService.BusinessSaleDeliverDocumentblService;
//import express.businesslogicService.businessSaleBLService.BusinessSaleReceiveDocumentblService;
//import express.businesslogicService.businessSaleBLService.BusinessSaleShipmentDocumentblService;
//import express.businesslogicService.businessSaleBLService.DriverBusinessSaleblService;
//import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
//import express.businesslogicService.financialBLService.BankAccountBLService;
//import express.businesslogicService.financialBLService.InnerAccountBLService;
//import express.businesslogicService.financialBLService.PaymentBLService;
//import express.businesslogicService.financialBLService.StatisticFinancialBLService;
//import express.businesslogicService.financialBLService.StatisticManagerBLService;
//import express.businesslogicService.managerBLService.ExamDocumentBLService;
//import express.businesslogicService.managerBLService.SysLogBLService;
//import express.businesslogicService.transcenterRepoBLService.AdjustRepoBLService;
//import express.businesslogicService.transcenterRepoBLService.InventoryRepoBLService;
//import express.businesslogicService.transcenterRepoBLService.ScanRepoBLService;
//import express.po.UserRole;
//import express.vo.DocumentListVO;
//import express.vo.LogVO;
//
//public class Client_Driver {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		AdminBLService adminservice = new AdminBLService_stub("卢海龙","1001001",UserRole.DeliverMan,"123456");
//		AdminBLService_Driver admindriver = new AdminBLService_Driver();
//		admindriver.drive(adminservice);
//		
//		ExamDocumentBLService examdocumentservice = new ExamdocumentBL_stub(new DocumentListVO());
//		ExamDocumentBLService_Driver examdriver = new ExamDocumentBLService_Driver();
//		examdriver.drive(examdocumentservice);
//		
//		
//		
//		ArrayList<LogVO> log = new ArrayList<LogVO>();
//		SysLogBLService sysloservice = new SysLogBLService_stub(log);
//		SyslogBLService_Driver syslodriver = new SyslogBLService_Driver();
//		syslodriver.drive(sysloservice);
//		//
//		AdjustRepoBLService adjustRepoBLService=new AdjustRepoBLService_stub();
//		AdjustRepoBLService_Driver adjustRepo=new AdjustRepoBLService_Driver();
//		adjustRepo.drive(adjustRepoBLService);
//		
//		BankAccountBLService bankAccountBLService=new BankAccountBLService_stub(null);
//		BankAccountBLService_Driver bankAccount=new BankAccountBLService_Driver();
//		bankAccount.drive(bankAccountBLService);
//		
//		InventoryRepoBLService inventoryRepoBLService=new InventoryRepoBLService_stub(null);
//		InventoryRepoBLService_Driver inventory=new InventoryRepoBLService_Driver();
//		inventory.drive(inventoryRepoBLService);
//		
//		PaymentBLService paymentBLService=new PaymentBLService_stub(null);
//		PaymentBLService_Driver payment=new PaymentBLService_Driver();
//		payment.drive(paymentBLService);
//		
//		ScanRepoBLService scanRepoBLService=new ScanRepoBLService_stub(null);
//		ScanRepoBLService_Driver scanRepo=new ScanRepoBLService_Driver();
//		scanRepo.drive(scanRepoBLService);
//		
//		StatisticFinancialBLService statisticFinancialBLservice=new StatisticFinancialBLservice_stub(null, null, null, null);
//		StatisticFinancialBLService_Driver statisticFinancial=new StatisticFinancialBLService_Driver();
//		statisticFinancial.drive(statisticFinancialBLservice);
//		
//		StatisticManagerBLService_stub statisticManagerBLService=new StatisticManagerBLService_stub(null,null,null,null);
//		StatisticManagerBLService_Driver statisticManager=new StatisticManagerBLService_Driver();
//		statisticManager.drive(statisticManagerBLService);
//		
//		InnerAccountBLService innerAccountBLService=new InnerAccountBLService_stub();
//		InnerAccountBLService_Driver innerAccountBL=new InnerAccountBLService_Driver();
//		innerAccountBL.drive(innerAccountBLService);
//	
//	    
//		//Lu Hailong
//		BusinessSaleArrivalDocumentblService  arrivalDocservice=new BusinessSaleArrivalDocumentblService_stub("","","","",null);
//		BusinessSaleArrivalDocumentblService_Driver arrivalDocdriver=new BusinessSaleArrivalDocumentblService_Driver();
//		arrivalDocdriver.drive(arrivalDocservice);
//		
//		BusinessSaleDeliverDocumentblService deliverDocservice=new BusinessSaleDeliverDocumentblService_stub(null, null, null);
//		BusinessSaleDeliverDocumentblService_Driver deliverDocdriver=new BusinessSaleDeliverDocumentblService_Driver();
//	    deliverDocdriver.drive(deliverDocservice);
//	    
//	    BusinessSaleReceiveDocumentblService receiveDocservice=new BusinessSaleReceiveDocumentblService_stub(null, 0, null, null);
//	    BusinessSaleReceiveDocumentblService_Driver receiveDocdriver=new BusinessSaleReceiveDocumentblService_Driver();
//	    receiveDocdriver.drive(receiveDocservice);
//	    
//	    BusinessSaleShipmentDocumentblService shipmentDocservice=new BusinessSaleShipmentDocumentblService_stub(null, null, null, null, null, null, null, null, 0);
//	    BusinessSaleShipmentDocumentblService_Driver shipmentDocdriver =new BusinessSaleShipmentDocumentblService_Driver();
//	    shipmentDocdriver.drive(shipmentDocservice);
//	    
//	    DriverBusinessSaleblService driverservice=new DriverBusinessSaleblService_stub(null, null, null);
//	    DriverBusinessSaleblService_Driver driverdriver=new DriverBusinessSaleblService_Driver();
//	    driverdriver.drive(driverservice);
//	    
//	    VehicleBusinessSaleblService vehicleservice =new VehicleBusinessSaleblService_stub(null, null);
//	    VehicleBusinessSaleblService_Driver  vehicledriver=new VehicleBusinessSaleblService_Driver();
//        vehicledriver.drive(vehicleservice); 	    
//	    
//	    
//	    
//	}
//
//}
