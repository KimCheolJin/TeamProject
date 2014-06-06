package data;

import DBCENTER.DBload;
import store.Store;
import friend.Friend;

//�α��ν� DB���� �ҷ����� �͵� ��������ִ°�
public class Load {
	
	//DB�̿�
	DBload dbl = new DBload();
	//�������ִº�����
	User u;
	Store st;
	Team ut;
	Player[] up;
	Friend f;
	
	
	public Load(String id){
		
		st = dbl.loadStore();
		u = dbl.loadUser(id);
		up = dbl.loadPlayer(id);
		ut = dbl.loadTeam(id);
		ut.setPlayer(up);
		
		
	}
	
	public Store getStore(){
		return this.st;
	}
	
	public Team getTeam(){
		return this.ut;
	}

	public User getUser() {
		return u;
	}
	
	public Friend getFriend(){
		return f;
	}

}
