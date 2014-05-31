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

		return "[" + name + "]" + "[슛]" + shoot + "[드리블]" + dribble + "[패스]"
				+ pass + "[스테미너]" + stamina + "[태클]" + tackle + "[스틸]" + steal
				+ "[스피드]" + speed + "[GK능력치]" + gk + "[가격]" + price + "[경험치]"
				+ exp;
	}

}
