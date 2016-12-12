package express.businesslogicService.transcenterRepoBLService;

import java.util.ArrayList;

import express.vo.RepoInfoVO;

public interface InitRepoBLService {

	public boolean initRepo(RepoInfoVO vo);
	
	public boolean changeRepoInfo(String orgID,RepoInfoVO vo);
	
	public boolean deleteRepo(String orgID);

	public ArrayList<RepoInfoVO> getAllRepo();

	/**
	 * 
	 * @param num
	 * @return 检查仓库每个区输入的排数是否合理，如果是正数，返回true，其他返回false
	 */
	public boolean isNumValid(String num);
	
	/**
	 * 
	 * @param orgID，传入的id是期初建账时选择的中转中心对应的id，一个中转中心只能有一个仓库
	 * @return 返回仓库是否重复
	 */
	public boolean checkReset(String orgID);

}
