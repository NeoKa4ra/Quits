
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class Aide extends JDialog {
	
	
	
	Aide(JFrame parent, String title, boolean modal){
		
		
		super(parent, title, modal);
		//setUndecorated(true);
		
		
		JPanel panbouton = new JPanel();
		ImageIcon icon = new ImageIcon(new ImageIcon("contenu_aide.png").getImage().getScaledInstance(500, 440, Image.SCALE_DEFAULT));
	
		JLabel aide= new JLabel(icon);
		JLabel titre= new JLabel("AIDE");
		titre.setPreferredSize(new Dimension(500,30));

		JButton ok = new JButton("OK");	
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			    dispose();
			  }
		}
		);
		
		
	    panbouton.add(ok);	
		
		
		setLayout(new BorderLayout());
		
		add(aide,BorderLayout.CENTER );
		add(panbouton, BorderLayout.SOUTH);
		
		setSize(500,500);
		setLocation(200,200);		
		setResizable(false);
		setVisible(true);
		repaint();
		}
	
	
}
