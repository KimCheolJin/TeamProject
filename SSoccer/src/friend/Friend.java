package friend;

import java.util.ArrayList;

public class Friend {

	//ģ�����
	ArrayList<String> list = new ArrayList<String>();
	//ģ���� �г��� list
	ArrayList<String> nickList = new ArrayList<String>();
	
	public Friend(){
		
	}

	public void addFriend(String friendid,String nick){
		
		list.add(friendid);
		nickList.add(nick);
		
	}
	
	public void updateFriend(){
		
		
		
	}
	
	public String[] getFriendList(){
	    	
	    String[] temp= this.list.toArray(new String[list.size()]); //arrayList -> String[]	
	    return temp;
	    
	}
	
	public String[] getFriendNickList(){
		
		String[] temp = this.nickList.toArray(new String[nickList.size()]);
		return temp;
	}
	
	
}
