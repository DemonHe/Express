 package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import express.businessLogic.IDKeeper;
import express.businessLogic.infoManageBL.DistanceManager;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.businesslogicService.transcenterSaleBLService.TransCenterTransferDocblService;
import express.dataService.documentDataService.TransCenterTransferDocDataService;
import express.po.ArrivalDocTransCenterPO;
import express.po.ShipmentDocBusinessHallPO;
import express.po.TransWay;
import express.po.TransferDocPO;
import express.vo.ArrivalDocTransCenterVO;
import express.vo.OrderVO;
import express.vo.TransferDocVO;
import express.rmi.RMIClient;

public class TransferDoc implements TransCenterTransferDocblService{

	TransCenterTransferDocDataService rmiObj;
	
	public TransferDoc(){
		rmiObj=RMIClient.getTransferDocObject();
	}
	
	
	public boolean addTransferDoc(TransferDocVO vo) {
		ArrayList<String> orderlist=vo.getOrderlist();
		for(String id:orderlist){
			if(!isOrderIDavailable(id)){
				return false;
			}	
		}  //check every orderid
		
		if(isTransIDavailable(vo.gettranscenterNumber())){
			return false;
		}
		
		TransferDocPO po=new TransferDocPO(vo.getdate(), vo.gettranscenterNumber(), vo.getflightNumber(), vo.getbegin(), vo.getarrival(), vo.getcontainerNumber(),vo.getcheckMan(), 
						vo.getmoney(), vo.getTransWay(), vo.getOrderlist());
		
		
		
		try{
			rmiObj.createTransferDoc(po);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	public TransferDocVO getTransferDoc(String transID) {
		try{
			TransferDocPO po=rmiObj.getTransferDoc(transID);
			TransferDocVO vo=new TransferDocVO(po.getdate(), po.getTransDocID(), po.getflightNumber(), po.getbegin(), po.getarrival(), po.getcontainerNumber(), 
					po.getcheckMan(), po.getmoney(), po.getTransWay(), po.getOrderlist());
			
			return vo;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean isTransIDavailable(String transid) {
		//  025 0 20010203 0000001
		// 0931 0 20020909 0000002
		//19/20�?
		
		if(transid.charAt(0)!='0'){
			return false;
		}
		
		if(transid.length()==19||transid.length()==20){
			for(int i=0;i<transid.length();i++){
				if(transid.charAt(i)>='0'&&transid.charAt(i)<='9'){
					
				}
				else {
					return false;
				}
			}
			return true;
		}
		else {
			return false;
		}
		
	}

	public void endTransferDoc() {
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("生成中转�?");
		try{
			rmiObj.writeAllTransferDoc();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private boolean isOrderIDavailable(String id){
		CheckOrder checker=new CheckOrder();
		return checker.isOrderIDAvailable(id);
	}
	
	public double getTransferFee(TransferDocVO vo){
		DistanceManager disman=new DistanceManager();
		double distance=disman.getTwoCityDistance(vo.getarrival(), vo.getbegin());
		double totalfee=0;
		ArrayList<String> orderlist=vo.getOrderlist();
		OrderController orderctr=new OrderController();
		double totalWeight=0;
		
		try{
			for(String id:orderlist){
				OrderVO orderinstance=orderctr.getOrder(id);				
				totalWeight+=orderinstance.getWeight();
			}
			
			totalWeight=totalWeight/1000;
			totalfee=distance*totalWeight;
			
			if(vo.getTransWay().equals(TransWay.Car)){
				totalfee=totalfee*2;
			}else if ((vo.getTransWay().equals(TransWay.Train))){
				totalfee=totalfee*0.2;
			}else if ((vo.getTransWay().equals(TransWay.Plane))){
				totalfee=totalfee*20;
			}
			
			return totalfee;	
		}catch(Exception e){
			return -1;
		}
	}
	
	public ArrayList<TransferDocPO>  getAllTransferDoc(){
		try {
			ArrayList<TransferDocPO> transferdoclist=rmiObj.getTransferDoclist();
			return transferdoclist;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String getTransferDocID(){
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

	public ArrayList<TransferDocVO> getUnExamedTransferDoc(){
		try{
			ArrayList<TransferDocPO> list=rmiObj.getTransferDoclist();
			ArrayList<TransferDocVO> unexam=new ArrayList<TransferDocVO>();
			
			
			for(TransferDocPO po:list){
				if(po.getState()==false){
					TransferDocVO vo=new TransferDocVO(po.getdate(), po.getTransDocID(), po.getflightNumber(), 
													po.getbegin(), po.getarrival(), po.getcontainerNumber(), po.getcheckMan(), 
													po.getmoney(), po.getTransWay(), po.getOrderlist());
					unexam.add(vo);				
				}
			}
			return unexam;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean changeTransferDoc(TransferDocVO vo){
		TransferDocPO po=new TransferDocPO(vo.getdate(), vo.gettranscenterNumber(), vo.getflightNumber(), vo.getbegin(), vo.getarrival(), vo.getcontainerNumber(),vo.getcheckMan(), 
				vo.getmoney(), vo.getTransWay(), vo.getOrderlist());
		po.setState(true);
		try{
			rmiObj.changeTransferDoc(po);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
public static void main(String[] args){
	try{
		RMIClient.init();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	//TransferDocVO vo=new TransferDocVO(d, transnumber, f, b, ar, c, che, m, transway, orderlist);
	
	
	
	
	
	
}
	
	
	
}
