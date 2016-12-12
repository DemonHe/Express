package express.businessLogic.documentBL;

import java.util.ArrayList;

import express.businesslogicService.delivermanBLService.DeliverCreateOrderBLService;
import express.po.OrderPO;
import express.vo.ArrivalTimeVO;
import express.vo.OrderVO;

public class OrderController implements DeliverCreateOrderBLService{
	Order order=new Order();
	CheckOrder checker=new CheckOrder();
	public String addOrder(OrderVO vo){
		
			return order.addOrder(vo);
	
	}
	
	
	public boolean isCellPhoneAvailable(String cellphonenumber){
		return order.isCellPhoneAvailable(cellphonenumber);
	}
	
	
	
	public double getTotal(OrderVO vo){
		return order.getTotal(vo);
	}
	
	
	
	public OrderVO getOrder(String id){
		if(checker.isOrderIDAvailable(id)){
			return order.getOrder(id);
		}
		else {
			return null;
		}
		
	}
	public ArrivalTimeVO getPredictArrivalTime(String startCity,String endCity){
		return order.getPredictArrivalTime(startCity,endCity);
	}
	public void endOrder(){
		order.end();
	}


	public ArrayList<OrderPO> getAllOrder() {
		return order.getAllOrder();	
	}


	
	
	
	
}
