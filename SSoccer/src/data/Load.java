package data;

import DBCENTER.DBload;
import store.Store;
import friend.Friend;

//로그인시 DB에서 불러오는 것들 실행시켜주는곳
public class Load {
	
	//DB이용
	DBload dbl = new DBload();
	//가지고있는변수들
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
