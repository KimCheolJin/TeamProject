package match.main;

import java.io.IOException;
import java.io.ObjectOutputStream;

import client.ClientK;
import match.data.SendingData;
import match.graphic.GraphicMain;

public class ServerThread extends MatchThread {

	SendingData data = new SendingData();
	ObjectOutputStream oos;
	ClientK clientK;
	
	public ServerThread(GraphicMain gm, ObjectOutputStream oos, ClientK clientK){
		super(gm);
		this.oos = oos;
		this.clientK = clientK;
	}
	
	public void run() {
		super.run();
		gm.dispose();
		clientK.restartClient(home.score, away.score);
	}

	public boolean write() {
		try {
			data.setData(home, away, ball);
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			gm.close();
			clientK.restartClient(home.score, away.score);
			return false;
		}
		return true;
	}
	
}
