package friend;

import java.util.ArrayList;

public class Friend {

	//ģ���������
	ArrayList<FriendsInfo> list = new ArrayList<FriendsInfo>();

	public Friend(){
		
	}

	public void addFriend(String friendid,String nick){
		list.add(new FriendsInfo(friendid,nick));
	}
	
	public void updateFriend(){
		
	}
	
	public ArrayList<FriendsInfo> getFriends(){
		return this.list;
	}
	
	
	
}
