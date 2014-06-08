package match.graphic;

import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import data.TemporaryTeam;
import match.data.MTeam;
import match.data.setDLocation;
import GUI.SetStrategy;

public class EditStrategy extends SetStrategy {

	TeamInfo teaminfo;
	MTeam team;
	ObjectOutputStream oosm;

	//멀티플레이 모드 생성자
	public EditStrategy(final MTeam team, final TeamInfo teaminfo, final ObjectOutputStream oosm) {
		super(team);//흠 이부분에 오류나네요
		this.team = team;
		tTeam = new TemporaryTeam(team);
		this.teaminfo = teaminfo;
		this.oosm = oosm;
		setFrame();
		for (int i = 0; i < 22; i++) {
			player[i].setText(tTeam.player[i].name);
		}
	}
	
	//AI 모드 생성자
	public EditStrategy(final MTeam team, final TeamInfo teaminfo) {
		this(team, teaminfo, null);
	}
	
	//JFrame 생성
	public void setFrame(){
		JFrame editor = new JFrame(team.name + " Edit");
		editor.setContentPane(this);
		editor.setSize(550, 400);
		editor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		editor.setVisible(true);
	}
	
	//적용 버튼을 눌렀을때의 반응
	protected void okPerformed() {
		boolean eleven = true;
		
		for(int i = 0; i < 11; i++){
			if(tTeam.player[i].name.equals("")){
				eleven = false;
			}
		}
		
		if(eleven){
			tTeam.getData(team);
			if(team.player[0].goalX == 720) 
				new setDLocation().setHomeFomation(team);
			else 
				new setDLocation().setAwayFomation(team);
			teaminfo.draw();
			if(oosm != null){
				try {
					oosm.writeObject(tTeam);
					oosm.flush();
					oosm.reset();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}	
	
}
