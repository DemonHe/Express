package express.businesslogicService.transcenterSaleBLService;

import java.util.ArrayList;

import express.po.ShipmentDocTransCenterPO;
import express.vo.ShipmentDocTransCenterVO;

public interface TransCenterSaleShipmentDocblService {
	public boolean addShipmentDoc(ShipmentDocTransCenterVO vo);
	public ShipmentDocTransCenterVO getShipmentDoc(String shipmentID) ;
	public boolean isTransIDavailable(String transid);
	public ArrayList<ShipmentDocTransCenterVO>  getAllShipmentDoc();
	public ArrayList<ShipmentDocTransCenterPO>  getAllShipmentDocPO();
	public String getShipmentDocID();
	public double getShipmentfee(ShipmentDocTransCenterVO vo) ;
	public void endShipmentDoc();
}
