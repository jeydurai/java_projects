package guiPractise;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ShowFlowLayout extends JFrame{

	public ShowFlowLayout() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		
		add(new JLabel("First Name"));
		add(new JTextField(8));
		add(new JLabel("MI"));
		add(new JTextField(1));
		add(new JLabel("Last Name"));
		add(new JTextField(8));
	}
}
