package express.businesslogicService.financialBLService;

import java.util.ArrayList;

import express.vo.OperateFormVO;

public interface OperateManagerBLService {

	public ArrayList<String> getOperateFormListTitle();
	
	public OperateFormVO getOperateForm(int index);
	
	public boolean removeOperateForm(int index);
	
	public boolean exportExcel(OperateFormVO operateForm,String path);
}
