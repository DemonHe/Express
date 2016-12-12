package express.businesslogicService.businessSaleBLService;

import java.util.ArrayList;

import express.vo.DriverInfoVO;

public interface DriverBusinessSaleblService {
	
	public boolean addDriverInfo(DriverInfoVO vo);
	
	public boolean removeDriverInfo(String DriverID);
	
	public ArrayList<DriverInfoVO> getDriverInfoList(String orgID);
	
	public ArrayList<DriverInfoVO> getDriverInfoList();
	
	public DriverInfoVO getDriverInfo(String DriverID);
	
	public boolean changeDriverInfo(DriverInfoVO vo,String DriverID);
	
	public boolean isDriverIDAvailable(String driverID);
	
	public boolean isIdentityIdAvailable(String id);
	
	public void endManage();
}
