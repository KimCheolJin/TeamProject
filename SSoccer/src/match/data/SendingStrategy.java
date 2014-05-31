package match.data;

import java.io.Serializable;
import data.Player;

public class SendingStrategy implements Serializable {

	public Player player[] = new Player[22];
	public int strategyA, strategyD, strategyT, strategyF;
	public int defNum, midNum, atkNum;
	
	public SendingStrategy(MTeam team){
		for(int i = 0; i < 22; i++){
			player[i] = new Player();
			player[i].name = team.player[i].name;
			player[i].shoot = team.player[i].shoot;
			player[i].dribble = team.player[i].dribble;
			player[i].pass = team.player[i].pass;
			player[i].stamina = team.player[i].stamina;
			player[i].speed = team.player[i].speed;
			player[i].tackle = team.player[i].tackle;
			player[i].steal = team.player[i].steal;
			player[i].gk = team.player[i].gk;
		}
		strategyA = team.strategyA;
		strategyD = team.strategyD;
		strategyT = team.strategyT;
		strategyF = team.strategyF;
		defNum = team.defNum;
		midNum = team.midNum;
		atkNum = team.atkNum;
	}
	
	public void getData(MTeam team){
		for(int i = 0; i < 22; i++){
			team.player[i].copy(player[i]);;
		}
		team.strategyA = strategyA;
		team.strategyD = strategyD;
		team.strategyT = strategyT;
		team.strategyF = strategyF;
		team.defNum = defNum;
		team.midNum = midNum;
		team.atkNum = atkNum;
	}
	
}
