package data;

import java.util.ArrayList;

import DBCENTER.DBload;


public class AIdata {
	
	Team t;
	int randomNumber;
	DBload dbl = new DBload();
	
	public AIdata(){
		
	}
	
	//Player�� list�� ����
	public void setTeam(int num,ArrayList<Player> list ){
		
		//0~9����
		randomNumber = (int)(Math.random()*10);
		//0,1 �ΰ��� �켱 ���õǵ��� ��
		randomNumber = randomNumber%2;
		
		String tempName="�ƽ���"; //����Ʈ�� ���� ���ҽ� ����
		ArrayList<Player> templist = new ArrayList<Player>();
		
		//�ش��ϴ� �������� ������
		if(num==0){
			//�ƽ��η� ���õ�
			
			tempName="�ƽ���";
			
			for(int i=0; i<22; i++){
				templist.add(list.get(i));
			}
			
		}
		else if(num==1){
			//�ǽ�Ƽ�� ���õ�
			
			tempName="�ǽ�Ƽ";
			
			for(int i=22; i<44; i++){
				templist.add(list.get(i));
			}
			
		}
		else{
		
		}
		
		//DB���� ������ ������
		t = dbl.loadAIteam(tempName);
		t.setPlayer(templist);
		
	}
	
	//getTeam�Ҷ����� �������� ���ٲ�
	public Team getTeam(Load data){
		
		this.setTeam(randomNumber, data.getStore().getNewPlayer());
		return this.t;
	}

}
