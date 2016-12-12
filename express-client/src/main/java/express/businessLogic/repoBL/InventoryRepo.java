package express.businessLogic.repoBL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import express.businessLogic.documentBL.InDoc;
import express.businessLogic.syslogBL.SysLog;
import express.businesslogicService.transcenterRepoBLService.InDocblService;
import express.po.Area;
import express.po.InDocPO;
import express.po.RepoPosition;
import express.vo.InDocVO;

public class InventoryRepo {

	public InventoryRepo() {

	}

	public ArrayList<InDocVO> inventoryRepo(String orgID) {
		InDocblService inDoc = new InDoc();
		ArrayList<InDocPO> list = inDoc.getAllInDocPO(orgID);
		ArrayList<InDocVO> transList = new ArrayList<InDocVO>();
		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(d);

		if (list != null) {
			for (InDocPO po : list) {
				String date = po.getdate();
				if (date.equals(time)) {
					InDocVO vo = new InDocVO(po.getdeliveryNumber(),
							po.getdate(), po.getarrival(),
							po.getRepoPosition(), po.getOrgID());
					transList.add(vo);
				}
			}
		}
		return transList;
	}

	public boolean exportExcel(String orgID,String path) {
		try {
			// InputStreamReader read = new InputStreamReader(new
			// FileInputStream(
			// "config-client/filePath.txt"));
			// BufferedReader br = new BufferedReader(read);
			// String filePath = br.readLine();
			// br.close();
			// 读配置文件

			String s;
			if (System.getProperty("os.name").equals("Mac OS X"))
				s = "/";
			else
				s = "\\";

			String file = path + s + "库存盘点.xls";

			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb;
			File f = new File(file);
			// 如果文件不存在，则创建一个文件
			if (!f.exists()) {
				wb = new HSSFWorkbook();
			} else {
				InputStream input = new FileInputStream(file);
				wb = new HSSFWorkbook(input);
			}

			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			Date d = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String time = format.format(d);
			HSSFSheet sheet = wb.createSheet(time);
			sheet.setColumnWidth(0, 30);

			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			// 合并第一行1~3个单元格
			HSSFCell cell;
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

			// 第一行，设置表头，yyyy-MM-dd 库存盘点
			HSSFRow row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue(time.substring(0, 10) + " 库存盘点");
			cell.setCellStyle(style);

			// 第二行
			HSSFRow row1 = sheet.createRow(1);
			String[] title = { "快递编号", "入库日期", "目的地", "区号", "排号", "架号", "位号" };

			for (int i = 0; i < title.length; i++) {
				cell = row1.createCell(i);
				cell.setCellValue(title[i]);

				cell.setCellStyle(style);
			}

			ArrayList<InDocVO> list = inventoryRepo(orgID);

			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					InDocVO vo = list.get(i);
					HSSFRow row2 = sheet.createRow(2 + i);
					cell = row2.createCell(0);
					cell.setCellValue(vo.getdeliveryNumber());
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(vo.getdate());
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue(vo.getarrival());
					cell.setCellStyle(style);

					RepoPosition p = vo.getRepoPosition();
					cell = row2.createCell(3);
					Area a = p.getblock();

					switch (a) {
					case AIR:
						cell.setCellValue("航运区");
						break;
					case TRAIN:
						cell.setCellValue("铁运区");
						break;
					case CAR:
						cell.setCellValue("汽运区");
						break;
					case FLEXIBLE:
						cell.setCellValue("机动区");
						break;
					}
					cell.setCellStyle(style);

					cell = row2.createCell(4);
					cell.setCellValue(p.getrow());
					cell.setCellStyle(style);

					cell = row2.createCell(5);
					cell.setCellValue(p.getshelf());
					cell.setCellStyle(style);

					cell = row2.createCell(6);
					cell.setCellValue(p.getposition());
					cell.setCellStyle(style);
				}
			}

			// 第六步，将文件存到指定位置
			/*
			 * InputStreamReader read = new InputStreamReader(new
			 * FileInputStream( "config/filePath.txt")); BufferedReader br = new
			 * BufferedReader(read); String filePath = br.readLine();
			 * br.close(); // 读配置文件
			 * 
			 * String s; if (System.getProperty("os.name").equals("Mac OS X")) s
			 * = "/"; else s = "\\";
			 */

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

	public void endRepoInventory() {
		SysLog log = new SysLog();
		log.addSysLog("库存盘点");
	}

}
