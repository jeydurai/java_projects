package viewsGUIComponents;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuItemEngine extends JMenuItem {
	
	public MenuItemEngine(String menuItemName, int keyEventConstant, int actionEventConstant) {
		super(menuItemName);
		this.setMnemonic(keyEventConstant);
		this.setAccelerator(KeyStroke.getKeyStroke(keyEventConstant, actionEventConstant));
	}

}
