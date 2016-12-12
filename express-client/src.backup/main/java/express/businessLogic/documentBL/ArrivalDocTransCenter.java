package express.businessLogic.documentBL;

import java.util.ArrayList;
import java.util.Calendar;

import express.businessLogic.IDKeeper;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.businesslogicService.transcenterSaleBLService.TransCenterArrivalDocblService;
import express.dataService.documentDataService.TransCenterArrivalDocumentDataService;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.po.ArrivalDocTransCenterPO;
import express.po.GoodTransStatusPO;
import express.po.GoodsArrivalStatus;
import express.vo.ArrivalDocTransCenterVO;
import express.rmi.RMIClient;

public class ArrivalDocTransCenter implements TransCenterArrivalDocblService{
	TransCenterArrivalDocumentDataService rmiobj;
	public ArrivalDocTransCenter(){
		rmiobj=RMIClient.getTransCenterArrivalDocObject();
	}
	
	
	public boolean addArrivalDoc(ArrivalDocTransCenterVO vo) {
		ArrivalDocTransCenterPO po=new ArrivalDocTransCenterPO(vo.getOrderID(),vo.getArriveDate(),vo.getTransCenterID(),vo.getTransferDocID()
				,vo.getDeparture(),vo.getArrivalStatus());
		if(isOrderIDavailable(vo.getOrderID())){
		
		try{
			OrderController oct=new OrderController();
			if(null==oct.getOrder(vo.getOrderID())){
				return false;
			}
				
			
			
			
			rmiobj.createArrivalDoc(po);
			String orderId=vo.getOrderID();
			
			GoodsStatusDataService changeStatusObj=RMIClient.getGoodStatusObject();
			GoodTransStatusPO statuspo=changeStatusObj.search(orderId);
			
			
			if(po.getTransferDocID().equals("-1")){  //-1 表示没有
				statuspo.setFirsttransCenterID(IDKeeper.getOrgID());
				statuspo.addStatus("到达寄件人中转中�?");
			}
			else{
				statuspo.setSecondtransCenterID(IDKeeper.getOrgID());
				statuspo.addStatus("到达收件人中转中�?");
			}
			Calendar c = Calendar.getInstance();
			int year=c.get(Calendar.YEAR);
			int month=-c.get(Calendar.MONTH)-1;
			int day=-c.get(Calendar.DATE);
			String date="";
			date+=year;
			date+=month;
			date+=day;
			statuspo.addTime(vo.getArriveDate());
			
			changeStatusObj.changeGoodtransstatus(statuspo);
			
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
		return true;
		}return false;
	}

	public ArrivalDocTransCenterVO getArrivalDoc(String OrderID) {
		if(isOrderIDavailable(OrderID)){
			try{
				ArrivalDocTransCenterPO po=rmiobj.getArrivalDoc(OrderID);
				ArrivalDocTransCenterVO vo=new ArrivalDocTransCenterVO(po.getOrderID(),po.getArriveDate(),po.getTransCenterID(),po.getTransferDocID()
						,po.getDeparture(),po.getArrivalStatus());
				return vo;
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		else {
			ArrivalDocTransCenterVO notfindvo=new ArrivalDocTransCenterVO("-1", null,null,null,null,null);
			return notfindvo;
		}
		return null;
	}

	public ArrayList<ArrivalDocTransCenterPO> getUnexamedArrivalDoc(){
		try{
		ArrayList<ArrivalDocTransCenterPO> list=rmiobj.getArrivalDoclist();
		ArrayList<ArrivalDocTransCenterPO> unexam=new ArrayList<ArrivalDocTransCenterPO>();
		int len=list.size();
		for(int i=0;i<len;i++){
			if(list.get(i).getState()==false){
				unexam.add(list.get(i));
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
	
	public void endArrivalDoc() {
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("生成中转中心到达�?");
		
		try{
			rmiobj.writeAllArrivalDoc();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ArrivalDocTransCenterVO> getUnExamedTransArrivalDoc(){
		try{
			ArrayList<ArrivalDocTransCenterPO> list=rmiobj.getArrivalDoclist();
			ArrayList<ArrivalDocTransCenterVO> unexam=new ArrayList<ArrivalDocTransCenterVO>();
			
			
			for(ArrivalDocTransCenterPO po:list){
				if(po.getState()==false){
					ArrivalDocTransCenterVO vo=new ArrivalDocTransCenterVO(po.getOrderID(), po.getArriveDate(), po.getTransCenterID(), 
															po.getTransferDocID(),po.getDeparture(), po.getArrivalStatus());
					unexam.add(vo);				
				}
			}
			return unexam;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean changeTransCenterArrivalDoc(ArrivalDocTransCenterVO vo){
		ArrivalDocTransCenterPO po=new ArrivalDocTransCenterPO(vo.getOrderID(),vo.getArriveDate(),vo.getTransCenterID(),vo.getTransferDocID()
				,vo.getDeparture(),vo.getArrivalStatus());
		po.setState(true);
			try{
				rmiobj.changeArrialDoc(po);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
	}
	
//	public static void main(String[] args){
//		try{
//			RMIClient.init();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		ArrivalDocTransCenterVO vo=new ArrivalDocTransCenterVO("0000000001", "2015-12-29", "0250201512290000000", "-1", "南京", GoodsArrivalStatus.Complete);
//		ArrivalDocTransCenter adtc=new ArrivalDocTransCenter();
//		
//		System.out.println(adtc.addArrivalDoc(vo));
//		
//		adtc.endArrivalDoc();
//		
//	}
	
	

}
