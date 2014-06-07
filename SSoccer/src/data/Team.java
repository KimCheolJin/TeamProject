package data;

import java.io.Serializable;

import data.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Team implements Serializable {

	//arraylist사용해야할듯
	public ArrayList<Player> player2 = new ArrayList<Player>();
	public Player player[];

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
			player[i] = new Player("");
			player[i].setNull();
		}
	}
	
    public Team( String name, int sA, int sD, int sT, int sF, int cR, int cG, int cB){
    	this.player=null; //this(); 로 하면 오류 떠서 이걸로 오류 해결
    	this.name=name;
		this.strategyA=sA;
		this.strategyD=sD;
		this.strategyT=sT;
		this.strategyF=sF;
		this.colorR=cR;
		this.colorG=cG;
		this.colorB=cB;
	}
    
    public void setPlayer(ArrayList<Player> p){
    	
    	this.player2=p;
    	player = new Player[player2.size()];
    	this.listToArray();
    	
    	//이부분 에러떠서 일단 주석처리했어요
    	/* for(int i = 0; i < 21; i++){
    		player[i] = p[i];
    	}
    	player[21].setNull(); */ 
    }
    
    public String[]	printPlayer(){
    	
    	String[] temp = new String[22];
    	
    	for(int i=0; i<22; i++){
    		temp[i] = player[i].toString();
    	}
    	
    	return temp;
    }
    
    //선수배열 list로 변환
    public void arrayToList(){
    	
    	Collections.addAll(player2, player);
    	
    }
    
    //선수리스트 배열로 변환
    public void listToArray(){
    	
    	player = player2.toArray(new Player[player2.size()]);
    	
    }
    
    //팀에 선수 추가
    public void addPlayer(Player p){
    	
    	this.arrayToList();
    	player2.add(p);
    	this.listToArray();
    }
    
    //팀에 선수 제거
    public void removePlayer(int index){
    	
    	this.arrayToList();
    	
    	player2.remove(index);
    	
    	this.listToArray();
    	
    }
    
    //변수대신 list 이용해서 그냥 크기 반환
    public int getNumberOfPlayer(){
    	
    	this.arrayToList();
    	return this.player2.size();
    }
    
    public Player getPlayer(int index){
    	
    	this.arrayToList();
    	return player2.get(index);
    }

}
