package match.data;

import java.io.Serializable;

public class SendingData implements Serializable {
	
	public double homeLocation[][] = new double[11][2];
	public double awayLocation[][] = new double[11][2];
	public double balllocation[] = new double[3];
	public int homescore, awayscore;
	
	public void setData(MTeam home, MTeam away, Ball ball){
		for(int i = 0; i < 11; i++){
			homeLocation[i][0] = home.player[i].X;
			homeLocation[i][1] = home.player[i].Y;
			awayLocation[i][0] = away.player[i].X;
			awayLocation[i][1] = away.player[i].Y;
		}
		balllocation[0] = ball.X;
		balllocation[1] = ball.Y;
		balllocation[2] = ball.H;
		homescore = home.score;
		awayscore = away.score;
	}
	
	public void getData(MTeam home, MTeam away, Ball ball){
		for(int i = 0; i < 11; i++){
			home.player[i].X = homeLocation[i][0];
			home.player[i].Y = homeLocation[i][1];
			away.player[i].X = awayLocation[i][0];
			away.player[i].Y = awayLocation[i][1];
		}
		ball.X = balllocation[0];
		ball.Y = balllocation[1];
		ball.H = balllocation[2];
		home.score = homescore;
		away.score = awayscore;
	}
}
