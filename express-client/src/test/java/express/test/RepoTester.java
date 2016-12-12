package express.test;

import static org.junit.Assert.*;

import org.junit.Test;

import express.businessLogic.repoBL.AdjustRepo;
import express.po.Area;
import express.po.RepoInfoPO;
import express.po.RepoPosition;
import express.presentation.userUI.LoginUI;
import express.rmi.RMIClient;

public class RepoTester {

	@Test
	public void test() {
		try {
			RMIClient.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AdjustRepo a =new AdjustRepo();
		
		System.out.println(a.getPosition("0250", "001"));
		
		RepoPosition rp1 = new RepoPosition("001",Area.AIR,1,1,1,true);
		RepoPosition rp2 = new RepoPosition("002",Area.AIR,2,2,2,true);
		
		a.setRepoBlock("0250", rp1);
		a.setRepoBlock("0250", rp2);
		a.adjustRepo("0250", rp2, rp2);

		String[] list = a.getAllInDoc("0250");
		
		for(int i = 0;i<list.length;i++){
			System.out.println(list[i]);
		}
		
		System.out.println(a.getPosition("0250", "001").getrow());
	}
}
