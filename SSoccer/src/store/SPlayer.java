package store;

import data.Player;

public class SPlayer extends Player {

	int price;

	public SPlayer(String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp, int pN, int price) {
		super(n, s, d, p, st, tk, sl, sp, gk, exp, pN);
		this.price = price;
	}

	public String toString() {

		return "[" + name + "]" + "[��]" + shoot + "[�帮��]" + dribble + "[�н�]"
				+ pass + "[���׹̳�]" + stamina + "[��Ŭ]" + tackle + "[��ƿ]" + steal
				+ "[���ǵ�]" + speed + "[GK�ɷ�ġ]" + gk + "[����]" + price + "[����ġ]"
				+ exp;
	}

}
