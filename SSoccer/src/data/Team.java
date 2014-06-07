package data;

import java.io.Serializable;

import data.Player;
import java.util.ArrayList;

public class Team implements Serializable {
	//arraylist����ؾ��ҵ�
	public ArrayList<Player> player2;
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
		for(int i = 0; i < 22; i++){
			player[i] = new Player("");
			player[i].setNull();
		}
	}
	
    public Team( String name, int sA, int sD, int sT, int sF, int cR, int cG, int cB){
    	this.player=null; //this(); �� �ϸ� ���� ���� �̰ɷ� ���� �ذ�
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
    
    public String[]	printPlayer(){
    	
    	String[] temp = new String[22];
    	
    	for(int i=0; i<22; i++){
    		temp[i] = player[i].toString();
    	}
    	
    	return temp;
    }
    
    //���� ���� �߰�
    public void addPlayer(Player p){
    	
    	player2.add(p);
    	
    }
    
    //���� ���� ����
    public void removePlayer(int index){
    	
    	player2.remove(index);
    	
    	for(int i=index; i<player2.size()-1; i++){
    		player2.add(player2.get(i+1));
    	}
    	
    	
    }

}
