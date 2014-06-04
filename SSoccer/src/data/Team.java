package data;

import java.io.Serializable;

import data.Player;

public class Team implements Serializable {
	public Player player[] ;
	public String name;
	public int atkNum = 2;
	public int midNum = 4;
	public int defNum = 4;
	public int strategyA; //5
	public int strategyD; //5
 	public int strategyT; //5
	public int strategyF;
	public int colorR, colorG, colorB;
	
	
	public Team(){
		
	}
	
	public Team(String name) {
		this.name = name;
		for(int i = 0; i < 22; i++){
			player[i] = new Player("");
			player[i].setNull();
		}
	}
	
    public Team( String name, int sA, int sD, int sT, int sF, int cR, int cG, int cB){
	
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
    	this.player=p;
    }

}
