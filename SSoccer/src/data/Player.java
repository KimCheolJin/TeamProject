package data;

import java.io.Serializable;

public class Player implements Serializable {
	
	public int primaryNum;
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


	public Player() {
		name = "";
	}

	public Player(int primaryNum, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp) {
		
		this.primaryNum = primaryNum;
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
		
	}

	public void setNull() {
		name = "";//공백칸으로 둔다
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
	
	public String toString() {

		//살짝 수정 모습좋게 보이기 위함 JLIST 에서 수정전에도 수평막대생김
		return "[" + name + "]==" + "[슛] " + shoot + " [드리블] " + dribble + " [패스] "
				+ pass + " [스테미너] " + stamina + " [태클] " + tackle + " [스틸] " + steal
				+ " [스피드] " + speed + " [GK능력치] " + gk +  " [경험치] "
				+ exp;
	}
	
	
	
}
