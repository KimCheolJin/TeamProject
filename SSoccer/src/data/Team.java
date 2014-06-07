package data;

import java.io.Serializable;

import data.Player;
import java.util.ArrayList;

public class Team implements Serializable {
<<<<<<< HEAD
	//arraylist사용해야할듯
	public ArrayList<Player> player2;
	public Player player[] ;
=======
	public Player player[] = new Player[22];
>>>>>>> origin/master
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
    
    public void setPlayer(Player[] p){
    	for(int i = 0; i < 21; i++){
    		player[i] = p[i];
    	}
    	player[21].setNull();
    }
    
    public String[]	printPlayer(){
    	
    	String[] temp = new String[22];
    	
    	for(int i=0; i<22; i++){
    		temp[i] = player[i].toString();
    	}
    	
    	return temp;
    }
    
    //팀에 선수 추가
    public void addPlayer(Player p){
    	
    	player2.add(p);
    	
    }
    
    //팀에 선수 제거
    public void removePlayer(int index){
    	
    	player2.remove(index);
    	
    	for(int i=index; i<player2.size()-1; i++){
    		player2.add(player2.get(i+1));
    	}
    	
    	
    }

}
