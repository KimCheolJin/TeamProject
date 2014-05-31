package data;

import java.io.Serializable;

import data.Player;

public class Team implements Serializable {
	public Player player[] = new Player[22];
	public String name;
	public int atkNum = 2;
	public int midNum = 4;
	public int defNum = 4;
	public int strategyA = 5;
	public int strategyD = 5;
	public int strategyT = 5;
	public int strategyF = 4;
	public int colorR, colorG, colorB;
	
	public Team(String name) {
		this.name = name;
		for(int i = 0; i < 22; i++){
			player[i] = new Player("");
			player[i].setNull();
		}
	}

}
