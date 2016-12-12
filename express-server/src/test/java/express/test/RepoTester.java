package express.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import express.data.repoData.RepoInfoIO;
import express.po.Area;
import express.po.RepoInfoPO;
import express.po.RepoPosition;

public class RepoTester {

	@Test
	public void test() {
		try {
			RepoInfoIO repo = new RepoInfoIO();
			
			RepoInfoPO r1 = new RepoInfoPO("0250",5,5,5,10);
			RepoInfoPO r2 = new RepoInfoPO("0100",5,5,5,10);
			RepoInfoPO r3 = new RepoInfoPO("0210",5,5,5,10);
			repo.addRepo(r1);
			repo.addRepo(r2);
			repo.addRepo(r3);
			repo.writeAllRepo();
			
			
			
//			RepoPosition rp = new RepoPosition("001",Area.AIR,1,1,1,true);
//			
//			RepoPosition rp2 = new RepoPosition("001",Area.AIR,2,2,2,true);
//			
//			repo.addBlock("0250", rp);
//			repo.deleteBlock("0250", rp);
//			repo.addBlock("0250", rp2);
//			RepoInfoPO po = repo.getRepo("0250");
//			
//			System.out.println(po.getRepoPosition().get(0).getrow());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
