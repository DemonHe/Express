package express.businessLogic.documentBL;

import java.util.ArrayList;

import express.businesslogicService.transcenterSaleBLService.TransCenterSaleShipmentDocblService;
import express.po.ShipmentDocTransCenterPO;
import express.vo.ShipmentDocTransCenterVO;

public class ShipmentDocController implements TransCenterSaleShipmentDocblService{
	ShipmentDocTransCenter shipmentdoc=new ShipmentDocTransCenter();
	ShipmentDocCheck shipmentdocchecker= new ShipmentDocCheck();
	
			public boolean addShipmentDoc(ShipmentDocTransCenterVO vo){
					if(isTransIDavailable(vo.getTransId())){
						return shipmentdoc.addShipmentDoc(vo);
						
					}
					else {
						return false;
					}	
			}
	
			
			
			public ShipmentDocTransCenterVO getShipmentDoc(String shipmentID){
				
				return shipmentdoc.getShipmentDoc(shipmentID);
			}
			
			public void endShipmentDoc(){
				shipmentdoc.endShipmentDoc();
			}

			public boolean isTransIDavailable(String transid) {
				return shipmentdocchecker.isTransIDavailable(transid);
			}



			@Override
			public ArrayList<ShipmentDocTransCenterVO> getAllShipmentDoc() {
				return shipmentdoc.getAllShipmentDoc();
			}



			@Override
			public String getShipmentDocID() {
				return shipmentdoc.getShipmentDocID();
			}



			@Override
			public double getShipmentfee(ShipmentDocTransCenterVO vo) {
				return shipmentdoc.getShipmentfee(vo);
			}
			public ArrayList<ShipmentDocTransCenterPO>  getAllShipmentDocPO(){
				return shipmentdoc.getAllShipmentDocPO();
			}
}
