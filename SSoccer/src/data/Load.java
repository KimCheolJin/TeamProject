package data;

import java.util.ArrayList;

import DBCENTER.DBload;
import DBCENTER.DBdata;
import store.SPlayer;
import store.Store;
import friend.Friend;

//로그인시 DB에서 불러오는 것들 실행시켜 주고 정보들 가지고 있음
public class Load {
	
	//DB이용
	DBload dbl = new DBload();
	DBdata dbd = new DBdata();
	
	//가지고있는변수들
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
	
	
	//추가적으로 여기서 로드작업 외 여러 메소드 실행

	//update메소드 현재는 전체했으나 나중에 세부적으로만 진행 될 수 도 있다
	public void upDate(){
		
		st = dbl.loadStore();
		//u = dbl.loadUser(id);
		f = dbl.loadFriend(id);
		up = dbl.loadPlayer(id);
		//ut = dbl.loadTeam(id);
		ut.setPlayer(up);
		
	}
	
	//신규선수구매메소드
	 public void buyNewPlayer(int index){
		 
		SPlayer temp = this.st.buyNewPlayer(index, this.ut);
		ut.addPlayer((Player)temp); //살짝문제생길수 있는부분 SPLAYER,PLAYER 좀더 살펴보기 캐스팅으로 지금은해결
		dbd.putNewPlayer(temp, id);
		u.changeMoney(-temp.getPrice());
		
	}
	 
	 
	 //이적시장선수구매메소드
	 public void buyOldPlayer(int index){
			
		 SPlayer temp = this.st.buyOldPlayer(index, this.ut);
		 ut.addPlayer((Player)temp);
		 dbd.putOldPlayer(temp, id);
		 u.changeMoney(-temp.getPrice());
		 
		}
	 
	 
	 //선수판매메소드
	 public void sellPlayer(int index, int pri){
		 
		 Player temp = this.ut.getPlayer(index);
		 ut.removePlayer(index);
		 st.addoldPlayer(temp.primaryNum, temp.name, temp.shoot, temp.dribble, temp.pass, temp.stamina, temp.tackle, temp.steal, temp.speed, temp.gk, temp.exp, pri);
		 dbd.sellPlayer(temp, pri, id);
	 }
	 
	 
	 //친구등록하는부분
	 public void putFriend(String userid, String friendid){
		 
		 String nick = dbd.putFriends(userid, friendid);
		 f.addFriend(friendid, nick, 0, 0, 0, 0, 0);
		 
		 
	 }
}
