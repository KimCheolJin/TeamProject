package data;

import java.io.Serializable;

public class Player implements Serializable {
	public String name;
	public int shoot;
	public int dribble;
	public int pass;
	public int stamina;
	public int speed;
	public int tackle;
	public int steal;
	public int gk;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(){}

	public void setNull() {
		name = null;
		shoot = 0;
		dribble = 0;
		pass = 0;
		stamina = 0;
		tackle = 0;
		steal = 0;
		gk = 0;
	}

}
