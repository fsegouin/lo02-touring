package fr.lo02.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.controller.gui.GameGUI;
import fr.lo02.model.Game;
import fr.lo02.model.Match;

public class ControllerGUI {

	Game game;
	Match match;
	GameGUI gameGUI;
	
	public ControllerGUI(Game _game, GameGUI _gameGUI) {
		this.game = _game;
		this.gameGUI = _gameGUI;
		
		this.gameGUI.addValidateListener(new StartGame());
	}

	class StartGame implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			game.setNbHumanPlayer(gameGUI.getNbPlayer());
			game.setNbComputerPlayer(gameGUI.getNbComputer());
			game.setNamePlayerTab(gameGUI.getPlayerName());
			gameGUI.startMatch();
			match = game.startMatch();
		}
		
	}
}
