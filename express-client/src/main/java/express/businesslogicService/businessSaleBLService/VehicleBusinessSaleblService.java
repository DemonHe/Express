package express.businesslogicService.businessSaleBLService;

import java.util.ArrayList;

import express.po.VehicleInfoPO;
import express.vo.VehicleInfoVO;

public interface VehicleBusinessSaleblService {
	
	public boolean addVehicleInfo(VehicleInfoVO vo);
	
	public boolean removeVehicleInfo(String carID);
	
	public ArrayList<VehicleInfoVO> getVehicleInfoList();
	
	public ArrayList<VehicleInfoPO> getVehicleInfoListPO();
	
	public ArrayList<VehicleInfoVO> getVehicleInfoList(String orgID);
	
	public VehicleInfoVO getVehicleInfo(String carID);
	
	public boolean changeVehicleInfo(VehicleInfoVO vo,String carID);
	
	public boolean isCarIDAvailable(String carID);
	
	public boolean isCarLicenseAvailable(String license);
	
	public void recordVehicleInfo();
	
	public void endManage();
}
