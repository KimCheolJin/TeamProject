package data;

public class User {

	public String ID;
	public String nickName;
	public int money;
	public int win;
	public int lose;
	public int draw;
	public int score;
	public int point;
	
	public User(){
		
	}
	
	public User(String id, String nick, int m, int w, int l, int d, int s, int p ){
		
		this.ID=id;
		this.nickName=nick;
		this.money=m;
		this.win=w;
		this.lose=l;
		this.draw=d;
		this.score=s;
		this.point=p;
		
	}
	
	public String getUser(){
		return this.ID;
	}
	
	
}
