package friend;

public class FriendsInfo {
	
	int accept=0;
	String friendId;
	String friendNick;
	
	public FriendsInfo(String id, String nick){

		this.friendId=id;
		this.friendNick=nick;
	}
	
	public int getAccept(){
		return this.accept;
	}
	
	public String getFriendId(){
		return this.friendId;
	}
	
	public String getFriendNick(){
		return this.friendNick;
	}
	

}