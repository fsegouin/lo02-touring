package fr.lo02.controller;

import java.util.Scanner;

import fr.lo02.model.Game;
import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class Controller {

	public static boolean activePlayerPlay(Player acifPlayer, Match match) {
		int cardIndex = 0;
		boolean askAgain = true;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Votre main actuelle est : " + acifPlayer.showHand());
		System.out.println("Vous piochez : " + acifPlayer.pickCard(match.getGameStack()));
		System.out.println("Votre main actuelle est : " + acifPlayer.showHand());
		
		while(askAgain) {
			System.out.println("Que souhaitez-vous jouer ? Entrez le numero de la carte.");
			try {
				cardIndex = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				continue;
			}

			if (cardIndex <= (acifPlayer.getHand().size()))
				askAgain = false;
		}
		
		acifPlayer.selectedCard(cardIndex).playThisCard(acifPlayer, null);
		System.out.println("Kilometres parcourus par " + acifPlayer.getName()+ " : " + acifPlayer.getTotalMilage());
		return true;
	}
	
	
	public static void main(String[] args) {

		Game game = new Game();
		Scanner sc = new Scanner(System.in);
		Player acifPlayer = null;

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
		
		do {
			acifPlayer = match.nextPlayer();
			System.out.println("\n" + acifPlayer.getName() + " c'est a vous");
		} while (activePlayerPlay(acifPlayer, match));
		
	}
		
}
