package express.businessLogic.infoManageBL;

import java.awt.List;
import java.rmi.RemoteException;
import java.util.ArrayList;

import express.dataService.strategyDataService.DistanceDataService;
import express.po.CityDistancePO;
import express.vo.CityDistanceVO;
import express.rmi.RMIClient;

public class DistanceManager {

	public double getTwoCityDistance(String city1, String city2) {
		DistanceDataService rmiObj = RMIClient.getDistanceStrategy();
		try {
			ArrayList<CityDistancePO> arr = rmiObj.getAllDistanceStrategy();
			int len = arr.size();
			for (int i = 0; i < len; i++) {
				CityDistancePO po = arr.get(i);
				if (po.getCity1().equals(city1) && po.getCity2().equals(city2)) {
					return po.getDistance();
				}
				if (po.getCity1().equals(city2) && po.getCity2().equals(city1)) {
					return po.getDistance();
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return -1; // not find
	}

	public boolean setTwoCityDistance(CityDistanceVO vo) { // 改变现有的城市距�?
		DistanceDataService rmiObj = RMIClient.getDistanceStrategy();
		CityDistancePO po = new CityDistancePO(vo.getCity1(), vo.getCity2(),
				vo.getDistance());
		try {
			rmiObj.changeDistanceStrategy(po);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean addDistanceStrategy(ArrayList<CityDistanceVO> volist) {
		DistanceDataService rmiObj = RMIClient.getDistanceStrategy();

		int len = volist.size();
		/*
		 * WARNING!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * 传入的Arraylist顺序必须要和原来读出来的城市顺序�?致！！！！！！！�? ex. 原来顺序�? 南京 北京 上海 广州 现添加西�?
		 * 务必将传入arraylist的顺序写为： 西安在前！！！！！！ 西安－－北京 888km 西安－－上海 888km 西安－－广州
		 * 888km 西安－－南京 888km
		 * 
		 * 
		 * 
		 * 否则会死！！！！！！！！�?
		 */

		try {
			int currsize = getAllCity().size();
			int nowsize = volist.size();
			if (currsize != nowsize) {
				return false;
			}

			ArrayList<CityDistancePO> list = rmiObj.getAllDistanceStrategy();
			int numbers = list.size();
			int skip = (int) Math.sqrt(numbers);

			int i = skip;

			for (CityDistanceVO vo : volist) {
				CityDistancePO po = new CityDistancePO(vo.getCity2(),
						vo.getCity1(), vo.getDistance()); // reverse
				rmiObj.addDistanceStrategy(po, i); // insert
				i += skip;
			}

			for (CityDistanceVO vo : volist) {
				CityDistancePO po = new CityDistancePO(vo.getCity1(),
						vo.getCity2(), vo.getDistance());
				rmiObj.addDistanceStrategy(po);
			}

			// itself
			CityDistancePO po = new CityDistancePO(volist.get(0).getCity1(),
					volist.get(0).getCity1(), 30);
			rmiObj.addDistanceStrategy(po);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// 每次添加�?定要传入�?个完整的包！�?
	// Arraylist<CityPO>
	//

	public boolean deleteDistanceStrategy(String city) {
		DistanceDataService rmiObj = RMIClient.getDistanceStrategy();
		try {
			rmiObj.deleteDistanceStrategy(city);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 删除会把�?有的关于该城市的信息�?并删�?

	public ArrayList<String> getAllCity() {
		DistanceDataService rmiObj = RMIClient.getDistanceStrategy();
		ArrayList<CityDistancePO> list;
		try {
			list = rmiObj.getAllDistanceStrategy();
			ArrayList<String> citylist = new ArrayList<String>();
			int len = list.size();
			int skip = (int) Math.sqrt(len);

			for (int i = 0; i < len; i += skip) {
				citylist.add(list.get(i).getCity1());
			}
			return citylist;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void endDistance() {
		DistanceDataService rmiObj = RMIClient.getDistanceStrategy();
		try {
			rmiObj.writeAllDistanceStrategy();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			RMIClient.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DistanceManager dm = new DistanceManager();
		ArrayList<String> lis = dm.getAllCity();
		for (String city : lis) {
			System.out.println(city);
		}

		System.out.println(dm.getTwoCityDistance("上海", "广州"));

		CityDistanceVO vo = new CityDistanceVO("上海", "广州", 8989);

		dm.setTwoCityDistance(vo);
		System.out.println(dm.getTwoCityDistance("广州", "上海"));

		// dm.deleteDistanceStrategy("北京");
		//
		// lis=dm.getAllCity();
		// for(String city:lis){
		// System.out.println(city);
		// }
		// System.out.println(lis.size());

	}
}
