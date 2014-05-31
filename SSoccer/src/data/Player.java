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
	public int exp;
	public int primaryNum;

	public Player(String name) {
		this.name = name;
	}

	public Player() {
	}

	public Player(String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp, int primaryNum) {
		this.name = n;
		this.shoot = s;
		this.dribble = d;
		this.pass = p;
		this.stamina = st;
		this.tackle = tk;
		this.steal = sl;
		this.speed = sp;
		this.gk = gk;
		this.exp = exp;
		this.primaryNum = primaryNum;
	}

	public void setNull() {
		name = null;
		shoot = 0;
		dribble = 0;
		pass = 0;
		stamina = 0;
		tackle = 0;
		steal = 0;
		speed = 0;
		gk = 0;
		exp = 0;
		primaryNum = 0;
	}

	
	public void copy(Player p) {
		name = p.name;
		shoot = p.shoot;
		dribble = p.dribble;
		pass = p.pass;
		stamina = p.stamina;
		speed = p.speed;
		tackle = p.tackle;
		steal = p.steal;
		gk = p.gk;	
		exp = p.exp;
		primaryNum = p.primaryNum;
	}
	
}
