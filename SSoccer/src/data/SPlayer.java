package data;

public class SPlayer extends Player {

	int price;

	public SPlayer(String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp, int primaryNum) {
		super(n, s, d, p, st, tk, sl, sp, gk, exp, primaryNum);
		this.price = price;
		this.exp = exp;
	}

	public String toString() {

		return "[" + name + "]" + "[��]" + shoot + "[�帮��]" + dribble + "[�н�]"
				+ pass + "[���׹̳�]" + stamina + "[��Ŭ]" + tackle + "[��ƿ]" + steal
				+ "[���ǵ�]" + speed + "[GK�ɷ�ġ]" + gk + "[����]" + price + "[����ġ]"
				+ exp;
	}

}
