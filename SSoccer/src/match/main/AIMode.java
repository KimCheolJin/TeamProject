package match.main;

import client.ClientK;
import match.data.MTeam;
import match.data.setDLocation;
import match.graphic.EditStrategy;
import match.graphic.GraphicMain;
import data.Team;

public class AIMode {

	public AIMode(Team team, Team teamAI, ClientK clientK) {
		MTeam home = new MTeam(team);
		MTeam away = new MTeam(teamAI);

		new setDLocation().setHomeTeam(home);
		new setDLocation().setAwayTeam(away);

		GraphicMain gm = new GraphicMain(home, away);
		new Thread(new AIModeThread(gm, clientK)).start();
		gm.editStrategy = new EditStrategy(home, gm.homeInfo);
	}

}