//package express.dataService_Driver;
//
//import express.data.stub.DeliverCreateOrderDataService_stub;
//import express.dataService.documentDataService.DeliverCreateOrderDataService;
//import express.po.DeliveryType;
//import express.po.GoodTransStatusPO;
//import express.po.OrderPO;
//
//public class DeliverCreateOrderDataService_Driver {
//		public void drive(DeliverCreateOrderDataService deliverCreateOrderDataService){
//			
//			
//			
//			OrderPO order=new OrderPO();
//			//deliverCreateOrderDataService
//			order.setSenderInfo("luhailong","Nanjing",
//					"NJU","15651705115",
//					"12345678");
//			order.setReceiverInfo("hailongwu","Peking",
//					"PKU","12345678901",
//					"87654321");
//			order.setGoodsInfo(3,20.8,8,"Laptop",DeliveryType.Fast);
//			
//			
//			GoodTransStatusPO goodstate=new GoodTransStatusPO();
//			
//			OrderPO orderget=new OrderPO();
//			
//			boolean isCreated=deliverCreateOrderDataService.createOrder(order);
//			boolean isChanged=deliverCreateOrderDataService.changeGoodsState(goodstate);
//			
//			
//			
//			orderget=deliverCreateOrderDataService.getOrder("0000000001");
//			
//			
//			
//			
//			
//			
//			
//		}
//	
//	
//	
//}
