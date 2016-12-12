package express.businesslogicService.managerBLService;

import java.util.ArrayList;

import express.po.SalaryPO;
import express.vo.SalaryStrategyVO;

public interface SalaryManagerBLService {

	public boolean changeSalaryStrategy(SalaryStrategyVO vo);
	
	public ArrayList<SalaryStrategyVO> getSalaryStrategyList();
	
	public ArrayList<SalaryPO> getSalaryStrategyListPO();
	
	public boolean recordSalaryStrategy();
}
