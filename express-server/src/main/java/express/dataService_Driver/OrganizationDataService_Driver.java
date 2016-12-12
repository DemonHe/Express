//package express.dataService_Driver;
//
//import java.rmi.RemoteException;
//
//import express.dataService.organizationDataService.OrganizationDataService;
//import express.po.OrganizationPO;
//
//public class OrganizationDataService_Driver {
//	public void drive(OrganizationDataService organizationdataservice){
//		try {
//			organizationdataservice.changeOrgInfo(new OrganizationPO("S快递公司","南京鼓楼"),0);
//			System.out.println("Change succeed.");
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
