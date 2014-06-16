package match.main;

import client.ClientK;
import match.graphic.GraphicMain;

public class AIModeThread extends MatchThread{

	ClientK clientK;
	
	public AIModeThread(GraphicMain gm, ClientK clientK) {
		super(gm);
		this.clientK = clientK;
	}
	
	public void run() {
		super.run();
		gm.close();
		clientK.restartClient(0, 0);
	}
}