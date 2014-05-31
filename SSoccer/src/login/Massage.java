package login;

import java.awt.*;

import javax.swing.*;

public class Massage extends JFrame {

	
	public Massage(String s){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("알림");
		this.setSize(300,300);
		this.setLocation(300,300);
		
		JPanel p = new JPanel();
		this.add(p);
		p.setLayout(new BorderLayout());
		p.setSize(200,200);
		
    	JLabel lb = new JLabel(s);
    	JButton bt = new JButton("확인");
    	
    	
    	bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActionPerformed(evt);
            }
        });
    	
    	lb.setFont(new java.awt.Font("굴림", 1, 20));
    	p.add(lb,BorderLayout.CENTER);
    	p.add(bt,BorderLayout.SOUTH);
    	
    	p.setVisible(true);
    	this.pack();
    	this.setVisible(true);
	}
	
     private void btActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    	 this.setVisible(false);
        
    }  
     
}
