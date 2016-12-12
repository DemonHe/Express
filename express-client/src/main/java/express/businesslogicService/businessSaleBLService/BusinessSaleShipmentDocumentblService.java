package express.businesslogicService.businessSaleBLService;

import java.util.ArrayList;

import express.po.ShipmentDocBusinessHallPO;
import express.vo.ShipmentDocBusinessHallVO;

public interface BusinessSaleShipmentDocumentblService {
	public boolean addShipmentDoc(ShipmentDocBusinessHallVO vo);
	public ShipmentDocBusinessHallVO getShipmentDoc(String shipmentID) ;
	public double getShipmentfee(ShipmentDocBusinessHallVO vo);
	public ArrayList<ShipmentDocBusinessHallPO>  getAllShipmentDoc();
	public String getShipmentDocID();
	public void endShipmentDoc();
}
