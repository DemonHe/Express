package express.businessLogic.searchBL;

import express.businessLogic.documentBL.CheckOrder;
import express.businesslogicService.customerBLService.SearchBLService;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.po.GoodTransStatusPO;
import express.vo.GoodTransStatusVO;
import express.rmi.RMIClient;

public class Search implements SearchBLService{
	GoodsStatusDataService search;
	public Search () {
		search=RMIClient.getGoodStatusObject();
	}
	
	public GoodTransStatusVO getOrderIDStatus(String orderID){
		if(isOrderIDAvailable(orderID)){
			GoodTransStatusPO po=new GoodTransStatusPO();
			try{
			
				po=search.search(orderID);
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
			//System.out.print(po.getDeliverManID());
			
			GoodTransStatusVO vo= new GoodTransStatusVO();
			vo.setDeliverManID(po.getDeliverManID());
			vo.setFirstBusinessHallID(po.getFirstBusinessHallID());
			vo.setFirsttransCenterID(po.getFirsttransCenterID());
			vo.setOrderID(po.getOrderID());
			vo.setSecondBusinessHallID(po.getSecondBusinessHallID());
			vo.setSecondtransCenterID(po.getSecondtransCenterID());
			vo.setTime(po.getTime());
			vo.setStatus(po.getstatusList());
			return vo;
			
		}
		else{
			return null;
		}
		
		
		
	}
	private boolean isOrderIDAvailable(String id){
		CheckOrder checker=new CheckOrder();
		return checker.isOrderIDAvailable(id);
	}
	
	
}
