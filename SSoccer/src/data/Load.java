package data;

import java.util.ArrayList;

import DBCENTER.DBload;
import store.Store;
import friend.Friend;

//�α��ν� DB���� �ҷ����� �͵� ��������ִ°�
public class Load {
	
	//DB�̿�
	DBload dbl = new DBload();
	//�������ִº�����
	protected User u;
	protected Store st;
	protected Team ut;
	protected Player[] up;
	protected Friend f;
	
	public Load(){}
	
	public Load(String id){
		st = dbl.loadStore();
		u = dbl.loadUser(id);
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

}
