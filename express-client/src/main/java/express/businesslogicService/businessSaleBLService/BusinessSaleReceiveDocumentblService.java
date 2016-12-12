package express.businesslogicService.businessSaleBLService;

import java.util.ArrayList;

import express.vo.ReceiveDocVO;

public interface BusinessSaleReceiveDocumentblService {
	
	public boolean addReceiveDoc(ReceiveDocVO vo);
	
	public ReceiveDocVO getReceiveDoc(String date,String delivermanID);
	
	public boolean isDateAvailable(String date);
	
	public void endReceiveDoc();
	
	public double getTotalPrice(ArrayList<String> orderlist);
}
