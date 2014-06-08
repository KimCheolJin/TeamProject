package friend;

public class FriendsInfo {
	
	private int accept=0;
	private String friendId;
	private String friendNick;
	private int win;
	private int lose;
	private int draw;
	private int score1; //����
	private int score2; //����

	
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
			return "0�� (0%)\t0�� (0%) \t0�� (0%)";
		return win + "�� (" + (100 * win / gameNumber()) + "%)\t" + 
				lose + "�� (" + (100 * lose / gameNumber()) + "%)\t" + 
				draw + "�� (" + (100 * draw / gameNumber()) + "%)\t";
	}

	public String getScore() {
		return "����� : " + (score1 - score2);
	}
	
	public String getScore1() {
		return "���� ���� : " + score1 + "\t��� ���� : " + (double)(10 * score1 / gameNumber()) / 10;
	}
	
	public String getScore2() {
		return "���� ���� : " + score2 + "\t��� ���� : " + (double)(10 * score2 / gameNumber()) / 10;
	}
	
	private int gameNumber() {
		return win + lose + draw;
	}
}