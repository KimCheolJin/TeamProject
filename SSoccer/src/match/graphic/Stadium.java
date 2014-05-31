package match.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import match.data.Ball;
import match.data.MPlayer;
import match.data.MTeam;

public class Stadium extends JPanel implements MouseListener {

	MTeam home;
	MTeam away;
	public Ball ball = new Ball();
	
	public Stadium(MTeam home, MTeam away){
		setSize(400,260);
		setBackground(new Color(0,200,0));
		this.home = home;
		this.away = away;
		this.addMouseListener(this);
	}

	
	public void paint(Graphics g){
		paintStadium(g);
		for(int i = 0; i<11; i++){
			g.setColor(new Color(255,0,0));
			paintPlayer(g, home.player[i]);
		}
		for(int i = 0; i<11; i++){
			g.setColor(new Color(0,0,255));
			paintPlayer(g, away.player[i]);
		}

		g.setColor(Color.WHITE);
		g.fillOval((int)ball.X-5 - (int)ball.H/2, (int)ball.Y-5 - (int)ball.H/2, 
				10+(int)ball.H, 10+(int)ball.H);
	}

	public void paintStadium(Graphics g){
		g.setColor(new Color(100,255,100));
		g.fillRect(0, 0, 720, 430);
		
		g.setColor(new Color(200,255,200));
		g.fillOval(310, 165, 100, 100);
		g.fillArc(70, 165, 100, 100 , -90, 180);
		g.fillArc(550, 165, 100, 100 , 90, 180);
		g.fillOval(-18, -18, 36, 36);
		g.fillOval(-18, 412, 36, 36);
		g.fillOval(702, 412, 36, 36);
		g.fillOval(702, -18, 36, 36);
		g.fillRect(0, 110, 120, 210);
		g.fillRect(600, 110, 120, 210);
		
		g.setColor(new Color(100,255,100));
		g.fillOval(314, 169, 92, 92);
		g.fillArc(74, 169, 92, 92, -90, 180);
		g.fillArc(554, 169, 92, 92, 90, 180);
		g.fillOval(-14, -14, 28, 28);
		g.fillOval(-14, 416, 28, 28);
		g.fillOval(706, 416, 28, 28);
		g.fillOval(706, -14, 28, 28);
		g.fillRect(0, 114, 116, 202);
		g.fillRect(604, 114, 116, 202);
		
		g.setColor(new Color(200,255,200));
		g.fillRect(358, 0, 4, 430);
		g.fillRect(0, 165, 60, 4);
		g.fillRect(660, 165, 60, 4);
		g.fillRect(0, 261, 60, 4);
		g.fillRect(660, 261, 60, 4);
		g.fillRect(60, 165, 4, 100);
		g.fillRect(660, 165, 4, 100);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 175, 4, 80);
		g.fillRect(716, 175, 4, 80);
	}

	private void paintPlayer(Graphics g, MPlayer p){
		g.fillOval((int) p.X - 10, (int) p.Y - 10, 20, 20);
		g.setColor(Color.black);
		g.drawOval((int) p.X - 10, (int) p.Y - 10, 20, 20);
		p.eyeX = p.X + 10 * p.lenX / p.length;
		p.eyeY = p.Y + 10 * p.lenY / p.length;
		g.fillOval((int) p.eyeX - 5, (int) p.eyeY - 5, 10, 10);
	}


	public void mouseClicked(MouseEvent e) { }
	public void mousePressed(MouseEvent e) {
		ball.player = null;
		ball.leftAtk = 1;
		ball.H = 0.01;
		
		if(e.getX() - ball.X > 0)
			ball.vX = Math.sqrt(e.getX() - ball.X) * 0.45;
		else
			ball.vX = -Math.sqrt(ball.X - e.getX()) * 0.45;
		if(e.getY() - ball.Y > 0)
			ball.vY = Math.sqrt(e.getY() - ball.Y) * 0.45;
		else
			ball.vY = -Math.sqrt(ball.Y - e.getY()) * 0.45;
		
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
	
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
}
