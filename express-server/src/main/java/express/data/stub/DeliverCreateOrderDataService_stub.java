package express.data.stub;

import express.po.GoodTransStatusPO;
import express.po.OrderPO;

public class DeliverCreateOrderDataService_stub {
	public boolean createOrder(OrderPO po){
		if(po.getOrderID().equals("0000000001")){
			return true;
		}
		else{
			return false;
		}	
	}
	public boolean changeGoodsState(GoodTransStatusPO po){
		if(po.getOrderID().equals("0000000001")){
			return true;
		}
		else{
			return false;
		}
	}
	public OrderPO getOrder(String orderid){
		OrderPO x=new OrderPO();
		if(orderid.equals("0000000001")){
			return x;
		}
		else{
			return null;
		}
	}
}
