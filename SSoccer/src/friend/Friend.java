package friend;

import java.util.ArrayList;

public class Friend {

	//친구목록
	ArrayList<String> list = new ArrayList<String>();
	
	public Friend(){
		
	}

	public void addFriend(String friendid){
		
		list.add(friendid);
		
	}
	
	public void updateFriend(){
		
		
		
	}
	
	public String[] getFriendList(){
	    	
	    String[] temp= this.list.toArray(new String[list.size()]); //arrayList -> String[]	
	    return temp;
	    
	}
	
	
}
