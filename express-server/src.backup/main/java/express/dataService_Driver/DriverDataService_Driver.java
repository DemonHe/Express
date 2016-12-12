//package express.dataService_Driver;
//
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//import express.dataService.vehicleAndDriverDataService.DriverDataService;
//import express.po.DriverInfoPO;
//
//public class DriverDataService_Driver {
//	public void drive(DriverDataService driverservice){
//		boolean create;
//		try {
//			create = driverservice.createDriverInfo(new DriverInfoPO(null, null, 
//					null, null, null, null, true, 0));
//			if (create){
//				System.out.println("DriverInfo created");
//			}
//			
//			DriverInfoPO driverInfoPO=new DriverInfoPO(null, null, null, null, null,
//					null, true, 0);
//			driverInfoPO=driverservice.getDriverInfo(1);
//			System.out.println("DriverInfo got");
//			
//			ArrayList<DriverInfoPO> List=new ArrayList<DriverInfoPO>();
//			List=driverservice.getDriverInfoList();
//			System.out.println("DriverInfoList got");
//			
//			boolean delete=driverservice.deleteDriverInfo(1);
//			if (delete){
//				System.out.println("DriverInfo deleted");
//			}
//			boolean change;
//			change=driverservice.changeDriverInfo(new DriverInfoPO(null, null, null, null, null, null, true, 0),0);
//			if (change){
//				System.out.println("DriverInfo changed");
//			}
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//}
