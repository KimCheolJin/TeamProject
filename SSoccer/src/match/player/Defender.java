package match.player;

import data.Player;
import match.data.Ball;
import match.data.MPlayer;
import match.data.MTeam;

public class Defender extends MPlayer {

	public Defender(Player p) {
		super(p);
	}

	public Defender(MPlayer p) {
		super(p);
	}
	
	public void act(Ball ball, MTeam team) {
		if(delay > 0)
			delay -= 1;
		else {
			lenX = goalX - X;
			lenY = goalY - Y;
			length = Math.sqrt(lenX * lenX + lenY * lenY);
			
			
			ball.X = X + 10 * lenX / length;
			ball.Y = Y + 10 * lenY / length;
	
			check();
			
			if(Math.random() * 120 < 1){
				for(int i = 10; i >= 0; i--){
				lenX = team.player[i].X - X;
				lenY = team.player[i].Y - Y;
				if(team.player[i] == this)
					continue;
					if(Math.sqrt(lenX * lenX + lenY * lenY) < pass * 3){
						if(Math.sqrt(lenX * lenX + lenY * lenY) > pass) {
							pass(ball, team.player[i]);
							break;
						}
					}
				}
			}
			
			run(goalX, goalY);
			
			lenX = goalX - X;
			lenY = goalY - Y;
		}
	}

	public void pass(Ball ball, MPlayer player) {
		ball.player = null;
		ball.leftAtk = 1;
		ball.H = 0.01;
		
		if(player.X - ball.X > 0)
			ball.vX = Math.sqrt(player.X - ball.X) * 0.45;
		else
			ball.vX = -Math.sqrt(ball.X - player.X) * 0.45;
		ball.vX *= (100 - pass) * (Math.random() - 0.5) / 50 + 1;
		if(player.Y - ball.Y > 0)
			ball.vY = Math.sqrt(player.Y - ball.Y) * 0.45;
		else
			ball.vY = -Math.sqrt(ball.Y - player.Y) * 0.45;
		ball.vY *= (100 - pass) * (Math.random() - 0.5) / 50 + 1;
		
		if(ball.vX > 0)
			ball.vH = ball.vX / 12;
		if(ball.vX < 0)
			ball.vH = - ball.vX / 12;
		if(ball.vY > 0)
			if(ball.vH < ball.vY / 12)
				ball.vH = ball.vY / 12;
		if(ball.vY < 0)
			if(ball.vH < -ball.vY / 12)
				ball.vH = -ball.vY / 12;
	}
	
	public void def(Ball ball) {
		if (80 > length) {
			if(length < 10 && ball.player != null){
				if (tackle + (int) (Math.random() * 100) - 49 > ball.player.dribble) {
					delay = 20;
					ball.player.delay = 60;
					ball.player = this;
					ball.leftAtk = 2 - ball.leftAtk;
				} else {
						delay = 60;
						ball.player.delay = 10;
				}
			}
			else if(length < 5 + steal/10 && ball.player == null && ball.H <= 0){
				ball.player = this;
				ball.leftAtk = (int) goalX / 360;
			}
			else {
				run(ball.X, ball.Y);
			}
		} else {
			run(dX, dY);
		}
	}
	
	public void atk(Ball ball) {
		run(dX, dY);
	}

}
