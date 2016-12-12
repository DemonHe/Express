package express.businessLogic.repoBL;

import express.vo.RepoPositionVO;

public class MockRepoController {
	public boolean  checkRepoBlockUsed(RepoPositionVO vo) {
		System.out.println("checkRepoBlockUsedâ€¦â??");
		return true;
	}
	public boolean setRepoBlock(RepoPositionVO vo){
		System.out.println("setRepoBlockâ€¦â??");
		return true;
	}
	public boolean alarm(){
		System.out.println("alarmâ€¦â??");
		return true;
	}
	public boolean inventoryRepo() {
		System.out.println("inventoryRepoâ€¦â??");
		return true;
	}
}
