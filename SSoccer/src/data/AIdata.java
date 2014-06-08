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
	public void setTeam(int num,ArrayList<Player> list ){
		
		//0~9랜덤
		randomNumber = (int)(Math.random()*10);
		//0,1 두개만 우선 선택되도록 함
		randomNumber = randomNumber%2;
		
		String tempName="아스널"; //디폴트값 설정 안할시 에러
		ArrayList<Player> templist = new ArrayList<Player>();
		
		//해당하는 선수정보 가져옴
		if(num==0){
			//아스널로 선택됨
			
			tempName="아스널";
			
			for(int i=0; i<22; i++){
				templist.add(list.get(i));
			}
			
		}
		else if(num==1){
			//맨시티로 선택됨
			
			tempName="맨시티";
			
			for(int i=22; i<44; i++){
				templist.add(list.get(i));
			}
			
		}
		else{
		
		}
		
		//DB에서 팀정보 가져옴
		t = dbl.loadAIteam(tempName);
		t.setPlayer(templist);
		
	}
	
	//getTeam할때마다 랜덤으로 팀바뀜
	public Team getTeam(Load data){
		
		this.setTeam(randomNumber, data.getStore().getNewPlayer());
		return this.t;
	}

}
