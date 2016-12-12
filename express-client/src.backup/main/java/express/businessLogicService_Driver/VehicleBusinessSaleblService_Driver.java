//package express.businessLogicService_Driver;
//
//import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
//import express.po.VehicleInfoPO;
//import express.vo.DriverInfoListVO;
//import express.vo.DriverInfoVO;
//import express.vo.VehicleInfoListVO;
//import express.vo.VehicleInfoVO;
//
//public class VehicleBusinessSaleblService_Driver {
//	public void drive(VehicleBusinessSaleblService vehicleservice){
//
//		boolean add=vehicleservice.addVehicleInfo(new VehicleInfoVO());
//		System.out.println("VehicleInfo addd");
//		
//
//		
//		boolean remove=vehicleservice.removeVehicleInfo("Lu Hailong");
//		System.out.println("VehicleInfo removed");
//		
//		boolean change=vehicleservice.changeVehicleInfo("Lu Hailong");
//		System.out.println("VehicleInfo changed");
//		
//
//		
//		VehicleInfoListVO VehicleInfoListVO=new VehicleInfoListVO();
//		VehicleInfoListVO=vehicleservice.getVehicleInfoList();
//		System.out.println("VehicleInfoList got");
//		
//		VehicleInfoVO VehicleInfoVO=new VehicleInfoVO();
//		VehicleInfoVO=vehicleservice.getVehicleInfo("Lu Hailong");
//		System.out.println("VehicleInfo got");
//		
//		boolean fullfilled=vehicleservice.isDocFullfilled();
//		if (fullfilled){
//			System.out.println("Vehicle Info Fullfilled");
//			
//			
//		}
//		
//		vehicleservice.endManage();
//		System.out.println("end inputing");
//		
//
//
//		
//	}
//}
