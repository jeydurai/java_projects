package viewsGUIComponents;

import helperUtilities.GeneralConstants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame{
	
	private static int frameCount = 0;
	private static int xOffset = 30, yOffset = 30;
	protected ImageIcon frameIcon;
	
	public InternalFrame(String frameName) {
		super(frameName + " " + (++frameCount), true, true, true, true);
		frameIcon = new ImageIcon(this.getClass().getResource(GeneralConstants.FAVI_ICON_GIF));
		this.setFrameIcon(this.frameIcon);
		this.setSize(700, 500);
		this.setLocation(xOffset*frameCount, yOffset*frameCount);
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
    }

}
