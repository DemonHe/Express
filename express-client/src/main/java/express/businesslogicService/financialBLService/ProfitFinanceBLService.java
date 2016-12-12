package express.businesslogicService.financialBLService;

import express.vo.ProfitFormVO;

public interface ProfitFinanceBLService extends ProfitManagerBLService {
	
	public boolean addProfitForm(ProfitFormVO vo);
	
	public ProfitFormVO createProfitForm();
}
