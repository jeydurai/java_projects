package guiPractise;

import javax.swing.JFrame;

public class MainInit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ShowFlowLayout frame = new ShowFlowLayout();
		ShowGridLayout frame = new ShowGridLayout();
		//frame.setTitle("ShowFlowLayout");
		frame.setTitle("ShowGridLayout");
		frame.setSize(200, 125);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
