package match.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import match.data.Ball;
import match.data.MTeam;
import match.data.SendingData;
import match.graphic.GraphicMain;
import match.main.MatchThread.Checkfps;

public class ClientThread implements Runnable {

	public GraphicMain gm;
	MTeam home, away;
	Ball ball;
	SendingData data = new SendingData();
	ObjectInputStream ois;
	private int fps;
	
	public ClientThread(GraphicMain gm, ObjectInputStream ois){
		this.gm = gm;
		home = gm.home;
		away = gm.away;
		ball = gm.stadium.ball;
		this.ois = ois;
	}
	
	public void run() {
		new Thread(new Checkfps()).start();
		while(true){
			read();
			setPlayerEye();
			gm.stadium.repaint();
			gm.score.rescore();
			fps += 1;
		}	
	}

	void setPlayerEye(){
		for(int i = 0; i < 11; i++){
			home.player[i].setLength(ball);
			away.player[i].setLength(ball);
		}
	}
	
	public void read() {
		try {
			data = (SendingData) ois.readObject();
			data.getData(home, away, ball);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
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

}
