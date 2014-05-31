package match.data;

import data.Player;

public class MPlayer extends data.Player {

	public int delay, sp;
	public double X, Y, dX, dY;
	public double eyeX, eyeY, lenX, lenY, length;
	public double goalX, goalY = 215;
	
	public MPlayer(Player p) {
		name = p.name;
		shoot = p.shoot;
		dribble = p.dribble;
		pass = p.pass;
		stamina = p.stamina;
		speed = p.speed;
		tackle = p.tackle;
		steal = p.steal;
		gk = p.gk;
		sp = p.stamina * 100;
	}
		
	public MPlayer(MPlayer p) {
		copy((Player) p);
		sp = p.sp;
		X = p.X;
		Y = p.Y;
		goalX = p.goalX;
	}

	public void copy(MPlayer p) {
		copy((Player) p);
		sp = p.sp;
		X = p.X;
		Y = p.Y;
		goalX = p.goalX;
	}

	public void reduceSp() {
		if (sp == 0) {
			shoot /= 2;
			dribble /= 2;
			pass /= 2;
			tackle /= 2;
			steal /= 2;
			speed /= 2;
			gk /= 2;
			sp--;
		} else
			sp--;
	}

	public void move(Ball ball, int atk) {
		if(delay > 0)
			delay -= 1;
		else if (this != ball.player) {
			setLength(ball);
			if (atk == 2){
				atk(ball);
			}
			else
				def(ball);
			check();
		}
	}

	public void run(double x, double y){
		if(x - X > 2 || x - X < - 2)
			X += (x-X) * speed / Math.sqrt((x-X)*(x-X) + (y-Y)*(y-Y)) / 50;
		if(y - Y > 2 || y - Y < - 2)
			Y += (y-Y) * speed / Math.sqrt((x-X)*(x-X) + (y-Y)*(y-Y)) / 50;
	}
	
	public void setLength(Ball ball) {
		lenX = ball.X - X;
		lenY = ball.Y - Y;
		length = Math.sqrt(lenX * lenX + lenY * lenY);
	}
	
	public void act(Ball ball, MTeam team) {}
	public void atk(Ball ball) {}

	public void def(Ball ball) {}

	public void check() {
		if (X > 720)
			X = 720;
		else if (X < 0)
			X = 0;
		if (Y > 430)
			Y = 430;
		else if (Y < 0)
			Y = 0;	
		}

}
