package data;

import java.io.Serializable;

import data.Player;

import java.util.ArrayList;

public class Team implements Serializable {

	public ArrayList<Player> playerList = new ArrayList<Player>();
	public Player player[] = new Player[22];

	public String name;
	public int strategyA;
	public int strategyD;
 	public int strategyT;
	public int strategyF;
	public int colorR, colorG, colorB;
	
	public int defNum;
	public int midNum;
	public int atkNum;
		
	public static String fmt[] = {"2-3-5", "3-4-3", "4-3-3", "3-5-2", "4-4-2", "5-3-2", "6-3-1"};
	
    public Team( String name, int sA, int sD, int sT, int sF, int cR, int cG, int cB){
    	for(int i = 0; i < 22; i++)
    		player[i] = new Player();
    	this.strategyA=sA;
		this.strategyD=sD;
		this.strategyT=sT;
		this.strategyF=sF;
		this.name=name;
		this.colorR=cR;
		this.colorG=cG;
		this.colorB=cB;
		setFormation();
	}
    
	private void setFormation() {
		defNum = fmt[strategyF].charAt(0) - 48;
		midNum = fmt[strategyF].charAt(2) - 48;
		atkNum = fmt[strategyF].charAt(4) - 48;	
	}
    
    public void setPlayer(ArrayList<Player> playerList){
    	this.playerList=playerList;
    	setPlayer();
    }
    
    public void setPlayer() {

    	for(int i = 0; i < playerList.size(); i++){
    		player[i] = playerList.get(i);
    	}
    	for(int i = playerList.size(); i < 22; i++){
    		player[i].setNull();
    	}
    }
    
    public String[]	printPlayer(){
    	String[] temp = new String[playerList.size()];
    	for(int i=0; i<playerList.size(); i++){
    		temp[i] = player[i].toString();
    	}
    	return temp;
    }
    
    //팀에 선수 추가
    public void addPlayer(Player p){
    	playerList.add(p);
    	setPlayer();
    }
    
    //팀에 선수 제거
    public void removePlayer(int index){
    	playerList.remove(index);
    	setPlayer();
    }
    
    //변수대신 list 이용해서 그냥 크기 반환
    public int getNumberOfPlayer(){
    	return playerList.size();
    }
    
    //index의 해당하는 보유선수 반환
    public Player getPlayer(int index){
    	try{
    		return playerList.get(index);
    	} catch(Exception e){}
    	return null;
    }

}
