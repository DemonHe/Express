package express.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.dataService.ipandname.IPDataService;
import express.po.IPPO;

public class IPmanager extends UnicastRemoteObject implements IPDataService{

	static ArrayList<IPPO> iplist=new ArrayList<IPPO>();
	
	
	public IPmanager() throws RemoteException {
		super();
	}

	@Override
	public void addIP(IPPO ip) throws RemoteException {
		iplist.add(ip);
		System.out.println(iplist.size()+iplist.get(0).getIP()+iplist.get(0).getName());
	}

	@Override
	public void minusIP(IPPO ip) throws RemoteException {
		String ipad=ip.getIP();
		int len=iplist.size();
		
		for(int i=0;i<len;i++){
			if(iplist.get(i).getIP().equals(ipad)){
				iplist.remove(i);
				break;
			}
		}
		
	}
	
	public ArrayList<IPPO> getIPlist(){
		return iplist;
	}
	
	
	public static void main(String[] args){
		
		
		
		
		
	}
	
	

}
