import fr.lo02.controller.ControllerGameGUI;
import fr.lo02.controller.gui.GameGUI;
import fr.lo02.model.Game;


public class MilleBorne {
	
	
	public MilleBorne() {
	}

	/** Fonction main du programme. Appelle la vue et le controller.
	 * @param args
	 */
	public static void main(String[] args) {
			Game model = new Game();
			GameGUI view = new GameGUI(model);
			view.setVisible(true);
			ControllerGameGUI controller = new ControllerGameGUI(model, view);
	}
}
