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

import express.businessLogic.documentBL.PaymentDoc;
import express.businessLogic.documentBL.SumReceiveDoc;
import express.businesslogicService.businessSaleBLService.GetReceiveDocBLService;
import express.businesslogicService.financialBLService.OperateFinanceBLService;
import express.businesslogicService.financialBLService.PaymentBLService;
import express.dataService.statisticsDataService.OperateFormDataService;
import express.po.OperateFormPO;
import express.po.PaymentDocPO;
import express.po.PaymentItem;
import express.po.ReceiveDocPO;
import express.rmi.RMIClient;
import express.vo.OperateFormVO;
import express.vo.PaymentDocVO;
import express.vo.ReceiveDocVO;

public class OperateStatistic implements OperateFinanceBLService {

	OperateFormDataService operate;

	public OperateStatistic() {
		operate = RMIClient.getOperateFormObject();
	}

	public ArrayList<String> getOperateFormListTitle() {
		try {
			return operate.getOperateFormListTitle();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public OperateFormVO getOperateForm(int index) {
		try {

			OperateFormPO po = operate.getOperateForm(index);
			if (po != null) {
				OperateFormVO vo = transPOToVO(po);
				return vo;
			}
			return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean removeOperateForm(int index){
		try {
			boolean succ = operate.removeOperateForm(index);
			operate.writeAllOperateForm();
			return succ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public OperateFormVO createOperateForm(String beginDate, String endDate) {
		GetReceiveDocBLService r = new SumReceiveDoc();
		PaymentBLService pay = new PaymentDoc();

		OperateFormVO operateForm = new OperateFormVO();
		operateForm.setStartDate(beginDate);
		operateForm.setEndDate(endDate);

		ArrayList<ReceiveDocVO> receiveList = r.getAllReceiveDoc();
		ArrayList<PaymentDocVO> payList = pay.getAllPaymentDoc();

		ArrayList<ReceiveDocVO> selectReceive = new ArrayList<ReceiveDocVO>();
		ArrayList<PaymentItem> selectPay = new ArrayList<PaymentItem>();

		if (receiveList != null) {

			for (ReceiveDocVO vo : receiveList) {
				String date = vo.getReceiveDate();
				if (date.compareTo(beginDate) >= 0
						&& date.compareTo(endDate) <= 0) {

					selectReceive.add(vo);
				}
			}
		}
		operateForm.setReceiveDoc(selectReceive);

		if (payList != null) {

			for (PaymentDocVO vo : payList) {
				PaymentItem payment = vo.getPaymentList();
				String date = payment.getDate();
				if (date.compareTo(beginDate) >= 0
						&& date.compareTo(endDate) <= 0) {
					selectPay.add(payment);	
				}
			}
		}
		operateForm.setPaymentDoc(selectPay);

		if(beginDate.equals(" ")){
			if (receiveList != null&&receiveList.size()>0){
				beginDate = receiveList.get(0).getReceiveDate();
			}
			else
				beginDate = endDate;
		}
		operateForm.setStartDate(beginDate);
		operateForm.setEndDate(endDate);
		
		return operateForm;
	}

	public boolean addOperateForm(String beginDate, String endDate) {
		GetReceiveDocBLService r = new SumReceiveDoc();
		PaymentBLService pay = new PaymentDoc();

		ArrayList<ReceiveDocPO> receiveList = r.getAllReceiveDocPO();
		ArrayList<PaymentDocPO> payList = pay.getAllPaymentDocPO();

		OperateFormPO operateForm = new OperateFormPO();
		operateForm.setStartDate(beginDate);
		operateForm.setEndDate(endDate);
		
		ArrayList<ReceiveDocPO> selectReceive = new ArrayList<ReceiveDocPO>();
		ArrayList<PaymentItem> selectPay = new ArrayList<PaymentItem>();

		if (receiveList != null) {

			for (ReceiveDocPO po : receiveList) {
				String date = po.getReceiveDate();
				if (date.compareTo(beginDate) >= 0
						&& date.compareTo(endDate) <= 0) {

					selectReceive.add(po);
				}
			}		
		}
		operateForm.setReceiveDoc(selectReceive);
		
		if (payList != null) {

			for (PaymentDocPO po : payList) {
				PaymentItem payment = po.getPaymentList();
				String date = payment.getDate();
				if (date.compareTo(beginDate) >= 0
						&& date.compareTo(endDate) <= 0) {
					selectPay.add(payment);	
				}
			}
		}
		operateForm.setPaymentDoc(selectPay);

		try {
			operate.createOperateForm(operateForm);
			return operate.writeAllOperateForm();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean checkDateAvailable(String beginDate, String endDate) {
		if (beginDate.compareTo(endDate) > 0)
			return false;
		else
			return true;
	}

	private OperateFormVO transPOToVO(OperateFormPO po) {
		OperateFormVO vo = new OperateFormVO();

		vo.setEndDate(po.getEndDate());
		ArrayList<ReceiveDocPO> receList = po.getReceiveDoc();
		
		ArrayList<ReceiveDocVO> l = new ArrayList<ReceiveDocVO>();
		
		if (receList != null&&receList.size() > 0) {
			
			for (ReceiveDocPO i : receList) {
				l.add(new ReceiveDocVO(i.getReceiveDate(), i.getReceivePrice(),
						i.getDeliverManID(), i.getAllOrderIDs(), i.getOrgID()));
			}
		}
		vo.setReceiveDoc(l);
		vo.setStartDate(po.getStartDate());
		vo.setEndDate(po.getEndDate());
		vo.setPaymentDoc(po.getPaymentDoc());
		return vo;
	}

	public boolean exportExcel(OperateFormVO operateForm,String path) {
		
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

			String file = path + s + "经营状�?�表.xls";

			// 第一步，创建�?个webbook，对应一个Excel文件
			HSSFWorkbook wb;
			File f = new File(file);
			// 如果文件不存在，则创建一个文�?
			if (!f.exists()) {
				wb = new HSSFWorkbook();
			} else {
				InputStream input = new FileInputStream(file);
				wb = new HSSFWorkbook(input);
			}

			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String time = format.format(date);

			HSSFSheet sheet = wb.createSheet(time);
			// 第四步，创建单元格，并设置�?�表�? 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建�?个居中格�?

			// 合并第一�?1~5个单元格
			HSSFCell cell;
			HSSFRow row = sheet.createRow(0);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

			// 第一行，设置表头，yyyy-MM-dd~yyyy-MM-dd 经营状�?�表
			cell = row.createCell(0);
			cell.setCellValue(operateForm.getStartDate() + "~"
					+ operateForm.getEndDate() + " 经营状�?�表");
			cell.setCellStyle(style);
			
			// 第二行，合并1~2个单元格�?3~5个单元格
			HSSFRow row1 = sheet.createRow(1);		
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 4));
			cell = row1.createCell(0);
			cell.setCellValue("收款�?");
			cell.setCellStyle(style);

			cell = row1.createCell(2);
			cell.setCellValue("付款�?");
			cell.setCellStyle(style);

			// 第三�?
			String[] title = { "收款日期", "收款金额", "付款日期", "付款金额", "付款条目" };
			HSSFRow row2 = sheet.createRow(2);
			for (int i = 0; i < title.length; i++) {
				cell = row2.createCell(i);
				cell.setCellValue(title[i]);
				cell.setCellStyle(style);
			}

			ArrayList<ReceiveDocVO> receiveList = operateForm.getReceiveDoc();
			ArrayList<PaymentItem> payList = operateForm.getPaymentDoc();
			ArrayList<HSSFRow> rowList = new ArrayList<HSSFRow>();

			int lenReceive = 0;
			int lenPay = 0;
			int max;
			if (receiveList != null) {
				lenReceive = receiveList.size();
			}
			if (payList != null) {
				lenPay = payList.size();
			}

			max = Math.max(lenReceive, lenPay);

			for (int i = 0; i < max; i++) {
				HSSFRow row3 = sheet.createRow(i + 3);
				rowList.add(row3);
			}

			for (int i = 0; i < lenReceive; i++) {
				ReceiveDocVO vo = receiveList.get(i);
				HSSFRow row3 = rowList.get(i);
				// 收款日期
				cell = row3.createCell(0);
				cell.setCellValue(vo.getReceiveDate());
				cell.setCellStyle(style);
				// 收款金额
				cell = row3.createCell(1);
				cell.setCellValue(vo.getReceivePrice());
				cell.setCellStyle(style);
			}

			for (int i = 0; i < lenPay; i++) {
				PaymentItem vo = payList.get(i);
				HSSFRow row3 = rowList.get(i);
				// 付款日期
				cell = row3.createCell(2);
				cell.setCellValue(vo.getDate());
				cell.setCellStyle(style);
				// 付款金额
				cell = row3.createCell(3);
				cell.setCellValue(vo.getSum());
				cell.setCellStyle(style);
				// 付款条目
				cell = row3.createCell(4);
				cell.setCellValue(vo.getEntry());
				cell.setCellStyle(style);
			}

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
			fout.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
