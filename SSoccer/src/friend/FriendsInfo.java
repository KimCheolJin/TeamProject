package friend;

public class FriendsInfo {
	
	private int accept=0;
	private String friendId;
	private String friendNick;
	private int win;
	private int lose;
	private int draw;
	private int score1; //µæÁ¡
	private int score2; //½ÇÁ¡

	
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
	
	public String getRecord() {
		if(gameNumber() == 0)
			return "0½Â (0%)\t0ÆÐ (0%) \t0¹« (0%)";
		return win + "½Â (" + (100 * win / gameNumber()) + "%)\t" + 
				lose + "ÆÐ (" + (100 * lose / gameNumber()) + "%)\t" + 
				draw + "¹« (" + (100 * draw / gameNumber()) + "%)\t";
	}

	public String getScore() {
		return "µæ½ÇÂ÷ : " + (score1 - score2);
	}
	
	public String getScore1() {
		return "´©Àû µæÁ¡ : " + score1 + "\tÆò±Õ µæÁ¡ : " + (double)(10 * score1 / gameNumber()) / 10;
	}
	
	public String getScore2() {
		return "´©Àû ½ÇÁ¡ : " + score2 + "\tÆò±Õ ½ÇÁ¡ : " + (double)(10 * score2 / gameNumber()) / 10;
	}
	
	private int gameNumber() {
		return win + lose + draw;
	}
}