package viewsGUIComponents;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class PanelGradient extends JPanel{

	protected boolean isDark;
    
    public PanelGradient(LayoutManager layout, boolean isDoubleBuffered){
        super(layout, isDoubleBuffered);
        setOpaque(true);
        setDark(false);
    }
    public PanelGradient(LayoutManager layout) {
        this(layout, true);
    }
    public PanelGradient(boolean isDoubleBuffered) {
        this(null, isDoubleBuffered);
    }
    public PanelGradient() {
        this(null, true);
    }
    public void setDark(boolean b) {
        isDark = b;
    }

    @Override
    public void paintComponent(Graphics oldG) {
    	super.paintComponent(oldG);
        Graphics2D g = (Graphics2D)oldG;
        Point2D startPoint = new Point2D.Float(0,0);
        Point2D endPoint = new Point2D.Float(0,getWidth());
        Color back = Color.white;
        if (isDark) {
            back = Color.black;
        }
        Paint gradientPaint = new GradientPaint(startPoint,back,endPoint,getBackground(),true);
        g.setPaint(gradientPaint);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fill(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
    }

}
