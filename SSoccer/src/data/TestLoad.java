package data;

import store.Store;
import friend.Friend;


//DB ���� ���ϰ� ����ϱ� ���� �׽�Ʈ�� Ŭ����
public class TestLoad extends Load {

	public TestLoad(String id) {
		super();
		u = makeUser(id);
		st = makeStore();
		ut = makeTeam("�� �̸�");
		f = makeFriend();
	}

	//�ӽ� �޼ҵ�. ���� �� ���� ���������� ����.
	public Team makeTeam(String tName){
		Team team = new Team();
		team.name = tName;
		for(int i = 0; i < 22; i++){
			String pname = team.name + " " + i + "��";
			team.player[i] = new Player();
			team.player[i].name = pname;
			team.player[i].shoot = 70;
			team.player[i].dribble = 70;
			team.player[i].pass = 70;
			team.player[i].stamina = 70;
			team.player[i].speed = 70;
			team.player[i].tackle = 70;
			team.player[i].steal = 70;
			team.player[i].gk = 70;
			team.player[i].exp = 1000;
		}
		team.player[21].setNull();
		team.strategyA = 5;
		team.strategyD = 5;
		team.strategyF = 5;
		team.strategyT = 5;
		team.colorR = (int) (Math.random() * 256);
		team.colorG = (int) (Math.random() * 256);
		team.colorB = (int) (Math.random() * 256);
		return team;
	}

	public User makeUser(String id){
		User user = new User();
		user.ID = id;
		return user;
	}
	
	public Friend makeFriend(){
		Friend friend = new Friend();
		friend.addFriend("asd", "ASD");
		friend.addFriend("qwe", "QWE");
		friend.addFriend("zxc", "ZXC");
		return friend;
	}
	
	public Store makeStore(){
		Store store = new Store();
		return store;
	}

}
