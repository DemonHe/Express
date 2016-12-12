package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.businesslogicService.transcenterRepoBLService.InDocblService;
import express.dataService.documentDataService.InDocDataService;
import express.po.InDocPO;
import express.rmi.RMIClient;
import express.vo.InDocVO;

public class InDoc implements InDocblService {

	InDocDataService rmiObj;

	public InDoc() {
		rmiObj = RMIClient.getInDocObject();
	}

	@Override
	public boolean addInDoc(InDocVO vo) {
		if (!isOrderIDavailable(vo.getdeliveryNumber())) {
			return false;
		}
		InDocPO po = new InDocPO(vo.getdeliveryNumber(), vo.getdate(),
				vo.getarrival(), vo.getRepoPosition(), vo.getOrgID());
		try {
			rmiObj.createInDoc(po);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public InDocVO getInDoc(String orderID) {
		if (isOrderIDavailable(orderID)) {
			try {
				InDocPO po = rmiObj.getInDocPO(orderID);
				InDocVO vo = new InDocVO(po.getdeliveryNumber(), po.getdate(),
						po.getarrival(), po.getRepoPosition(), po.getOrgID());
				return vo;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public boolean isOrderIDavailable(String id) {
		CheckOrder checker = new CheckOrder();
		return checker.isOrderIDAvailable(id);
	}

	@Override
	public void endInDoc() {
		SysLogBLService syslog = new SysLog();
		syslog.addSysLog("生成入库�?");
		try {
			rmiObj.writeAllInDoc();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean changeInDoc(InDocVO vo) {
		InDocPO po = new InDocPO(vo.getdeliveryNumber(), vo.getdate(),
				vo.getarrival(), vo.getRepoPosition(), vo.getOrgID());
		po.setState(true);
		try {
			rmiObj.changeInDoc(po);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<InDocVO> getUnexamedInDoc() {
		try {
			ArrayList<InDocPO> list = rmiObj.getInDocPOlist();
			ArrayList<InDocVO> unexam = new ArrayList<InDocVO>();
			for (InDocPO po : list) {
				if (po.getState() == false) {
					InDocVO vo = new InDocVO(po.getdeliveryNumber(),
							po.getdate(), po.getarrival(),
							po.getRepoPosition(), po.getOrgID());
					unexam.add(vo);
				}
			}
			return unexam;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<InDocPO> getAllInDocPO(String orgID) {
		try {
			ArrayList<InDocPO> list = rmiObj.getInDocPOlist();
			ArrayList<InDocPO> select = new ArrayList<InDocPO>();
			if(list != null){
				for(InDocPO po : list){
					if(po.getOrgID().equals(orgID)){
						select.add(po);
					}
				}
			}
			
			return select;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
