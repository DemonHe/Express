//package express.businessLogic.stub;
//
//import express.vo.ArrivalTimeVO;
//import express.vo.OrderVO;
//
//public class DeliverCreateOrderBLService_stub {
//	public boolean addOrder(OrderVO vo){
//		System.out.println("adding Order");
//		return true;
//	}
//	public boolean isCellPhoneAvailable(String cellphonenumber){
//		if(cellphonenumber.equals("12345678901")){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
//	public double getTotal(){
//		return 9523.9523;
//	}
//	public OrderVO getOrder(String id){
//		OrderVO vo=new OrderVO();
//		if(id.equals("0000000001")){
//			return vo;
//		}
//		else{
//			return null;
//		}
//	}
//	public ArrivalTimeVO getPredictArrivalTime(){
//		ArrivalTimeVO vo =new ArrivalTimeVO();
//		vo.time="2015-01-01";
//		return vo;
//	}
//	public void endOrder(){
//		System.out.println("ending.....");
//	}
//}
