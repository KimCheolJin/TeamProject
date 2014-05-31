package match.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import match.data.MTeam;
import match.data.SendingStrategy;
import match.data.setDLocation;
import GUI.SetStrategy;

public class EditStrategy extends SetStrategy {

	TeamInfo teaminfo;
	
	public EditStrategy(final MTeam team, final TeamInfo teaminfo, final ObjectOutputStream oosm) {
		super(team);
		this.teaminfo = teaminfo;
		JButton ok = new JButton("적용");
		ok.setBounds(430, 300, 90, 50);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean eleven = true;
				
				for(int i = 0; i < 11; i++){
					System.out.println(sStrategy.player[i].name);
					System.out.println("asdasd");
					if(sStrategy.player[i].name == null){
						eleven = false;
						System.out.println(i);
					}
				}
				
				if(eleven){
					sStrategy.getData(team);
					if(team.player[0].goalX == 720) new setDLocation().setHomeFomation(team);
					else new setDLocation().setAwayFomation(team);
					teaminfo.draw();
					try {
						oosm.writeObject(sStrategy);
						oosm.flush();
						oosm.reset();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		add(ok);
	}
	
	public EditStrategy(final MTeam team, final TeamInfo teaminfo) {
		super(team);
		this.teaminfo = teaminfo;
		JButton ok = new JButton("적용");
		ok.setBounds(430, 300, 90, 50);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				boolean eleven = true;
				
				for(int i = 0; i < 11; i++){
					if(sStrategy.player[i].name == null){
						eleven = false;
					}
				}
				
				if(eleven){
					sStrategy.getData(team);
					if(team.player[0].goalX == 720) new setDLocation().setHomeFomation(team);
					else new setDLocation().setAwayFomation(team);
					teaminfo.draw();
				}
			}
		});
		add(ok);
	}
	
	public void startEdit(){
		JFrame editor = new JFrame(team.name + " Edit");
		editor.setContentPane(this);
		editor.setSize(550, 400);
		editor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		editor.setVisible(true);
	}
	
	
	
}
