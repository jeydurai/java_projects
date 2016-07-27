package viewsGUIComponents;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GroupContainer {
	
	public static JPanel groupContainer(String title, float alignment) {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), title), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		return container;
	}
	
	public static Box createBorderedBox(String title) {
		Box box = Box.createHorizontalBox();
		if (title != null) {
			box.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), title), 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		}
		return box;
	}

	public static CustomizedPanel createTitledCustomizedPanel(String title) {
		CustomizedPanel panel = new CustomizedPanel();
		if (title != null) {
			panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), title), 
					BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		}
		return panel;
	}
	
	public static JTextArea createTextAreaCustomized(String defaultText, int rows, int cols) {
		JTextArea jta = new JTextArea(defaultText, rows, cols);
		Font font = new Font("Verdana", Font.PLAIN, 14);
		jta.setFont(font);
		jta.setMargin(new Insets(10, 10, 10, 10));
		return jta;
	}
}
