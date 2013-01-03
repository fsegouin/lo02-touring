import java.awt.EventQueue;

import fr.lo02.controller.ControllerGUI;
import fr.lo02.controller.gui.GameGUI;
import fr.lo02.model.Game;


public class MilleBorne {
	
	
	public MilleBorne() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Game model = new Game();
//		GameGUI view = null;
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
						GameGUI view = new GameGUI();
						view.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
			ControllerGUI controller = new ControllerGUI(model, view);
		}
	}
