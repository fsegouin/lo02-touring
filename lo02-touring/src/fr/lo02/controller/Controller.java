package fr.lo02.controller;

import java.util.Scanner;

import fr.lo02.model.Game;
import fr.lo02.model.Match;
import fr.lo02.model.Player;

public class Controller {

	public static void main(String[] args) {

		Game game = new Game();
		Scanner sc = new Scanner(System.in);

		System.out.println("Entrez un nombre de joueur humain :");
		game.setNbHumanPlayer(sc.nextInt());

		System.out.println("Entrez un nombre de joueur ordinateur :");
		game.setNbComputerPlayer(sc.nextInt());

		// On vide la ligne avant d'en lire d'autres car on a utilisé
		// sc.nextInt() avant
		sc.nextLine();

		// Add the name of each HumanPlayer
		for (int i = 0; i < game.getNbHumanPlayer(); i++) {
			System.out.println("Entrez le nom du joueur " + (i + 1) + " :");
			game.setNamePlayerNumber(i, sc.nextLine());
		}

		// On demarre le jeu, on passe la main a Match
		System.out.println(" ----- La partie commence -----");
		Match match = game.startMatch();
		
		Player acifPlayer = match.nextPlayer();
		System.out.println("\n" + acifPlayer.getName() + " c'est a vous");

	}
}
