package data;

import java.io.Serializable;

import data.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Team implements Serializable {

	//arraylist����ؾ��ҵ�
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
    
    public void setPlayer(ArrayList<Player> p){
    	
    	this.player2=p;
    	player = new Player[player2.size()];
    	this.listToArray();
    	
    	//�̺κ� �������� �ϴ� �ּ�ó���߾��
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
    
    //�����迭 list�� ��ȯ
    public void arrayToList(){
    	
    	Collections.addAll(player2, player);
    	
    }
    
    //��������Ʈ �迭�� ��ȯ
    public void listToArray(){
    	
    	player = player2.toArray(new Player[player2.size()]);
    	
    }
    
    //���� ���� �߰�
    public void addPlayer(Player p){
    	
    	this.arrayToList();
    	player2.add(p);
    	this.listToArray();
    }
    
    //���� ���� ����
    public void removePlayer(int index){
    	
    	this.arrayToList();
    	
    	player2.remove(index);
    	
    	this.listToArray();
    	
    }
    
    //������� list �̿��ؼ� �׳� ũ�� ��ȯ
    public int getNumberOfPlayer(){
    	
    	this.arrayToList();
    	return this.player2.size();
    }
    
    public Player getPlayer(int index){
    	
    	this.arrayToList();
    	return player2.get(index);
    }

}
