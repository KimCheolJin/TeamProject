package data;

import java.util.ArrayList;

import DBCENTER.DBload;
import DBCENTER.DBdata;
import store.SPlayer;
import store.Store;
import friend.Friend;

//�α��ν� DB���� �ҷ����� �͵� ������� �ְ� ������ ������ ����
public class Load {
	
	//DB�̿�
	DBload dbl = new DBload();
	DBdata dbd = new DBdata();
	
	//�������ִº�����
	protected User u;
	protected Store st;
	protected Team ut;
	protected ArrayList<Player> up;
	protected Friend f;
	protected String id;
	
	
	
	
	public Load(){}
	
	public Load(String id){
		this.id=id;
		st = dbl.loadStore();
		u = dbl.loadUser(id);
		f = dbl.loadFriend(id);
		up = dbl.loadPlayer(id);
		ut = dbl.loadTeam(id);
		ut.setPlayer(up);
	}
	
	public Store getStore(){
		return st;
	}
	
	public Team getTeam(){
		return ut;
	}

	public User getUser() {
		return u;
	}
	
	public Friend getFriend(){
		return f;
	}
	
	
	//�߰������� ���⼭ �ε��۾� �� ���� �޼ҵ� ����

	//update�޼ҵ� ����� ��ü������ ���߿� ���������θ� ���� �� �� �� �ִ�
	public void upDate(){
		
		st = dbl.loadStore();
		//u = dbl.loadUser(id);
		f = dbl.loadFriend(id);
		up = dbl.loadPlayer(id);
		//ut = dbl.loadTeam(id);
		ut.setPlayer(up);
		
	}
	
	//�űԼ������Ÿ޼ҵ�
	 public void buyNewPlayer(int index){
		 
		SPlayer temp = this.st.buyNewPlayer(index, this.ut);
		ut.addPlayer((Player)temp); //��¦��������� �ִºκ� SPLAYER,PLAYER ���� ���캸�� ĳ�������� �������ذ�
		dbd.putNewPlayer(temp, id);
		u.changeMoney(-temp.getPrice());
		
	}
	 
	 
	 //�������弱�����Ÿ޼ҵ�
	 public void buyOldPlayer(int index){
			
		 SPlayer temp = this.st.buyOldPlayer(index, this.ut);
		 ut.addPlayer((Player)temp);
		 dbd.putOldPlayer(temp, id);
		 u.changeMoney(-temp.getPrice());
		 
		}
	 
	 
	 //�����ǸŸ޼ҵ�
	 public void sellPlayer(int index, int pri){
		 
		 Player temp = this.ut.getPlayer(index);
		 ut.removePlayer(index);
		 st.addoldPlayer(temp.primaryNum, temp.name, temp.shoot, temp.dribble, temp.pass, temp.stamina, temp.tackle, temp.steal, temp.speed, temp.gk, temp.exp, pri);
		 dbd.sellPlayer(temp, pri, id);
	 }
	 
	 
	 //ģ������ϴºκ�
	 public void putFriend(String userid, String friendid){
		 
		 String nick = dbd.putFriends(userid, friendid);
		 f.addFriend(friendid, nick, 0, 0, 0, 0, 0);
		 
		 
	 }
}
