package data;

import java.io.Serializable;

import data.Player;

public class Team implements Serializable {
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
			player[i] = new Player("");
			player[i].setNull();
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
    
    public void setPlayer(Player[] p){
    	for(int i = 0; i < 21; i++){
    		player[i] = p[i];
    	}
    	player[21].setNull();
    }

}
