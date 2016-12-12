package express.businesslogicService.businessSaleBLService;

import express.vo.ArrivalDocBusinessHallVO;


public interface BusinessSaleArrivalDocumentblService {

	public boolean addArrivalDoc(ArrivalDocBusinessHallVO vo);
	public ArrivalDocBusinessHallVO getArrivalDoc(String OrderID);
	public void endArrivalDoc();
	public String getTransferDocID(String orderID);
	public String getDeparture(String orderID) ;
	
	
}
