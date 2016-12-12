package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;


import express.businessLogic.IDKeeper;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.BusinessSaleArrivalDocumentblService;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.dataService.documentDataService.BusinessSaleArrivalDocumentDataService;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.dataService.documentDataService.TransCenterTransferDocDataService;
import express.po.ArrivalDocBusinessHallPO;
import express.po.ArrivalDocTransCenterPO;
import express.po.GoodTransStatusPO;
import express.po.GoodsArrivalStatus;
import express.po.TransferDocPO;
import express.vo.ArrivalDocBusinessHallVO;
import express.vo.ArrivalDocTransCenterVO;
import express.vo.OrderVO;
import express.rmi.RMIClient;

public class ArrivalDocBusinessHall implements BusinessSaleArrivalDocumentblService{

	BusinessSaleArrivalDocumentDataService rmiObj;
	
	public ArrivalDocBusinessHall(){
		rmiObj=RMIClient.getBusinessHallArrivalDocObject();
	}
	
	public boolean addArrivalDoc(ArrivalDocBusinessHallVO vo) {
		ArrivalDocBusinessHallPO po=new ArrivalDocBusinessHallPO(vo.getArriveTime(), vo.getTransferDocID(), vo.getDeparture(),
				vo.getArrivalStatus(), vo.getOrderID());
		
		if(isOrderIDavailable(vo.getOrderID())){
		
		
		
		try{
			OrderController oct=new OrderController();
			if(null==oct.getOrder(vo.getOrderID())){
				return false;
			}
			
			if(null!=rmiObj.getArrivalDoc(vo.getOrderID())){
				System.out.print("IDIDI"+vo.getOrderID());
				return false;
			}

			rmiObj.createArrivalDoc(po);
			String orderId=vo.getOrderID();
			
			GoodsStatusDataService changeStatusObj=RMIClient.getGoodStatusObject();
			GoodTransStatusPO statuspo=changeStatusObj.search(orderId);

				statuspo.setSecondBusinessHallID(IDKeeper.getOrgID());
				statuspo.addStatus("到达("+IDKeeper.getCity()+")收件人营业厅");
			
			Calendar c = Calendar.getInstance();
			int year=c.get(Calendar.YEAR);
			int month=-c.get(Calendar.MONTH)-1;
			int day=-c.get(Calendar.DATE);

			String date="";
			date+=year;
			date+=month;
			date+=day;
			statuspo.addTime(vo.getArriveTime());
			
			changeStatusObj.changeGoodtransstatus(statuspo);
			
			return true;
			
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	else{
		return false;
	}
}
	
	
	public String getTransferDocID(String orderID){
		
		if(!isOrderIDavailable(orderID)){
			return null;
		}
		
		
		TransCenterTransferDocDataService transObj=RMIClient.getTransferDocObject();
		try {
			ArrayList<TransferDocPO> translist=transObj.getTransferDoclist();
			for(TransferDocPO po:translist){
				for(String id:po.getOrderlist()){
					if(id.equals(orderID)){
						return po.getTransDocID();
					}	
				}	
			}
			return null;
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getDeparture(String orderID) {
		if(!isOrderIDavailable(orderID)){
			return null;
		}
		OrderController ordercrt=new OrderController();
		OrderVO vo=ordercrt.getOrder(orderID);
		String departure=vo.getStartCity();
		return departure;	
	}
	

	public ArrivalDocBusinessHallVO getArrivalDoc(String OrderID) {
		if(isOrderIDavailable(OrderID)){
			try{
				ArrivalDocBusinessHallPO po=rmiObj.getArrivalDoc(OrderID);
				ArrivalDocBusinessHallVO vo=new ArrivalDocBusinessHallVO(po.getArriveTime(), po.getTransferDocID(), po.getDeparture(), 
																		po.getArrivalStatus(), po.getOrderID());
				return vo;
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		else {
			return null;
		}
		return null;
			
	}

	
	public void endArrivalDoc() {
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("生成营业厅到达单");
		
		try{
			rmiObj.writeAllArrivalDoc();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	private boolean isOrderIDavailable(String id) {
		CheckOrder checker = new CheckOrder();
		return checker.isOrderIDAvailable(id);
	}

	public ArrayList<ArrivalDocBusinessHallVO> getUnExamedBusinessHallArrivalDoc(){
		try{
			ArrayList<ArrivalDocBusinessHallPO> list=rmiObj.getArrivalDoclist();
			ArrayList<ArrivalDocBusinessHallVO> unexam=new ArrayList<ArrivalDocBusinessHallVO>();
			
			
			for(ArrivalDocBusinessHallPO po:list){
				if(po.getState()==false){
					ArrivalDocBusinessHallVO vo=new ArrivalDocBusinessHallVO(po.getArriveTime(), po.getTransferDocID(), po.getDeparture(), 
							po.getArrivalStatus(), po.getOrderID());
					unexam.add(vo);
				}
			}
			return unexam;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean changeBusinessHallArrivalDoc(ArrivalDocBusinessHallVO vo){
		ArrivalDocBusinessHallPO po=new ArrivalDocBusinessHallPO(vo.getArriveTime(), vo.getTransferDocID(), vo.getDeparture(),
				vo.getArrivalStatus(), vo.getOrderID());
		po.setState(true);
		try{
			rmiObj.changeBusinessHallArrivalDoc(po);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
//	public static void main(String[] args){
//		try{
//		RMIClient.init();
//	}catch(Exception e){
//		e.printStackTrace();
//	}
//		
//		ArrivalDocBusinessHallVO vo=new ArrivalDocBusinessHallVO("2015-12-31", "12344", "南京", GoodsArrivalStatus.Complete,"0000000001");		
//		ArrivalDocBusinessHall adbh=new ArrivalDocBusinessHall();
//		System.out.println(adbh.addArrivalDoc(vo));
//		adbh.endArrivalDoc();
//		
//	}
	
}
