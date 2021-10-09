
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChooseComp extends JFrame implements ActionListener{
	JButton comp;
	JButton player;
	ChooseComp(){
		comp = new JButton();
		player = new JButton();
		
		comp.setBounds(20, 40, 160, 80);
		comp.addActionListener(this);
		comp.setText("1 player");
		
		player.setBounds(200, 40, 160, 80);
		player.addActionListener(this);
		player.setText("2 player");
		
		this.setVisible(true);
		this.setSize(400, 200);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.add(comp);
		this.add(player);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comp) {
			NewBoard.p1 = true;
			this.dispose();
		} else if (e.getSource() == player) {
			this.dispose();
		}
		// TODO Auto-generated method stub
		
	}
}
