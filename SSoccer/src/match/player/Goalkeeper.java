package match.player;

import data.Player;
import match.data.Ball;
import match.data.MPlayer;
import match.data.MTeam;

public class Goalkeeper extends MPlayer {

	public Goalkeeper(Player p) {
		super(p);
	}

	public Goalkeeper(MPlayer p) {
		super(p);
	}
	
	public void act(Ball ball, MTeam team) {
		lenX = goalX - X;
		lenY = goalY - Y;
		length = Math.sqrt(lenX * lenX + lenY * lenY);
		
		ball.X = X + 10 * lenX / length;
		ball.Y = Y + 10 * lenY / length;


		if(delay > 0)
			delay -= 1;
		else {
			check();

			run(goalX, goalY);
			
			pass(ball);
		}
	}

	public void atk(Ball ball) {
		run(dX, dY);
	}
	
	public void pass(Ball ball) {
		ball.player = null;
		ball.leftAtk = 1;
		ball.H = gk / 10;
		
		if(360 > ball.X)
			ball.vX = 7 + (Math.random() * (100 - pass)) / 100;
		else
			ball.vX = -7 - (Math.random() * (100 - pass)) / 100;
		
		ball.vY = Math.random() * 8 - 4;
		
		if(ball.vX > 0)
			ball.vH = ball.vX / 12;
		if(ball.vX < 0)
			ball.vH = - ball.vX / 12;
	}
	
	public void def(Ball ball) {
		if (80 > length) {
			if(length < 10 && ball.player != null){
				delay = 20;
				ball.player.delay = 60;
				ball.player = this;
				ball.leftAtk = 2 - ball.leftAtk;
			}
			else if(length < 10 + (gk + steal) / 30.0 && ball.player == null && ball.H < gk / 10){
				delay = 20;
				ball.player = this;
				ball.leftAtk = (int) goalX / 360;
				ball.H = 0;
			}
			else {
				run(ball.X, ball.Y);
			}
		} else {
			dX = 720 - goalX - 40 * (720-goalX-ball.X) / Math.sqrt
					((720-goalX-ball.X)*(720-goalX-ball.X) + (ball.Y - 215)*(ball.Y - 215));
			dY = 215 + 40 * (ball.Y - 215) / Math.sqrt
					((720-goalX-ball.X)*(720-goalX-ball.X) + (ball.Y - 215)*(ball.Y - 215));
			run(dX, dY);
		}
	}
	
}
