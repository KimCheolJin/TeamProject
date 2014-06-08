package match.main;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.Team;
import match.data.MTeam;
import match.graphic.EditStrategy;
import match.graphic.GraphicMain;

public class Client  {

	Socket so;
	
	public Client(Team team, String ip, boolean isReturn) {
		try {
			MTeam away = new MTeam(team);
			
			so = new Socket(ip, 1111);
			System.out.println("�����߽��ϴ�.");
			
			BufferedInputStream bis = new BufferedInputStream(so.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(bis);
			System.out.println("Ȩ�� ���� ���� ����");
			
			MTeam home = new MTeam((Team) ois.readObject());
			System.out.println("Ȩ�� ���� ���� �Ϸ�");
			
			BufferedOutputStream bos = new BufferedOutputStream(so.getOutputStream());
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			System.out.println("������� ���� �߽� ����");
			
			oos.writeObject(team);
			oos.flush();
			oos.reset();
			System.out.println("������� ���� �߽� �Ϸ�");
			
			Socket so2 = new Socket(ip, 1111);
			BufferedOutputStream bosm = new BufferedOutputStream(so2.getOutputStream());
			ObjectOutputStream oosm = new ObjectOutputStream(bosm);
			oosm.flush();
			BufferedInputStream bism = new BufferedInputStream(so2.getInputStream());
			ObjectInputStream oism = new ObjectInputStream(bism);
			
			GraphicMain gm = new GraphicMain(home, away);
			new Thread(new ClientThread(gm, ois)).start();
			new Thread(new StrategyThread(oism, home, gm)).start();
			new EditStrategy(away, gm.awayInfo, oosm);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
