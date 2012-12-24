package fr.lo02.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import fr.lo02.model.Game;
import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.exception.SelectedCardNotDefinedException;

public class Controller {

	public static boolean activePlayerPlay(Player activePlayer, Match match) {
		int cardIndex = 0;
		int numPlayer = 0;
		HashSet<Player> lp = null;
		boolean askAgainNumCard = true;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Votre main actuelle est : " + activePlayer.showHand());
		System.out.println("Vous piochez : " + activePlayer.pickCard(match.getGameStack()));
		System.out.println("Votre main actuelle est : " + activePlayer.showHand());
		System.out.println("-------------------------------------------------------");

		while (askAgainNumCard) {
			System.out.println("Que souhaitez-vous jouer ? Entrez le numero de la carte.");
			try {
				cardIndex = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				continue;
			}

			if (cardIndex <= (activePlayer.getHand().size())) {
				activePlayer.setSelectedCard(cardIndex);
				try {
					lp = match.checkCardPlayable(activePlayer);
					if (lp.size() != 0) { //si il existe des joueur ciblable			
						boolean askAgainNumPlayer = true;
						while (askAgainNumPlayer) {
							System.out.println("Vous pouvez jouer sur :");
							int i = 0;
							for (Iterator iterator = lp.iterator(); iterator.hasNext();) {
								Player player = (Player) iterator.next();
								i++;
								System.out.println(i + " - " + player.getName());
							}
							System.out.println("Sur qui voulez-vous jouer cette carte ?");
							try {
								numPlayer = Integer.parseInt(scan.nextLine());
							} catch (NumberFormatException e) {
								continue;
							}
						}
						askAgainNumCard = false;
					} else
						System.out.println("Selectionner une autre carte.");
				} catch (SelectedCardNotDefinedException e) {
					e.getMessage();
				}
			}
		}
		
		if (activePlayer.getTotalMilage() >= 1000)
			return false;
		else
			return true;
	}

	public static void main(String[] args) {

		Game game = new Game();
		Scanner sc = new Scanner(System.in);
		Player activePlayer = null;

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
			activePlayer = match.nextPlayer();
			System.out.println("\n" + activePlayer.getName() + " c'est a vous");
		} while (activePlayerPlay(activePlayer, match));

	}

}
