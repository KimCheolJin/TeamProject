package match.main;

import java.io.IOException;
import java.io.ObjectOutputStream;

import match.data.SendingData;
import match.graphic.GraphicMain;

public class ServerThread extends MatchThread {

	SendingData data = new SendingData();
	ObjectOutputStream oos;
	
	public ServerThread(GraphicMain gm, ObjectOutputStream oos){
		super(gm);
		this.oos = oos;
	}
	
	public void run() {
		super.run();
	}

	public void write() {
		try {
			data.setData(home, away, ball);
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
}
