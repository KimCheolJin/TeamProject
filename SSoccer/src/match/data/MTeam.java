package match.data;

import match.player.Attacker;
import match.player.Defender;
import match.player.Goalkeeper;
import match.player.Midfielder;
import data.Team;

public class MTeam extends Team {
	
	public MPlayer player[] = new MPlayer[22];
	public int score = 0;
	
	public MTeam(Team t) {
		super();
		name = t.name;
		setPosition(t);
		strategyA = t.strategyA;
		strategyD = t.strategyD;
		strategyT = t.strategyT;
		strategyF = t.strategyF;
		colorR = t.colorR;
		colorG = t.colorG;
		colorB = t.colorB;
	}

	private void setPosition(Team t) {
		for (int i = 0; i < 22; i++) {
			if (i == 0)
				player[i] = new Goalkeeper(t.player[i]);
			else if (i < defNum + 1)
				player[i] = new Defender(t.player[i]);
			else if (i < defNum + midNum + 1)
				player[i] = new Midfielder(t.player[i]);
			else if (i < 11)
				player[i] = new Attacker(t.player[i]);
			else
				player[i] = new MPlayer(t.player[i]);
		}
		player[21].setNull();
	}

}
