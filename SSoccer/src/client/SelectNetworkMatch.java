package client;



import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetAddress;

import data.Load;
import GUI.SelectMatch;

public class SelectNetworkMatch extends SelectMatch {

	private ClientK mainmenu;

	private BufferedWriter bw;
	
	public SelectNetworkMatch(Load data, ClientK mainmenu) {
		super(data, mainmenu);
		this.mainmenu = mainmenu;
	}

	public void networkMatch(boolean isReturn) {
		try {
			mainmenu.pause();

			bw = mainmenu.bw2;
			if(isReturn)
				bw.write("special");				
			else
				bw.write("practice");
			bw.newLine();
			bw.flush();
			
			bw.write(InetAddress.getLocalHost().getHostAddress());
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
