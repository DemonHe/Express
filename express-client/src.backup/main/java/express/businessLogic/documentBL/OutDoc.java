package express.businessLogic.documentBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.managerBLService.SysLogBLService;
import express.businesslogicService.transcenterRepoBLService.OutDocblService;
import express.dataService.documentDataService.OutDocDataService;
import express.po.OutDocPO;
import express.rmi.RMIClient;
import express.vo.OutDocVO;

public class OutDoc implements OutDocblService {
	OutDocDataService rmiObj;

	public OutDoc() {
		rmiObj = RMIClient.getOutDocObject();
	}

	@Override
	public boolean addOutDoc(OutDocVO vo) {
		if (!isOrderIDavailable(vo.getOrderID())) {
			return false;
		}
		OutDocPO po = new OutDocPO(vo.getOrderID(), vo.getdate(),
				vo.getarrival(), vo.gettransKind(), vo.gettransNumber(),
				vo.getOrgID());
		try {
			rmiObj.crateOutDoc(po);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public OutDocVO getOutDoc(String orderID) {
		if (isOrderIDavailable(orderID)) {
			try {
				OutDocPO po = rmiObj.getOutDocPO(orderID);
				OutDocVO vo = new OutDocVO(po.getOrderID(), po.getdate(),
						po.getarrival(), po.gettransKind(),
						po.gettransNumber(), po.getOrgID());
				return vo;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
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
	public void endOutDoc() {
		SysLogBLService syslog = new SysLog();
		syslog.addSysLog("生成出库�?");
		try {
			rmiObj.writeAllOutDoc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean changeOutDoc(OutDocVO vo) {
		OutDocPO po = new OutDocPO(vo.getOrderID(), vo.getdate(),
				vo.getarrival(), vo.gettransKind(), vo.gettransNumber(),
				vo.getOrgID());
		po.setState(true);
		try {
			rmiObj.changeOutDoc(po);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<OutDocVO> getUnexamedOutDoc() {
		try {
			ArrayList<OutDocPO> list = rmiObj.getOutDocPOlist();
			ArrayList<OutDocVO> unexam = new ArrayList<OutDocVO>();
			for (OutDocPO po : list) {
				if (po.getState() == false) {
					OutDocVO vo = new OutDocVO(po.getOrderID(), po.getdate(),
							po.getarrival(), po.gettransKind(),
							po.gettransNumber(), po.getOrgID());
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
	public ArrayList<OutDocPO> getAllOutDocPO(String orgID) {
		try {
			ArrayList<OutDocPO> list = rmiObj.getOutDocPOlist();
			ArrayList<OutDocPO> select = new ArrayList<OutDocPO>();
			if(list != null){
				for(OutDocPO po : list){
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
