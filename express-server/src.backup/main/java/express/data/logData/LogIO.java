package express.data.logData;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import express.data.IOHelper.IOHelper;
import express.dataService.logDataService.LogDataService;
import express.po.LogPO;

public class LogIO extends UnicastRemoteObject implements LogDataService{

	String filename="SerializableData/SysLog.ser";
	ArrayList<LogPO> logList;
	
	public LogIO() throws RemoteException {
		super();
		IOHelper io=new IOHelper();
		logList=new ArrayList<LogPO>();
		try {
			logList=(ArrayList<LogPO>)io.readFromDisk(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<LogPO> getSystemLog() throws RemoteException{
		if(logList.size()>0)
			return logList;
		else
			return null;
	}

	public boolean createSystemLog(LogPO po) throws RemoteException{
		IOHelper io=new IOHelper();
		logList.add(po);
		try {
			io.writeToDisk(filename, logList);
			return true;
		} catch (IOException e) {
			
			return false;
		}
	}

}
