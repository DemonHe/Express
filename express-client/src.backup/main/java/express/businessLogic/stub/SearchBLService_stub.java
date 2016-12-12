package express.businessLogic.stub;

import express.vo.GoodTransStatusVO;

public class SearchBLService_stub {
	public GoodTransStatusVO getOrderIDStatus(String orderID){
		if(orderID.equals("0000000001")){
			System.out.println("Order status is get!");
		}
		else{
			System.out.println("NO SUCH ID");
		}
		return null;
	}
	public boolean isOrderIDAvailable(String id){
		if(id.equals("0000000001")){
			return true;
		}
		else{
			return false;
		}
	}
}
