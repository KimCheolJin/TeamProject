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

		//��¦ ���� ������� ���̱� ���� JLIST ���� ���������� ���򸷴����
		return "[" + name + "]==" + "[��] " + shoot + " [�帮��] " + dribble + " [�н�] "
				+ pass + " [���׹̳�] " + stamina + " [��Ŭ] " + tackle + " [��ƿ] " + steal
				+ " [���ǵ�] " + speed + " [GK�ɷ�ġ] " + gk + " [�ݾ�] " +price+ " [����ġ] "
				+ exp;
	}
	
	public int getPrice(){
		return this.price;
	}

}
