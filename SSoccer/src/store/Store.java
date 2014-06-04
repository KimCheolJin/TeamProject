package store;

import java.util.ArrayList; //arrayList이용

public class Store {

	//신규선수 목록
	ArrayList<SPlayer> newPlayer = new ArrayList<SPlayer>();
	//기존사용자가 사용하던 선수목록
	ArrayList<SPlayer> oldPlayer = new ArrayList<SPlayer>();
	//신규선수 String 목록
	ArrayList<String> np = new ArrayList<String>();
	//기존사용자 String 목록
	ArrayList<String> op = new ArrayList<String>();

	
	
	public Store(){
		
	}
	
	
	//신규선수 목록에 선수 추가 + String목록에도 추가
	public void addnewPlayer(int pN, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp, int price){
		
		SPlayer temp = new SPlayer(pN, n,s,d,p,st,tk,sl,sp,gk,exp,price);
		newPlayer.add(temp);
		np.add(temp.toString());
		
		
	}
	
	//기존선수 목록에 선수 추가 + String목록에도 추가
	public void addoldPlayer(int pN, String n, int s, int d, int p, int st, int tk, int sl,
			int sp, int gk, int exp,  int price){
		
		SPlayer temp = new SPlayer(pN, n,s,d,p,st,tk,sl,sp,gk,exp,price);
		oldPlayer.add(temp);
		op.add(temp.toString());
	}
	
	//신규선수 getter
	public ArrayList getNewPlayer(){
		
		return this.newPlayer;
		
	}
	
	//기존사용자가 사용하던 선수 getter
    public ArrayList getOldPlayer(){
		
		return this.oldPlayer;
		
	}
    
    //신규선수 String[] getter
    public String[] getnp(){
    	
    	String[] temp= this.np.toArray(new String[np.size()]); //arrayList -> String[]	
    	return temp;
    }

    //기존사용자가 사용하던 선수 String[] getter
    public String[] getop(){
    	
    	String[] temp= this.op.toArray(new String[op.size()]); //arrayList -> String[]	
    	return temp;
    }
}
