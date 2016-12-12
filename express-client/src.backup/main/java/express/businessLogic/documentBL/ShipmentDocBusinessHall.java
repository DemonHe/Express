package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JInternalFrame;

import express.businessLogic.IDKeeper;
import express.businessLogic.infoManageBL.DistanceManager;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.BusinessSaleShipmentDocumentblService;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.dataService.documentDataService.BusinessSaleShipmentDocDataService;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.po.GoodTransStatusPO;
import express.po.OrderPO;
import express.po.ShipmentDocBusinessHallPO;
import express.po.ShipmentDocTransCenterPO;
import express.vo.OrderVO;
import express.vo.ShipmentDocBusinessHallVO;
import express.vo.ShipmentDocTransCenterVO;
import javafx.scene.media.VideoTrack;
import express.rmi.RMIClient;

public class ShipmentDocBusinessHall implements BusinessSaleShipmentDocumentblService {
	BusinessSaleShipmentDocDataService rmiObj;

	public ShipmentDocBusinessHall() {
		rmiObj = RMIClient.getBusinessShipmentDocObject();
	}

	public boolean addShipmentDoc(ShipmentDocBusinessHallVO vo) {
		ShipmentDocBusinessHallPO po = new ShipmentDocBusinessHallPO(vo.getDate(), vo.getTransId(),
				vo.getBusinessHallNum(), vo.getArrivalPlace(), vo.getVanID(), vo.getCheckMan(), vo.getTransMan(),
				vo.getAllOrder(), vo.getMoney(), vo.getShipmentID(),vo.getStartPlace());

		ShipmentDocCheck transidchecker = new ShipmentDocCheck();
		if (transidchecker.isTransIDavailable(vo.getTransId())) {
			System.out.println("transID FAlse"+vo.getTransId());
			return false;
		}

		ArrayList<String> idlist = vo.getAllOrder();
		for (String id : idlist) {
			if (!isOrderIDavailable(id))
				return false;
		}

		// �?查完�?

		try {
			rmiObj.createShipmentDoc(po);
			GoodsStatusDataService changeStatusObj = RMIClient.getGoodStatusObject();

			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = -c.get(Calendar.MONTH) - 1;
			int day = -c.get(Calendar.DATE);
			String date = "";
			date += year;
			date += month;
			date += day;

			for (String id : idlist) {
				GoodTransStatusPO statuspo = changeStatusObj.search(id);
				statuspo.addStatus("已到达寄件人营业�?");
				statuspo.addTime(date);
				statuspo.setFirstBusinessHallID(IDKeeper.getOrgID());
				changeStatusObj.changeGoodtransstatus(statuspo);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ShipmentDocBusinessHallVO getShipmentDoc(String shipmentID) {
		try {
			ShipmentDocBusinessHallPO po = rmiObj.getShipmentDoc(shipmentID);
			ShipmentDocBusinessHallVO vo = new ShipmentDocBusinessHallVO(po.getDate(), po.getTransId(),
					po.getBusinessHallNum(), po.getArrivalPlace(), po.getVanID(), po.getCheckMan(), po.getTransMan(),
					po.getAllOrder(), po.getMoney(), po.getShipmentID(),po.getStartPlace());
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void endShipmentDoc() {
		SysLogBLService syslog = new SysLog();
		syslog.addSysLog("生成营业厅装车单");
		try {
			rmiObj.writeAllShipmentDoc();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isOrderIDavailable(String id) {
		CheckOrder checker = new CheckOrder();
		return checker.isOrderIDAvailable(id);
	}

	/**
	 * 营业�?------>>>>中转中心 营业厅装车单 交�?�工具应�? 汽车
	 * 
	 * @param vo
	 * @return 本次装车的需要支付的运费
	 */

	public double getShipmentfee(ShipmentDocBusinessHallVO vo) {
		DistanceManager disman=new DistanceManager();
		double distance=disman.getTwoCityDistance(vo.getArrivalPlace(), vo.getStartPlace());
		//计算本次装车总重�?
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
			totalfee=2*distance*totalWeight;//汽车每公里每�?2¥
			return totalfee;	
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public ArrayList<ShipmentDocBusinessHallPO>  getAllShipmentDoc(){
		try {
			ArrayList<ShipmentDocBusinessHallPO> shipmentdoclist=rmiObj.getShipmentDoclist();
			return shipmentdoclist;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getShipmentDocID(){  //汽运编号
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

		//加判�?       
		
		long a=System.currentTimeMillis();
		String x="";
		x+=a;
		int l=x.length();
		x=x.substring(l-5, l);
		ID+=x;
		return ID;
		
	}
	
	
	public ArrayList<ShipmentDocBusinessHallVO> getUnExamedBusinessShipmentDoc(){
		try{
			ArrayList<ShipmentDocBusinessHallPO> list=rmiObj.getShipmentDoclist();
			ArrayList<ShipmentDocBusinessHallVO> unexam=new ArrayList<ShipmentDocBusinessHallVO>();
			for(ShipmentDocBusinessHallPO po:list){
				if(po.getState()==false){
					ShipmentDocBusinessHallVO vo=new ShipmentDocBusinessHallVO(po.getDate(), po.getTransId(), po.getBusinessHallNum(), po.getArrivalPlace(), po.getVanID(), po.getCheckMan(), po.getTransMan(), 
																		po.getAllOrder(), po.getMoney(), po.getShipmentID(), po.getStartPlace());
					
					unexam.add(vo);
				}	
			}
			return unexam;	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean changeShipmentDoc(ShipmentDocBusinessHallVO vo){
		ShipmentDocBusinessHallPO po=new ShipmentDocBusinessHallPO(vo.getDate(), vo.getTransId(),
				vo.getBusinessHallNum(), vo.getArrivalPlace(), vo.getVanID(), vo.getCheckMan(), vo.getTransMan(),
				vo.getAllOrder(), vo.getMoney(), vo.getShipmentID(),vo.getStartPlace());
		po.setState(true);
		
		try{
			rmiObj.changeBusinessHallShipmentDoc(po);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
//	public static void main(String[] args){
//		try {
//			RMIClient.init();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		ShipmentDocBusinessHall spdb=new ShipmentDocBusinessHall();
//		//String transid=spdb.getShipmentDocID();
//		//System.out.println(transid);
//		ArrayList<String> order=new ArrayList<String>();
//		order.add("0000000000");
//		order.add("000000001");
//		
//		ShipmentDocBusinessHallVO vo=new 
//				ShipmentDocBusinessHallVO("2015-12-20", "0250002015122092669", "025000", "北京", "van1", "checker1", "transman1", order, 0, "2015122092669", "南京");
//		double cost=spdb.getShipmentfee(vo);
//		
//		System.out.println(cost);
//		
//		if(cost<0){
//			return;
//		}
//		
//		
//		vo.setMoney(cost);
//		
//		System.out.println(spdb.addShipmentDoc(vo));
//		spdb.endShipmentDoc();
//	}
	
	
	
	
	
	
}
