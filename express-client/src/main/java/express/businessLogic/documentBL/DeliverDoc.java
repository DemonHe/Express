package express.businessLogic.documentBL;

import java.util.ArrayList;
import java.util.Calendar;

import express.businessLogic.IDKeeper;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.BusinessSaleDeliverDocumentblService;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.dataService.documentDataService.BusinessSaleDeliverDocumentDataService;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.po.DeliverDocPO;
import express.po.GoodTransStatusPO;
import express.vo.DeliverDocVO;
import express.rmi.RMIClient;

public class DeliverDoc implements BusinessSaleDeliverDocumentblService{
	BusinessSaleDeliverDocumentDataService rmiObj;
	
	public DeliverDoc(){
		rmiObj=RMIClient.getDeliverDocObject();
	}
	
		
	public boolean addDeliverDoc(DeliverDocVO vo){
		if(!isOrderIDavailable(vo.getOrderID())){
			return false;
		}

		DeliverDocPO po=new DeliverDocPO(vo.getArriveDate(), vo.getOrderID(), vo.getDeliverManID());
		try{
		rmiObj.createDeliverDoc(po);
		OrderController oct=new OrderController();
		if(null==oct.getOrder(vo.getOrderID())){
			return false;
		}
		
		
		GoodsStatusDataService changeStatusObj=RMIClient.getGoodStatusObject();
		
		Calendar c = Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=-c.get(Calendar.MONTH)-1;
		int day=-c.get(Calendar.DATE);
		String date="";
		date+=year;
		date+=month;
		date+=day;
		
		GoodTransStatusPO statuspo=changeStatusObj.search(vo.getOrderID());
		statuspo.setDeliverManID(IDKeeper.getID());
		statuspo.addStatus("快件正在派件");
		statuspo.addTime(date);
		
		changeStatusObj.changeGoodtransstatus(statuspo);
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public DeliverDocVO getDeliverDoc(String OrderID){
		if(isOrderIDavailable(OrderID)){
			try{
				DeliverDocPO po=rmiObj.getDeliverDoc(OrderID);
				DeliverDocVO vo=new DeliverDocVO(po.getArriveDate(), po.getOrderID(), po.getDeliverManID());
				return vo;
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		else {
			DeliverDocVO notfindvo=new DeliverDocVO(null, "-1", null);
			return notfindvo;
		}
		return new DeliverDocVO(null, null, null);		
	}
	
	
	public ArrayList<DeliverDocVO> getUnexamedDeliverDoc(){
		try{
		ArrayList<DeliverDocPO> list=rmiObj.getDeliverDoclist();
		ArrayList<DeliverDocVO> unexam=new ArrayList<DeliverDocVO>();
		
		for(DeliverDocPO po:list){
			if(po.getState()==false){
				DeliverDocVO vo=new DeliverDocVO(po.getArriveDate(), po.getOrderID(), po.getDeliverManID());
				unexam.add(vo);
			}
		}
		return unexam;

		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean isOrderIDavailable(String id){
		CheckOrder checker=new CheckOrder();
		return checker.isOrderIDAvailable(id);
	}
	public void  endDeliverDoc(){
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("生成派件单");
		try{
			rmiObj.writeAllDeliverDoc();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean changeDeliverDoc(DeliverDocVO vo){
		DeliverDocPO po=new DeliverDocPO(vo.getArriveDate(), vo.getOrderID(), vo.getDeliverManID());
		po.setState(true);
		try{
			rmiObj.changeDeliverDoc(po);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
}
