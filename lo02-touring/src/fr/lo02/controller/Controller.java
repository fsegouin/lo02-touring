package fr.lo02.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import fr.lo02.controller.gui.ConsoleGUI;
import fr.lo02.model.ComputerPlayer;
import fr.lo02.model.Game;
import fr.lo02.model.HumanPlayer;
import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.exception.SelectedCardNotDefinedException;

public class Controller {		
	
	Game game;
	
	public Controller(Game model, ConsoleGUI view) {

		game = model;
		Scanner sc = new Scanner(System.in);
		Player activePlayer = null;

		System.out.println("Entrez un nombre de joueur humain :");
		game.setNbHumanPlayer(sc.nextInt());

		System.out.println("Entrez un nombre de joueur ordinateur :");
		game.setNbComputerPlayer(sc.nextInt());

		// On vide la ligne avant d'en lire d'autres car on a utilise
		// sc.nextInt() avant
		sc.nextLine();

		// Add the name of each HumanPlayer
		for (int i = 0; i < game.getNbHumanPlayer(); i++) {
			System.out.println("Entrez le nom du joueur " + (i + 1) + " :");
			game.setNamePlayerNumber(i, sc.nextLine());
		}

		// On demarre le jeu, on passe la main a Match
		System.out.println("----- La partie commence -----");
		Match match = game.startMatch();
		view.addHandObserver(match.getListPlayer());
		match.next();

//		do {
//			activePlayer = match.nextPlayer();
//			if (activePlayer instanceof HumanPlayer) {
//				activePlayerPlay(activePlayer, match);
//			} 
//			else if (activePlayer instanceof ComputerPlayer){
//				((ComputerPlayer) activePlayer).play(match);
//				System.out.println("Kilometres du " + activePlayer.getName() + " : " + activePlayer.getTotalMilage());
//			}
//		} while (!(match.testEndOfGame()));
//		System.out.println("--- Fin du jeu, merci d'avoir joue ! ---");
	}

}
