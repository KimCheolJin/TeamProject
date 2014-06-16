package match.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import data.TemporaryTeam;
import match.data.MTeam;
import match.data.setDLocation;
import match.graphic.GraphicMain;

public class StrategyThread implements Runnable {
	ObjectInputStream ois;
	MTeam team;
	GraphicMain gm;
	
	public StrategyThread (ObjectInputStream ois, MTeam team, GraphicMain gm){
		this.ois = ois;
		this.team = team;
		this.gm = gm;
		System.out.println("전송 준비 완료");
	}
	
	public void run() {
		while(true) {
			try {
				TemporaryTeam ss = (TemporaryTeam) ois.readObject();
				ss.getData(team);
				gm.homeInfo.draw();
				gm.awayInfo.draw();
				if(team.player[0].goalX == 720) new setDLocation().setHomeFomation(team);
				else new setDLocation().setAwayFomation(team);
			} catch (ClassNotFoundException | IOException e) {
				break;
			}
			
		}
		
	}

	
}