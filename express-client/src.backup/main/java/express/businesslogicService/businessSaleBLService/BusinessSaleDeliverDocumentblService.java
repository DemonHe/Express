package express.businesslogicService.businessSaleBLService;

import java.util.ArrayList;

import express.po.DeliverDocPO;
import express.vo.DeliverDocVO;

public interface BusinessSaleDeliverDocumentblService {
	public boolean addDeliverDoc(DeliverDocVO vo);
	public DeliverDocVO getDeliverDoc(String OrderID);
	public boolean isOrderIDavailable(String id);  
	public void endDeliverDoc();
	
}
