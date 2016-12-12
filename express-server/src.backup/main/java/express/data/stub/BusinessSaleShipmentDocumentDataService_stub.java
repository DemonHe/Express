package express.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.documentDataService.BusinessSaleShipmentDocumentDataService;
import express.po.ShipmentDocPO;


public class BusinessSaleShipmentDocumentDataService_stub implements BusinessSaleShipmentDocumentDataService {

	@Override
	public boolean createShipmentDoc(ShipmentDocPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShipmentDocPO getShipmentDoc(String OrderID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeAllShipmentDoc() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ShipmentDocPO> getShipmentDoclist() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
