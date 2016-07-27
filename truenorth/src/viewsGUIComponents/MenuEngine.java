package viewsGUIComponents;

import javax.swing.JMenu;

public class MenuEngine extends JMenu{

	public MenuEngine(String menuName, int keyEventConstant) {
		super(menuName);
		this.setMnemonic(keyEventConstant);
	}
}
