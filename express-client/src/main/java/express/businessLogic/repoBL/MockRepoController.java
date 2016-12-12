package express.businessLogic.repoBL;

import express.vo.RepoPositionVO;

public class MockRepoController {
	public boolean  checkRepoBlockUsed(RepoPositionVO vo) {
		System.out.println("checkRepoBlockUsed……");
		return true;
	}
	public boolean setRepoBlock(RepoPositionVO vo){
		System.out.println("setRepoBlock……");
		return true;
	}
	public boolean alarm(){
		System.out.println("alarm……");
		return true;
	}
	public boolean inventoryRepo() {
		System.out.println("inventoryRepo……");
		return true;
	}
}
