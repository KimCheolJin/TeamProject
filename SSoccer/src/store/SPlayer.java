package store;

import data.Player;

public class SPlayer extends Player {

    int price;

	public SPlayer( int pN, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp, int price) {
		super(pN, n, s, d, p, st, tk, sl, sp, gk, exp);
		this.price = price;
	}

	public String toString() {

		//살짝 수정 모습좋게 보이기 위함 JLIST 에서 수정전에도 수평막대생김
		return "[" + name + "]==" + "[슛] " + shoot + " [드리블] " + dribble + " [패스] "
				+ pass + " [스테미너] " + stamina + " [태클] " + tackle + " [스틸] " + steal
				+ " [스피드] " + speed + " [GK능력치] " + gk + " [금액] " +price+ " [경험치] "
				+ exp;
	}
	
	public int getPrice(){
		return this.price;
	}

}
