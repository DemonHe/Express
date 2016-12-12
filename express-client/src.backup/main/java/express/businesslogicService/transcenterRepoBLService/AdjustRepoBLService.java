package express.businesslogicService.transcenterRepoBLService;

import express.po.Area;
import express.po.RepoPosition;
import express.vo.RepoPositionVO;

public interface AdjustRepoBLService {
	/**
	 * 
	 * @param orgID
	 * @param position
	 * @return 使用返回true，未使用返回false
	 */
	public boolean checkRepoBlockUsed(String orgID, RepoPosition position);

	/**
	 * 
	 * @param orgID
	 * @param position
	 * @return 将position设置为使�?
	 */
	public boolean setRepoBlock(String orgID, RepoPosition position);
	
	/**
	 * 
	 * @param orgID
	 * @param position
	 * @return
	 */
	public boolean freeRepoBlock(String orgID, RepoPosition position);

	/**
	 * 
	 * @param position
	 * @return 仓库position�?在的区容量是否到90%，达到返回true，未达到返回false
	 */
	public boolean alarm(String orgID, RepoPosition position);

	/**
	 * 
	 * @param oldPosition
	 * @param newPosition
	 * @return 将旧的位置替换为新的位置
	 */
	public boolean adjustRepo(String orgID, RepoPosition oldPosition,
			RepoPosition newPosition);

	/**
	 * 
	 * @param orgID
	 * @param area
	 * @return 返回选中区的�?有排号，“第n排�??
	 */
	public String[] getRow(String orgID, Area area);
	
	/**
	 * 
	 * @param orgID
	 * @param area
	 * @return
	 */
	public String[] getAllRow(String orgID, Area area);

	/**
	 * 
	 * @param orgID
	 * @param area
	 * @param row
	 * @return 返回3*4的架，每�?个String的�?�为“架�?+�?+�?+位号”，如果该位置被占，在String前加�?#�?
	 */
	public String[] getPosition(String orgID, Area area, int row);

	/**
	 * 
	 * @param orgID
	 * @param orderID
	 * @return
	 */
	public boolean checkIn(String orgID, String orderID);

	/**
	 * 
	 * @param orgID
	 * @return
	 */
	public String[] getAllInDoc(String orgID);

	/**
	 * 
	 * @param orgID
	 * @param orderID
	 * @return
	 */
	public RepoPosition getPosition(String orgID, String orderID);

	/**
	 * 保存仓库信息
	 */
	public void recordRepo();

	/**
	 * 记录“仓库调整�?�到日志，并保存仓库信息
	 */
	public void endRepoManage();
}
