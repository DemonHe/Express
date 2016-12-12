package express.vo;

import java.util.ArrayList;

import express.po.RepoPosition;

public class RepoInfoVO {

	private String city;//orgID
	private int airShelfSize;//航运区排数
	private int trainShelfSize;//铁运区排数
	private int truckShelfSize;//汽运区排数
	private int flexibleShelfSize;//机动区排数
	private int airSize;//航运区总位数
	private int trainSize;//铁运区总位数
	private int truckSize;//汽运区总位数
	private int airSum;//航运区库存量
	private int trainSum;//铁运区库存量
	private int truckSum;//汽运区库存量
	private int height=3;//一个排的层数
	private int width=4;//一个排一架的位数
	private ArrayList<RepoPosition> repoPosition;//已用的库存位置
	private int repoSum;//库存总数
	
	public RepoInfoVO(String city,int airShelfSize,int trainShelfSize,
			int truckShelfSize,int flexibleShelfSize){
		this.city=city;
		this.airShelfSize=airShelfSize;
		this.trainShelfSize=trainShelfSize;
		this.truckShelfSize=truckShelfSize;
		this.flexibleShelfSize=flexibleShelfSize;
		this.airSize=airShelfSize*12;
		this.trainSize=trainShelfSize*12;
		this.truckSize=truckShelfSize*12;
		this.airSum=0;
		this.trainSum=0;
		this.truckSum=0;
		this.repoPosition=new ArrayList<RepoPosition>();
		this.repoSum=0;
	}
	
	public RepoInfoVO(String city,int airShelfSize,int trainShelfSize,
			int truckShelfSize,int flexibleShelfSize,int airSum,
			int trainSum,int truckSum,ArrayList<RepoPosition> repo,int repoSum){
		this.city=city;
		this.airShelfSize=airShelfSize;
		this.trainShelfSize=trainShelfSize;
		this.truckShelfSize=truckShelfSize;
		this.flexibleShelfSize=flexibleShelfSize;
		this.airSize=airShelfSize*12;
		this.trainSize=trainShelfSize*12;
		this.truckSize=truckShelfSize*12;
		this.airSum=airSum;
		this.trainSum=trainSum;
		this.truckSum=truckSum;
		this.repoPosition=repo;
		this.repoSum=repoSum;
	}
	
	public RepoInfoVO(){
		city=null;
		airShelfSize=0;
		trainShelfSize=0;
		truckShelfSize=0;
		flexibleShelfSize=0;
		airSize=0;
		trainSize=0;
		truckSize=0;
		airSum=0;
		trainSum=0;
		truckSum=0;
		repoPosition=new ArrayList<RepoPosition>();
		repoSum=0;
	}
	public String getCity(){
		return city;
	}
	public int getAirShelfSize(){
		return airShelfSize;
	}
	public int getTrainShelfSize(){
		return trainShelfSize;
	}
	public int getTruckShelfSize(){
		return truckShelfSize;
	}
	public int getFlexibleShelfSize(){
		return flexibleShelfSize;
	}
	public int getAirSize(){
		return airSize;
	}
	public int getTrainSize(){
		return trainSize;
	}
	public int getTruckSize(){
		return truckSize;
	}
	public int getAirSum(){
		return airSum;
	}
	public int getTrainSum(){
		return trainSum;
	}
	public int getTruckSum(){
		return truckSum;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public ArrayList<RepoPosition> getRepoPosition(){
		return repoPosition;
	}
	public int getRepoSum(){
		return repoSum;
	}
	public void changeRepoSum(int n){
		repoSum+=n;
	}
	public void setAirShelfSize(int size){
		airShelfSize=size;
		airSize=size*12;
	}
	public void setTrainShelfSize(int size){
		trainShelfSize=size;
		trainSize=size*12;
	}
	public void setTruckShelfSize(int size){
		truckShelfSize=size;
		truckSize=size*12;
	}
	public void setFlexibleShelfSize(int size){
		flexibleShelfSize=size;
	}
	
	public void changeAirSum(int n){
		airSum+=n;
	}
	public void changeTrainSum(int n){
		trainSum+=n;
	}
	public void changeTruckSum(int n){
		truckSum+=n;
	}
	public void addRepoPosition(RepoPosition position){
		repoPosition.add(position);
	}
	public void deleteRepoPosition(int index){
		repoPosition.remove(index);
	}
	public void deleteRepoPosition(RepoPosition position){
		repoPosition.remove(position);
	}
}
