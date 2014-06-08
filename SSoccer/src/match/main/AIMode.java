package match.main;

import match.data.MTeam;
import match.data.setDLocation;
import match.graphic.EditStrategy;
import match.graphic.GraphicMain;
import data.Team;

public class AIMode {

	public AIMode(Team team, Team teamAI) {
		MTeam home = new MTeam(team);
		MTeam away = new MTeam(teamAI);

		new setDLocation().setHomeTeam(home);
		new setDLocation().setAwayTeam(away);

		GraphicMain gm = new GraphicMain(home, away);
		new Thread(new AIModeThread(gm)).start();
		new EditStrategy(home, gm.homeInfo);
	}

}