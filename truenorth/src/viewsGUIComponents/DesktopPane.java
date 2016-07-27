package viewsGUIComponents;

import helperUtilities.Comforter;
import helperUtilities.GeneralConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class DesktopPane extends JDesktopPane{

	private BufferedImage image;
	
	public DesktopPane() {
		this.setBackground(Color.WHITE);
        try {
            image =  ImageIO.read(this.getClass().getResource(GeneralConstants.CISCO_DESKTOP_PNG));
        } catch (IOException e) {
			JOptionPane.showMessageDialog(this, Comforter.StackTraceToString(e));
        }
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
