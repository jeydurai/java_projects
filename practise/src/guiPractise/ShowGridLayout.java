package guiPractise;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ShowGridLayout extends JFrame {

	public ShowGridLayout() {
		setLayout(new GridLayout(2, 2, 5, 5));
		add(new JLabel("First Name"));
		add(new JTextField(8));
		add(new JLabel("MI"));
		add(new JTextField(1));
		add(new JLabel("Last Name"));
		add(new JTextField(8));
		//add(new JLabel("Email ID"));
		//add(new JTextField(20));
	}
}
