package friend;

public class FriendsInfo {
	
	private int accept=0;
	private String friendId;
	private String friendNick;
	private int win;
	private int lose;
	private int draw;
	private int score1; //µÊ¡°
	private int score2; //Ω«¡°

	
	public FriendsInfo(String id, String nick, int win, int lose, int draw, int score1, int score2){
		this.friendId=id;
		this.friendNick=nick;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
		this.score1 = score1;
		this.score2 = score2;
	}
	
	public int getAccept(){
		return this.accept;
	}
	
	public void setAccept(int accept){
		this.accept = accept;
	}
	
	public String getFriendId(){
		return this.friendId;
	}
	
	public String getFriendNick(){
		return this.friendNick;
	}
	

}