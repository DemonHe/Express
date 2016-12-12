package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import express.businessLogic.IDKeeper;
import express.businessLogic.infoManageBL.DistanceManager;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.businesslogicService.transcenterSaleBLService.TransCenterSaleShipmentDocblService;
import express.dataService.documentDataService.TransCenterShipmentDocDataService;
import express.po.DeliverDocPO;
import express.po.ShipmentDocBusinessHallPO;
import express.po.ShipmentDocTransCenterPO;
import express.po.TransferDocPO;
import express.vo.OrderVO;
import express.vo.ShipmentDocBusinessHallVO;
import express.vo.ShipmentDocTransCenterVO;
import express.vo.TransferDocVO;
import express.rmi.RMIClient;

public class ShipmentDocTransCenter {   //不用implements
	TransCenterShipmentDocDataService rmiObj;
	
	public ShipmentDocTransCenter(){
		rmiObj=RMIClient.getShipmentDocObject();
	}
	
	public boolean addShipmentDoc(ShipmentDocTransCenterVO vo){
		ShipmentDocTransCenterPO po=new ShipmentDocTransCenterPO(vo.getDate(), vo.getTransId(), vo.getArrivalPlace(), vo.getVanID(), vo.getCheckMan(), 
				vo.getTransMan(), vo.getAllOrder(), vo.getMoney(), vo.getShipmentID(), vo.getStartPlace());
		
		ArrayList<String> idlist=vo.getAllOrder();
		for(String id:idlist){
			if(!isOrderIDavailable(id))
				return false;
		}
		
		try{
			rmiObj.createShipmentDoc(po);
			}catch(Exception e){
				e.printStackTrace();
			}
		
			return true;
	}
	
	
	
	
	public ShipmentDocTransCenterVO getShipmentDoc(String shipmentID) {
		
			try{
				ShipmentDocTransCenterPO po=rmiObj.getShipmentDoc(shipmentID);
				ShipmentDocTransCenterVO vo=new ShipmentDocTransCenterVO(po.getDate(), po.getTransId(), po.getArrivalPlace(), po.getVanID(), po.getCheckMan(), 
						po.getTransMan(), po.getAllOrder(), po.getMoney(), po.getShipmentID(), po.getStartPlace());
				return vo;
			}catch(Exception e){
				e.printStackTrace();
			}
		return null;

	}
	
	public ArrayList<ShipmentDocTransCenterPO> getUnexamedShipmentDoc(){
		try{
		ArrayList<ShipmentDocTransCenterPO> list=rmiObj.getShipmentDoclist();
		ArrayList<ShipmentDocTransCenterPO> unexam=new ArrayList<ShipmentDocTransCenterPO>();
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
	
	
	
	public void  endShipmentDoc(){
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("生成中转中心装车单");
		try{
			rmiObj.writeAllShipmentDoc();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public boolean isOrderIDavailable(String id){
		CheckOrder checker=new CheckOrder();
		return checker.isOrderIDAvailable(id);
	}
	
	public ArrayList<ShipmentDocTransCenterVO>  getAllShipmentDoc(){
		try {
			ArrayList<ShipmentDocTransCenterPO> shipmentdoclist=rmiObj.getShipmentDoclist();
			ArrayList<ShipmentDocTransCenterVO> volist=new ArrayList<ShipmentDocTransCenterVO>();
			
			for(ShipmentDocTransCenterPO po :shipmentdoclist){
				ShipmentDocTransCenterVO vo=new ShipmentDocTransCenterVO(po.getDate(), po.getTransId(), po.getArrivalPlace(), po.getVanID(), po.getCheckMan(), 
						po.getTransMan(), po.getAllOrder(), po.getMoney(), po.getShipmentID(), po.getStartPlace());
				volist.add(vo);
			}

			
			return volist;
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getShipmentDocID(){
		String ID="";
		String orgID=IDKeeper.getOrgID();
		Calendar c = Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1;
		int day=c.get(Calendar.DATE);
		
		if(month<10&&day<10){
			ID+=orgID+year+"0"+month+"0"+day;
		}
		if(month>=10&&day<10){
			ID+=orgID+year+month+"0"+day;	
		}
		if(month<10&&day>=10){
			ID+=orgID+year+"0"+month+day;
		}
		if(month>=10&&day>=10){
			ID+=orgID+year+month+day;
		}

		
		
		long a=System.currentTimeMillis();
		String x="";
		x+=a;
		int l=x.length();
		x=x.substring(l-7, l);
		ID+=x;
		return ID;
		
	}
	
	
	public double getShipmentfee(ShipmentDocTransCenterVO vo) {
		DistanceManager disman=new DistanceManager();
		double distance=disman.getTwoCityDistance(vo.getArrivalPlace(), vo.getStartPlace());
		//计算本次装车总重量
		ArrayList<String> orderlist=vo.getAllOrder();
		OrderController orderctr=new OrderController();
		double totalWeight=0;
		double totalfee=0;
		
		
		try{
			for(String id:orderlist){
				OrderVO orderinstance=orderctr.getOrder(id);				
				totalWeight+=orderinstance.getWeight();
			}
			
			totalWeight=totalWeight/1000;
			totalfee=2*distance*totalWeight;//汽车每公里每吨2¥
			return totalfee;	
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public ArrayList<ShipmentDocTransCenterVO> getUnExamedShipmentDoc(){
		try{
			ArrayList<ShipmentDocTransCenterPO> list=rmiObj.getShipmentDoclist();
			ArrayList<ShipmentDocTransCenterVO> unexam=new ArrayList<ShipmentDocTransCenterVO>();
			
			
			for(ShipmentDocTransCenterPO po:list){
				if(po.getState()==false){
					ShipmentDocTransCenterVO vo=new ShipmentDocTransCenterVO(po.getDate(), po.getTransId(), po.getArrivalPlace(), po.getVanID(), po.getCheckMan(), 
							po.getTransMan(), po.getAllOrder(), po.getMoney(), po.getShipmentID(), po.getStartPlace());
					unexam.add(vo);				
				}
			}
			return unexam;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean changeTransCenterShipmentDoc(ShipmentDocTransCenterVO vo){
		ShipmentDocTransCenterPO po=new ShipmentDocTransCenterPO(vo.getDate(), vo.getTransId(), vo.getArrivalPlace(), vo.getVanID(), vo.getCheckMan(), 
				vo.getTransMan(), vo.getAllOrder(), vo.getMoney(), vo.getShipmentID(), vo.getStartPlace());
		po.setState(true);
		try{
			rmiObj.changeTransCenterShipmentDoc(po);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ArrayList<ShipmentDocTransCenterPO>  getAllShipmentDocPO(){
		try {
			return rmiObj.getShipmentDoclist();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
