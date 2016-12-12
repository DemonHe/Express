package express.businessLogic.statisticBL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import express.businessLogic.infoManageBL.BankAccount;
import express.businesslogicService.financialBLService.BankAccountBLService;
import express.businesslogicService.financialBLService.ProfitFinanceBLService;
import express.dataService.statisticsDataService.ProfitFormDataService;
import express.po.BankAccountPO;
import express.po.ProfitFormPO;
import express.rmi.RMIClient;
import express.vo.ProfitFormVO;

public class ProfitStatistic implements ProfitFinanceBLService {

	ProfitFormDataService profit;

	public ProfitStatistic() {
		profit = RMIClient.getProfitFormObject();
	}

	public ArrayList<ProfitFormVO> getProfitFormList() {
		try {
			ArrayList<ProfitFormPO> list = profit.getProfitFormList();
			
			ArrayList<ProfitFormVO> transList = new ArrayList<ProfitFormVO>();
			if(list!=null){
				for(int i = 0;i < list.size();i++){
					ProfitFormPO po = list.get(i);
					ProfitFormVO vo = new ProfitFormVO(po.getTitle(),
							po.getIncome(), po.getOutCome(), po.getProfit());
					
					transList.add(vo);
				}
			}
			
			return transList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ProfitFormVO getProfitForm(String date) {
		try {
			ProfitFormPO po = profit.getProfitForm(date);
			if (po != null) {
				ProfitFormVO vo = transPOToVO(po);
				return vo;
			}
			return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean removeProfitForm(int index){
		try {
			boolean succ = profit.removeProfitForm(index);
			profit.writeAllProfitForm();
			return succ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ProfitFormVO createProfitForm() {
		BankAccountBLService bank = new BankAccount();

		ArrayList<BankAccountPO> list = bank.getAllBankAccountPO();
		double income = 0, outcome = 0, p = 0;

		if (list != null) {
			for (BankAccountPO b : list) {
				income += b.getIncome();
				outcome += b.getOutcome();
			}
		}
		p = income - outcome;

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String time = format.format(date);

		ProfitFormVO profitForm = new ProfitFormVO(time, income, outcome, p);

		return profitForm;
	}

	public boolean addProfitForm(ProfitFormVO vo) {
		try {
			ProfitFormPO profitForm = new ProfitFormPO(vo.getTitle(),
					vo.getIncome(), vo.getOutCome(), vo.getProfit());
			profit.createProfitForm(profitForm);
			return profit.writeAllProfitForm();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private ProfitFormVO transPOToVO(ProfitFormPO po) {
		ProfitFormVO vo = new ProfitFormVO();

		vo.setTitle(po.getTitle());
		vo.setIncome(po.getIncome());
		vo.setOutcome(po.getOutCome());
		vo.setProfit(po.getProfit());

		return vo;
	}

	private ProfitFormPO transVOToPO(ProfitFormVO vo) {
		ProfitFormPO po = new ProfitFormPO();

		po.setTitle(vo.getTitle());
		po.setIncome(vo.getIncome());
		po.setOutcome(vo.getOutCome());
		po.setProfit(vo.getProfit());

		return po;
	}

	public boolean exportExcel(ProfitFormVO profit,String path) {
		
		try {
			// InputStreamReader read = new InputStreamReader(new
			// FileInputStream(
			// "config-client/filePath.txt"));
			// BufferedReader br = new BufferedReader(read);
			// String filePath = br.readLine();
			// br.close();
			// 读配置文�?

			String s;
			if (System.getProperty("os.name").equals("Mac OS X"))
				s = "/";
			else
				s = "\\";
			
			String file=path + s + "成本收益�?.xls";
			
			// 第一步，创建�?个webbook，对应一个Excel文件
			HSSFWorkbook wb;
			File f=new File(file);
			//如果文件不存在，则创建一个文�?			
			if(!f.exists()) { 
				wb = new HSSFWorkbook();
			}
			else{
				InputStream input = new FileInputStream(file);
				wb = new HSSFWorkbook(input);
			}
			
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(profit.getTitle());

			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建�?个居中格�?

			// 合并第一�?1~3个单元格
			HSSFCell cell;
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

			// 第一行，设置表头，yyyy-MM-dd 成本收益�?
			HSSFRow row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue(profit.getTitle().substring(0, 10) + " 成本收益�?");
			cell.setCellStyle(style);

			// 第二�?
			HSSFRow row1 = sheet.createRow(1);
			String[] title = { "总收�?", "总支�?", "总利�?" };
			int i;
			for (i = 0; i < title.length; i++) {
				cell = row1.createCell(i);
				cell.setCellValue(title[i]);
				cell.setCellStyle(style);
			}

			// 第三�?
			HSSFRow row2 = sheet.createRow(2);
			row2.createCell(0).setCellValue(profit.getIncome());
			row2.createCell(1).setCellValue(profit.getOutCome());
			row2.createCell(2).setCellValue(profit.getProfit());

			// 第六步，将文件存到指定位�?
				/*InputStreamReader read = new InputStreamReader(new FileInputStream(
						"config/filePath.txt"));
				BufferedReader br = new BufferedReader(read);
				String filePath = br.readLine();
				br.close();
				// 读配置文�?

				String s;
				if (System.getProperty("os.name").equals("Mac OS X"))
					s = "/";
				else
					s = "\\";*/

			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			wb.close();
			fout.close();

			return true;
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
