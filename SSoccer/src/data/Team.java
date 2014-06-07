package data;

import java.io.Serializable;

import data.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Team implements Serializable {

	public ArrayList<Player> playerList = new ArrayList<Player>();
	public Player player[] = new Player[22];

	public String name;
	public int atkNum = 2;
	public int midNum = 4;
	public int defNum = 4;
	public int strategyA;
	public int strategyD;
 	public int strategyT;
	public int strategyF;
	public int colorR, colorG, colorB;
	
	
	public Team(){
		for(int i = 0; i < 22; i++){
			player[i] = new Player();
		}
	}
	
    public Team( String name, int sA, int sD, int sT, int sF, int cR, int cG, int cB){
    	this();
    	this.name=name;
		this.strategyA=sA;
		this.strategyD=sD;
		this.strategyT=sT;
		this.strategyF=sF;
		this.colorR=cR;
		this.colorG=cG;
		this.colorB=cB;
	}
    
    public void setPlayer(ArrayList<Player> playerList){
    	this.playerList=playerList;
    	setPlayer();
    }
    
    public void setPlayer() {
    	playerList.size();
    	for(int i = 0; i < playerList.size(); i++){
    		player[i] = playerList.get(i);
    	}
    	for(int i = playerList.size(); i < 22; i++){
    		player[i].setNull();
    	}
    }
    
    public String[]	printPlayer(){
    	String[] temp = new String[22];
    	for(int i=0; i < playerList.size(); i++){
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
    
    public Player getPlayer(int index){
    	try{
    		return playerList.get(index);
    	} catch(Exception e){}
    	return null;
    }

}
