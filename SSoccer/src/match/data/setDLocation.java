package match.data;

import match.player.Attacker;
import match.player.Defender;
import match.player.Midfielder;

public class setDLocation {
	
	public MTeam team;
	
	public void setHomeTeam(MTeam team){
		this.team = team;
		for(int i = 0; i < 11; i++){
			if (i == 0)
				setPlayer(i, 40, 215);
			else if (i < team.defNum + 1)
				setPlayer(i, 120, 430 / (team.defNum + 1) * i);
			else if (i < team.defNum + team.midNum + 1)
				setPlayer(i, 252 + team.strategyT * 10, 430 / (team.midNum + 1) * (i - team.defNum));
			else
				setPlayer(i, 404 + team.strategyT * 20, 430 / (team.atkNum + 1) * (team.atkNum - 10 + i));
			team.player[i].goalX = 720;
		}
	}
	
	public void setHomeFomation(MTeam team){
		this.team = team;
		for(int i = 0; i < 11; i++){
			if (i == 0) 
				setPlayerD(i, 40, 215);
			else if (i < team.defNum + 1) {
				team.player[i] = new Defender(team.player[i]);
				setPlayerD(i, 70 + team.strategyT * 10, 430 / (team.defNum + 1) * i);
			} else if (i < team.defNum + team.midNum + 1){
				team.player[i] = new Midfielder(team.player[i]);			
				setPlayerD(i, 237 + team.strategyT * 15, 430 / (team.midNum + 1) * (i - team.defNum));
			} else {
				team.player[i] = new Attacker(team.player[i]);
				setPlayerD(i, 404 + team.strategyT * 20, 430 / (team.atkNum + 1) * (team.atkNum - 10 + i));
			}
		}
	}
	
	public void setAwayTeam(MTeam team){
		setHomeTeam(team);
		for(int i = 0; i < 11; i++){
			team.player[i].dX = 720 - team.player[i].dX;
			team.player[i].X = 720 - team.player[i].X;
			team.player[i].goalX = 0;
		}
	}
	
	public void setAwayFomation(MTeam team){
		this.team = team;
		setHomeFomation(team);
		for(int i = 0; i < 11; i++){
			team.player[i].dX = 720 - team.player[i].dX;
		}
	}
	
	public void setLocation(MTeam team){
		this.team = team;
		for(int i = 0; i < 11; i++){
			setPlayerL(i);
		}
	}
	

	private void setPlayer(int i, int x, int y) {
		team.player[i].dX = x;
		team.player[i].X = x / 2;
		team.player[i].dY = y;
		team.player[i].Y = y;
	}
		
	private void setPlayerD(int i, int x, int y) {
		team.player[i].dX = x;
		team.player[i].dY = y;
	}
	
	private void setPlayerL(int i) {
		team.player[i].X = team.player[i].dX;
		team.player[i].Y = team.player[i].dY;
	}
}
