package match.player;

import data.Player;
import match.data.Ball;
import match.data.MPlayer;
import match.data.MTeam;

public class Attacker extends MPlayer {

	public Attacker(Player p) {
		super(p);
	}
	
	public Attacker(MPlayer p) {
		super(p);
	}
	
	public void act(Ball ball, MTeam team) {
		if(delay > 0)
			delay -= 1;
		else {
			lenX = goalX - X;
			lenY = goalY - Y;
			length = Math.sqrt(lenX * lenX + lenY * lenY);
			
			run(goalX, goalY);
			
			ball.X = X + 10 * lenX / length;
			ball.Y = Y + 10 * lenY / length;
	
			check();
			
			if(160 > length)
				shoot(ball);
		}
	}

	public void def(Ball ball) {
		if (80 > length) {
			if(length < 10 && ball.player != null){
				if (tackle + Math.random() * 100 - 49 > ball.player.dribble) {
					delay = 20;
					ball.player.delay = 60;
					ball.player = this;
					ball.leftAtk = 2 - ball.leftAtk;
				} else {
						delay = 60;
						ball.player.delay = 10;
				}
			} else if(length < 5 + steal/10 && ball.player == null && ball.H <= 0){
				ball.player = this;
				ball.leftAtk = (int) goalX / 360;
			} else if(ball.player == null){
				run(ball.X, ball.Y);
			} else {
				run(dX, dY);
			}
		} else {
			run(dX, dY);
		}
	}
	
	public void atk(Ball ball) {
		run(dX, dY);	
	}
	
	public void shoot(Ball ball){
		ball.player = null;
		ball.leftAtk = 1;
		ball.H = 0.01;
		
		if (goalX - ball.X > 0) 
			ball.vX = Math.sqrt(goalX - ball.X) * 0.45 + 0.3;
		else 
			ball.vX = -Math.sqrt(ball.X - goalX) * 0.45 - 0.3;
		
		if(Y > 215) goalY = 180 + ((Math.random() - 0.5) * (100 - shoot));
		else  goalY = 250 - ((Math.random() - 0.5) * (100 - shoot));
		if (goalY - ball.Y > 0)
			ball.vY = Math.sqrt(goalY - ball.Y) * 0.45;
		else 
			ball.vY = -Math.sqrt(ball.Y - goalY) * 0.45;
		
		if(ball.vX > 0)
			ball.vH = ball.vX / 12;
		if(ball.vX < 0)
			ball.vH = - ball.vX / 12;
		ball.vH *= Math.random() * (100-shoot) / 300 + 1;

		goalY = 215;
	}

}
