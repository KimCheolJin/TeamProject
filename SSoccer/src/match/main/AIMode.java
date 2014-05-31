package match.main;

import match.data.MTeam;
import match.data.setDLocation;
import match.graphic.EditStrategy;
import match.graphic.GraphicMain;
import data.Player;
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

	// �ӽ� �޼ҵ�. ���� �� ���� ���������� ����.
	public static Team makeTeam(String tName) {
		Team team = new Team(tName);
		for (int i = 0; i < 22; i++) {
			String pname = team.name + " " + i + "��";
			team.player[i] = new Player(pname);
			team.player[i].shoot = 70;
			team.player[i].dribble = 70;
			team.player[i].pass = 70;
			team.player[i].stamina = 70;
			team.player[i].speed = 70;
			team.player[i].tackle = 70;
			team.player[i].steal = 70;
			team.player[i].gk = 70;
		}
		team.colorR = (int) (Math.random() * 256);
		team.colorG = (int) (Math.random() * 256);
		team.colorB = (int) (Math.random() * 256);
		return team;
	}

	public static void main(String args[]) {
		new AIMode(makeTeam("����"), makeTeam("�ǽ�Ƽ"));
	}

}