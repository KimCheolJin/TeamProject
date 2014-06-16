package match.main;

import match.data.Ball;
import match.data.MTeam;
import match.data.setDLocation;
import match.graphic.GraphicMain;

public class MatchThread implements Runnable {

	public GraphicMain gm;
	MTeam home, away;
	Ball ball;
	private int fps = 0;

	public MatchThread(GraphicMain gm) {
		this.gm = gm;
		home = gm.home;
		away = gm.away;
		ball = gm.stadium.ball;
		ball.player = home.player[10];
		ball.leftAtk = 2;
	}

	public void run() {
		int gc = 0;
		new Thread(new Checkfps()).start();
		setStarting(home);
		setPlayerEye();
		gm.stadium.repaint();
		try {
			write();
			Thread.sleep(2000);
			while (true) {
				if(gc == 0){
					move();
					ball.move();
					gc = gameContinue();				
				} else {
					gc -= 1;
					setPlayerEye();
				}
				
				if(gm.score.t > 54000) break;
				
				if(!write()) break;
				fps += 1;
				gm.stadium.repaint();
				gm.score.rescore();
				Thread.sleep(1000 / 60);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	class Checkfps implements Runnable {
		public void run(){
			String title = gm.getTitle();
			while(true) {
				try {
					Thread.sleep(1000);
					gm.setTitle(title + " FPS : " +  fps);
					fps = 0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	void setPlayerEye(){
		for(int i = 0; i < 11; i++){
			home.player[i].setLength(ball);
			away.player[i].setLength(ball);
		}
	}
	
	public void setStarting(MTeam team){
		new setDLocation().setHomeTeam(home);
		new setDLocation().setAwayTeam(away);
		ball.X = 360;
		ball.Y = 215;
		ball.H = 0;
		team.player[9].X = 360;
		team.player[9].Y = 210;
		team.player[10].X = 360;
		team.player[10].Y = 220;
		ball.player = team.player[10];
	}
	
	public void setGoalkick(MTeam team){
		new setDLocation().setLocation(home);
		new setDLocation().setLocation(away);
		if(ball.leftAtk == 2) ball.X = 50;
		else ball.X = 670;
		ball.H = 0;
		ball.Y = team.player[0].Y;
		ball.player = team.player[0];

	}
	
	public boolean write(){
		return true;
	};

	public void move() {
		for (int i = 0; i < 11; i++) {
			home.player[i].move(ball, ball.leftAtk);
		}
		for (int i = 0; i < 11; i++) {
			away.player[i].move(ball, 2 - ball.leftAtk);
		}
		if (ball.leftAtk == 2 && ball.player != null)
			ball.player.act(ball, home);
		else if (ball.leftAtk == 0 && ball.player != null)
			ball.player.act(ball, away);
	}

	public int gameContinue() {
		if (ball.X < 0) {
			if (ball.Y > 175 && ball.Y < 255 && ball.H < 5) {
				away.score += 1;
				ball.leftAtk = 2;
				setStarting(home);
			} else {
				ball.leftAtk = 2;
				setGoalkick(home);
			}
			return 120;
		} else if (ball.X > 720) {
			if (ball.Y > 175 && ball.Y < 255 && ball.H < 5) {
				home.score += 1;
				ball.leftAtk = 0;
				setStarting(away);
			} else {
				ball.leftAtk = 0;
				setGoalkick(away);
			}
			return 120;
		}
		return 0;
	}

}
