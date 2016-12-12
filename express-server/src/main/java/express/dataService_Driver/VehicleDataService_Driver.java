//package express.dataService_Driver;
//
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//import express.dataService.vehicleAndDriverDataService.VehicleDataService;
//import express.po.VehicleInfoPO;
//
//public class VehicleDataService_Driver {
//	public void drive(VehicleDataService vehicleservice){
//		boolean create;
//		try {
//			create = vehicleservice.createVehicleInfo(new VehicleInfoPO(null, null, false));
//			if (create){
//				System.out.println("VehicleInfo created");
//			}
//			
//			VehicleInfoPO vehicleInfoPO=new VehicleInfoPO(null, null, false);
//			vehicleInfoPO=vehicleservice.getVehicleInfo(1);
//			System.out.println("VehicleInfo got");
//			
//			ArrayList<VehicleInfoPO> List=new ArrayList<VehicleInfoPO>();
//			List=vehicleservice.getVehicleInfoList();
//			System.out.println("VehicleInfoList got");
//			
//			vehicleservice.deleteVehicleInfo(1);
//				System.out.println("VehicleInfo deleted");
//				
//			boolean change;
//			change=vehicleservice.changeVehicleInfoPO(new VehicleInfoPO(null, null, false), 0);
//			if (change){
//				System.out.println("VehicleInfo changed");
//			}
//
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//	}
//}
