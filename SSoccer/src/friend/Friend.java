package friend;

import java.util.ArrayList;

public class Friend {

	//친구정보목록
	ArrayList<FriendsInfo> list = new ArrayList<FriendsInfo>();

	public Friend(){
		
	}

	public void addFriend(String friendid,String nick, int win, int lose, int draw, int score1, int score2){
		list.add(new FriendsInfo(friendid,nick,win,lose,draw,score1,score2));
	}
	
	public void updateFriend(){
		
	}
	
	public ArrayList<FriendsInfo> getFriends(){
		return this.list;
	}
	
	
	
}
