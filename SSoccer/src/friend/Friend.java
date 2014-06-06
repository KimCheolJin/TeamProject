package friend;

import java.util.ArrayList;

public class Friend {

	//친구목록
	ArrayList<String> list = new ArrayList<String>();
	//친구의 닉네임 list
	ArrayList<String> nickList = new ArrayList<String>();
	//친구의 접속 상태에 대한 list
	ArrayList<Integer> accept = new ArrayList<Integer>();

	public Friend(){
		
	}

	public void addFriend(String friendid,String nick){
		list.add(friendid);
		nickList.add(nick);
	}
	
	public void updateFriend(){
		
	}
	
	public ArrayList<String> getFriendList(){
	    String[] temp= this.list.toArray(new String[list.size()]); //arrayList -> String[]	
	    return list;
	}
	
	public ArrayList<String> getFriendNickList(){
		String[] temp = this.nickList.toArray(new String[nickList.size()]);
		return nickList;
	}
	
	public ArrayList<Integer> getAcceptList(){
		return accept;
	}
}
