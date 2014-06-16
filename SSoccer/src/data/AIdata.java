package data;

import java.util.ArrayList;

import DBCENTER.DBload;


public class AIdata {
	
	Team t;
	int randomNumber;
	DBload dbl = new DBload();
	
	public AIdata(){
		
	}
	
	//Player형 list로 받음
	public void setTeam(ArrayList<Player> list){
		
		//0~9랜덤
		randomNumber = (int)(Math.random()*10);
		//0,1 두개만 우선 선택되도록 함
		randomNumber = randomNumber%2;
		
		String tempName="아스널"; //디폴트값 설정 안할시 에러
		ArrayList<Player> templist = new ArrayList<Player>();
		
		int num=0;
		
		//해당하는 선수정보 가져옴
		if(randomNumber==0){
			//아스널로 선택됨
			
			tempName="아스널";
			
			num=0;
			
		}
		else if(randomNumber==1){
			//맨시티로 선택됨
			
			tempName="맨시티";
			
			num=22;
			
		}
		else{
		
		}
		
		for(int i=num; i<num+21; i++){
			templist.add(list.get(i));
		}
		
		//DB에서 팀정보 가져옴
		t = dbl.loadAIteam(tempName);
		t.setPlayer(templist);
		
	}
	
	//getTeam할때마다 랜덤으로 팀바뀜
	public Team getTeam(Load data){
		
		this.setTeam(data.getStore().getNewPlayer());
		return this.t;
	}

}
