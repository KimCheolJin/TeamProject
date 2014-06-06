package match.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import data.Player;
import data.Team;
import match.data.MTeam;
import match.graphic.EditStrategy;
import match.graphic.GraphicMain;

public class Server {
	
	public Server(Team team){
		try {
			MTeam home = new MTeam(team);
			
			System.out.println("서버 기동, 클라이언트 접속을 기다립니다.");
			ServerSocket ss = new ServerSocket(1111);
			Socket so = ss.accept();
			System.out.println("클라이언트 접속 완료");
			
			BufferedOutputStream bos = new BufferedOutputStream(so.getOutputStream());
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			System.out.println("홈팀 정보 발신 시작");
			
			oos.writeObject(team);
			oos.flush();
			oos.reset();
			System.out.println("홈팀 정보 발신 완료");
			
			BufferedInputStream bis = new BufferedInputStream(so.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(bis);
			System.out.println("어웨이팀 정보 수신 시작");
			MTeam away = new MTeam((Team) ois.readObject());
			System.out.println("어웨이팀 정보 수신 완료");
			
			Socket so2 = ss.accept();
			BufferedOutputStream bosm = new BufferedOutputStream(so2.getOutputStream());
			ObjectOutputStream oosm = new ObjectOutputStream(bosm);
			oosm.flush();
			BufferedInputStream bism = new BufferedInputStream(so2.getInputStream());
			ObjectInputStream oism = new ObjectInputStream(bism);
			
			GraphicMain gm = new GraphicMain(home, away);
			new Thread(new ServerThread(gm, oos)).start();
			new Thread(new StrategyThread(oism, away, gm)).start();
			new EditStrategy(home, gm.homeInfo, oosm);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
		
	//임시 메소드. 팀과 그 팀의 구성원들을 생성.
	public static Team makeTeam(String tName){
		Team team = new Team();
		team.name = tName;
		for(int i = 0; i < 22; i++){
			String pname = team.name + " " + i + "번";
			team.player[i] = new Player(pname);
			team.player[i].shoot = 70;
			team.player[i].dribble = 70;
			team.player[i].pass = 70;
			team.player[i].stamina = 70;
			team.player[i].speed = 70;
			team.player[i].tackle = 70;
			team.player[i].steal = 70;
			team.player[i].gk = 70;
		}
		team.colorR = (int) (Math.random() * 256);
		team.colorG = (int) (Math.random() * 256);
		team.colorB = (int) (Math.random() * 256);
		return team;
	}
	
	public static void main(String args[]){
		new Server(makeTeam("맨유"));
	}

}
