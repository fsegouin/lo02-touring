package fr.lo02.controller;

import java.util.Scanner;

import fr.lo02.controller.gui.ConsoleHandGUI;
import fr.lo02.controller.gui.ConsoleMatchGUI;
import fr.lo02.model.Game;
import fr.lo02.model.Match;
import fr.lo02.model.Player;

public class Controller {		
	
	Game game;
	
	public Controller(Game model, ConsoleHandGUI view1, ConsoleMatchGUI view2) {

		game = model;
		Scanner sc = new Scanner(System.in);
		Player activePlayer = null;

		System.out.println("Entrez un nombre de joueur humain :");
		game.setNbHumanPlayer(sc.nextInt());

		System.out.println("Entrez un nombre de joueur ordinateur :");
		game.setNbComputerPlayer(sc.nextInt());
		sc.nextLine();

		// Add the name of each HumanPlayer
		for (int i = 0; i < game.getNbHumanPlayer(); i++) {
			System.out.println("Entrez le nom du joueur " + (i + 1) + " :");
			game.setNamePlayerNumber(i, sc.nextLine());
		}

		// On demarre le jeu, on passe la main a Match
		System.out.println("----- La partie commence -----");
		Match match = game.startMatch();
		view1.addHandObserver(match.getListPlayer());
		view2.addMatchObserver(match);
		match.next();

	}

}
