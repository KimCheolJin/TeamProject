package match.main;

import java.io.IOException;
import java.io.ObjectInputStream;

import client.ClientK;
import match.data.Ball;
import match.data.MTeam;
import match.data.SendingData;
import match.graphic.GraphicMain;

public class ClientThread implements Runnable {

	public GraphicMain gm;
	MTeam home, away;
	Ball ball;
	SendingData data = new SendingData();
	ObjectInputStream ois;
	ClientK clientK;
	private int fps;
	
	public ClientThread(GraphicMain gm, ObjectInputStream ois, ClientK clientK){
		this.gm = gm;
		home = gm.home;
		away = gm.away;
		ball = gm.stadium.ball;
		this.ois = ois;
		this.clientK = clientK;
	}
	
	public void run() {
		new Thread(new Checkfps()).start();
		while(true){
			if(!read()) break;
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
	
	public boolean read() {
		try {
			data = (SendingData) ois.readObject();
			data.getData(home, away, ball);
		} catch (IOException e) {
			gm.close();
			clientK.restartClient(away.score, home.score);
			return false;
		} catch (ClassNotFoundException e) {
			
		}
		return true;
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
