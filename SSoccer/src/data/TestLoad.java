package data;

import java.util.ArrayList;

import store.Store;
import friend.Friend;


//DB 연동 안하고 사용하기 위한 테스트용 클래스
public class TestLoad extends Load {

	public TestLoad(String id) {
		super();
		u = makeUser(id);
		st = makeStore();
		ut = makeTeam("맨유");
		f = makeFriend();
	}

	//임시 메소드. 팀과 그 팀의 구성원들을 생성.
	public Team makeTeam(String tName){
		Team team = new Team(tName, 5, 5, 5, 4, (int) (Math.random() * 256), 
				(int) (Math.random() * 256), (int) (Math.random() * 256));
		team.name = tName;
		
		Player[] player = new Player[20];
		ArrayList<Player> playerList = new ArrayList<Player>();
		for(int i = 0; i < player.length; i++){
			String pname = team.name + " " + i + "번";
			player[i] = new Player();
			player[i].name = pname;
			player[i].shoot = 70;
			player[i].dribble = 70;
			player[i].pass = 70;
			player[i].stamina = 70;
			player[i].speed = 70;
			player[i].tackle = 70;
			player[i].steal = 70;
			player[i].gk = 70;
			player[i].exp = 1000;
			playerList.add(player[i]);
		}
		team.setPlayer(playerList);
		return team;
	}

	public User makeUser(String id){
		User user = new User();
		user.ID = id;
		return user;
	}
	
	public Friend makeFriend(){
		Friend friend = new Friend();
		friend.addFriend("asd", "ASD", 3, 2, 1, 5, 5);
		friend.addFriend("qwe", "QWE", 5, 5, 3, 12, 21);
		friend.addFriend("zxc", "ZXC", 0, 0, 10, 0, 0);
		return friend;
	}
	
	public Store makeStore(){
		Store store = new Store();
		return store;
	}

}
