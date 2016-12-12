//package express.businessLogicService_Driver;
//
//import express.businesslogicService.businessSaleBLService.DriverBusinessSaleblService;
//import express.vo.DriverInfoListVO;
//import express.vo.DriverInfoVO;
//
//public class DriverBusinessSaleblService_Driver {
//	public void drive(DriverBusinessSaleblService driverservice){
//		if (driverservice.isIdentityIdAvailable("Lu Hailong")){
//			driverservice.addDriverInfo(new DriverInfoVO());
//			System.out.println("DriverInfo added");
//		}
//		boolean remove=driverservice.removeDriverInfo("Lu Hailong");
//		System.out.println("DriverInfo removed");
//		
//		boolean change=driverservice.changeDriverInfo("Lu Hailong");
//		System.out.println("DriverInfo changed");
//		
//		DriverInfoListVO driverInfoListVO=new DriverInfoListVO();
//		driverInfoListVO=driverservice.getDriverInfoList();
//		System.out.println("DriverInfoList got");
//		
//		DriverInfoVO driverInfoVO=new DriverInfoVO();
//		driverInfoVO=driverservice.getDriverInfo("Lu Hailong");
//		System.out.println("DriverInfo got");
//		
//		boolean fullfilled=driverservice.isDocFullfilled();
//		if (fullfilled){
//			System.out.println("Driver Info Fullfilled");
//			
//			
//		}
//		driverservice.endManage();
//		System.out.println("end inputing");
//		
//	}
//}
