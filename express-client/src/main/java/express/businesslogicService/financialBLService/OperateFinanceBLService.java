package express.businesslogicService.financialBLService;

import express.vo.OperateFormVO;

public interface OperateFinanceBLService extends OperateManagerBLService{

	public OperateFormVO createOperateForm(String beginDate,String endDate);
	
	public boolean addOperateForm(String beginDate, String endDate);
	
	public boolean checkDateAvailable(String beginDate,String endDate);
}
