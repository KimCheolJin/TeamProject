package data;

import java.io.Serializable;

import match.data.MPlayer;
import match.data.MTeam;

public class TemporaryTeam implements Serializable {
	// 팀 전략 설정에서 일시적으로 사용하는 데이터.
	// 적용 버튼을 누르면 여기에 저장된 정보가 Team이나 MTeam에 저장된다.
	
	public Player player[] = new Player[22];
	public int strategyA, strategyD, strategyT, strategyF;
	public int defNum, midNum, atkNum;
	
	public TemporaryTeam(Team team){
		for(int i = 0; i < 22; i++){
			player[i] = new Player();
			player[i].copy(team.player[i]);
		}
		strategyA = team.strategyA;
		strategyD = team.strategyD;
		strategyT = team.strategyT;
		strategyF = team.strategyF;
		defNum = team.defNum;
		midNum = team.midNum;
		atkNum = team.atkNum;
	}
	
	public TemporaryTeam(MTeam team){
		for(int i = 0; i < 22; i++){
			player[i] = new MPlayer(team.player[i]);
		}
		strategyA = team.strategyA;
		strategyD = team.strategyD;
		strategyT = team.strategyT;
		strategyF = team.strategyF;
		defNum = team.defNum;
		midNum = team.midNum;
		atkNum = team.atkNum;
	}
	
	public void getData(Team team){
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
