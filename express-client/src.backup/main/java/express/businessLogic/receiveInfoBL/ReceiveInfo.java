package express.businessLogic.receiveInfoBL;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import express.businessLogic.documentBL.CheckOrder;
import express.businessLogic.documentBL.OrderController;
import express.businessLogic.searchBL.Search;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.delivermanBLService.ReceiveInfoBLService;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.dataService.documentDataService.BusinessSaleDeliverDocumentDataService;
import express.dataService.documentDataService.GoodsStatusDataService;
import express.dataService.documentDataService.PredictTimeDataService;
import express.dataService.documentDataService.ReceiveInfoDataService;
import express.po.GoodTransStatusPO;
import express.po.HistoryTimePO;
import express.po.OrderPO;
import express.po.ReceiveInfoPO;
import express.vo.GoodTransStatusVO;
import express.vo.OrderVO;
import express.vo.ReceiveInfoVO;
import express.rmi.RMIClient;

public class ReceiveInfo implements ReceiveInfoBLService{
	ReceiveInfoDataService rmiObj;
	
	public ReceiveInfo(){
		rmiObj=RMIClient.getReceiveInfoObject();
	}
	
	public boolean isOrderIDAvailable(String id){
		CheckOrder checker=new CheckOrder();
		if(checker.isOrderIDAvailable(id)){
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	public boolean addReceiveInfo(ReceiveInfoVO vo){
		String id=vo.getOrderID();
		if(isOrderIDAvailable(id)){
			ReceiveInfoPO po=new ReceiveInfoPO(vo.getReceiverName(), vo.getReceiveTime(), vo.getOrderID());
			try {
				rmiObj.createReceiveInfo(po);
				
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
				statuspo.addTime(vo.getReceiveTime());
				String str="快件已签�? 收件人：";
				str+=vo.getReceiverName();
				statuspo.addStatus(str);
				
				changeStatusObj.changeGoodtransstatus(statuspo);
				//保存物流信息
				// 从GoodtransPO得到出发和到达时�? 记录ArrivalTimePO
				Search search=new Search();
				GoodTransStatusVO goovo=search.getOrderIDStatus(id);
				ArrayList<String> timelist=goovo.getTime();
				String setOutDate=timelist.get(0);
				String arrivalDate=timelist.get(timelist.size()-1);
				
				OrderController orderctr=new OrderController();
				OrderVO ovo=orderctr.getOrder(id);
				String endCity=ovo.getEndCity();
				String startCity=ovo.getStartCity();
				
				
				HistoryTimePO hpo=new HistoryTimePO(startCity, endCity, setOutDate, arrivalDate);
				
				
				PredictTimeDataService pretimeObj=RMIClient.getPredictTimeObject();
				pretimeObj.addToHistory(hpo);	
				pretimeObj.writeAllPredictTime();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			
			
		}
		return false;
	}
	
	
	
	public void endReceiveInfo(){
		SysLogBLService syslog=new SysLog();
		syslog.addSysLog("生成收件信息");
		try{
			rmiObj.writeAllReceiveInfo();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
//	public static void main(String[] args){
//		try {
//			RMIClient.init();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		ReceiveInfoVO infovo=new ReceiveInfoVO("JACK ", "2015-12-28", "0000000001");//4 5
//		ReceiveInfo info=new ReceiveInfo();//12-25�?
//		boolean res=info.addReceiveInfo(infovo);
//		System.out.println(res);
//		if(res)
//		info.endReceiveInfo();
//		
//	}
	
}
