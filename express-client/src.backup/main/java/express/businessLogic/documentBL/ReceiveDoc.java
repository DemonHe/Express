package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.businessSaleBLService.BusinessSaleReceiveDocumentblService;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.dataService.documentDataService.BusinessSaleDeliverDocumentDataService;
import express.dataService.documentDataService.BusinessSaleReceiveDocumentDataService;
import express.po.DeliverDocPO;
import express.po.ReceiveDocPO;
import express.po.TransferDocPO;
import express.vo.OrderVO;
import express.vo.ReceiveDocVO;
import express.vo.TransferDocVO;
import express.rmi.RMIClient;

public class ReceiveDoc implements BusinessSaleReceiveDocumentblService{
	BusinessSaleReceiveDocumentDataService rmiObj;
	
	public ReceiveDoc(){
		rmiObj=RMIClient.getReceiveDocObject();
	}
	
	public boolean addReceiveDoc(ReceiveDocVO vo){
		String date=vo.getReceiveDate();
		String delivermanID=vo.getDeliverManID();
		if(getReceiveDoc(date, delivermanID)==null){
			ReceiveDocPO po=new ReceiveDocPO(vo.getReceiveDate(), vo.getReceivePrice(),
				vo.getDeliverManID(), vo.getAllOrderIDs(),vo.getOrgID());
			try{
				rmiObj.createReceiveDoc(po);
			}catch(Exception e){
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;// 今天该快递员已经建立收款�?
		}
	}
	public ReceiveDocVO getReceiveDoc(String date,String delivermanID ){
		try{
			ReceiveDocPO po=rmiObj.getReceiveDoc(date,delivermanID);
			if(po==null){
				return null; //查不到返回null
			}
			ReceiveDocVO vo=new ReceiveDocVO(po.getReceiveDate(),po.getReceivePrice(),
					po.getDeliverManID(), po.getAllOrderIDs(), po.getOrgID());
			return vo;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ReceiveDocVO(null, 0, date, null,null);
	}
	public boolean isDateAvailable(String date){
		//20150709
		if(date.length()!=8){
			return false;
		}
		String [] box=new String[3];
		box[0]=date.substring(0,4);
		box[1]=date.substring(4,6);
		box[2]=date.substring(6,8);
		
		int year=Integer.parseInt(box[0]);
		int month=Integer.parseInt(box[1]);
		int day=Integer.parseInt(box[2]);
		
		if(year>=2500||year<2000){
			return false;
		}
		if(month<1||month>12){
			return false;
		}
		
		if(day<1||day>31){
			return false;
		}
		
		System.out.println(box[0]+"  "+box[1]+"   "+box[2]);
		return true;
	}
	public void endReceiveDoc(){
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("生成收款�?");
		
		try {
			rmiObj.writeAllReceiveDoc();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<ReceiveDocVO> getUnExamedReceiveDoc(){
		try{
			ArrayList<ReceiveDocPO> list=rmiObj.getReceiveDoclist();
			ArrayList<ReceiveDocVO> unexam=new ArrayList<ReceiveDocVO>();
			
			
			for(ReceiveDocPO po:list){
				if(po.getState()==false){
					ReceiveDocVO vo=new ReceiveDocVO(po.getReceiveDate(),po.getReceivePrice(),
					po.getDeliverManID(), po.getAllOrderIDs(), po.getOrgID());
					unexam.add(vo);				
				}
			}
			return unexam;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean changeReceiveDoc(ReceiveDocVO vo){
		ReceiveDocPO po=new ReceiveDocPO(vo.getReceiveDate(), vo.getReceivePrice(),
				vo.getDeliverManID(), vo.getAllOrderIDs(),vo.getOrgID());
		po.setState(true);
		try{
			rmiObj.changeReceiveDoc(po);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public double getTotalPrice(ArrayList<String> orderlist){
		OrderController octr=new OrderController();
		double sum=0;
		
		try{
		for(String id:orderlist){
			OrderVO vo=octr.getOrder(id);
			sum+=vo.getFee();
		}
		}catch(Exception e){
			return -1;
		}
		return sum;
	}
	
	
	
	
	
//	public static void main(String[] args){
//		
//		
//		try{
//			RMIClient.init();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		ArrayList<String> idlist=new ArrayList<String>();
//		idlist.add("0000000001");
//		idlist.add("0000000000");
//		
//		ReceiveDoc rdc=new ReceiveDoc();
//		
//		System.out.println(rdc.getTotalPrice(idlist));
//		
//		ReceiveDocVO vo=new ReceiveDocVO("2015-12-20", 0, "12345", idlist, "001");
//		
//		System.out.println(rdc.addReceiveDoc(vo));
//		
//		rdc.endReceiveDoc();
//		
//		
//		
//	}
	
	
}
