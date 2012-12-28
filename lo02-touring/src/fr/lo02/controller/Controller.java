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
		HashSet<Player> lp = null;
		boolean askAgainNumCard = true;
		boolean askAgainPlayerName = true;
		Scanner scan = new Scanner(System.in);
		Player selectedPlayer = null;

		System.out.println("-------------------------------------------------------");
		System.out.println("Votre main actuelle est : " + activePlayer.showHand());
		System.out.println("Vous piochez : " + activePlayer.pickCard(match.getGameStack()));
		System.out.println("Votre nouvelle main est : " + activePlayer.showHand());
		System.out.println("-------------------------------------------------------");

		// SELECTIONNE LA CARTE A JOUER
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
					// SI DES JOUEUR PEUVENT ETRE CIBLE PAR LA CARTE SELECTIONNE
					if (lp.size() != 0) {
						System.out.println("Vous pouvez jouer sur :");
						int i = 0;
						//deroule les joueurs ciblable
						for (Iterator<Player> iterator = lp.iterator(); iterator.hasNext();) {
							Player player = (Player) iterator.next();
							i++;
							System.out.println(i + " - " + player.getName());
						}
						// ACTION AVEC LA CARTE SELECTIONNE
						while (askAgainPlayerName) {
							System.out.println("Tapez: le nom du joueur a cibler, (a) pour changer de carte, (d) pour se defausser de cette carte )");
							String choice = scan.nextLine();
							
							// ---- Se defausser ----
							if (choice.equals("d")) {
								activePlayer.setSelectedCard(cardIndex);
								System.out.println("Vous vous defausser de : " + activePlayer.getSelectedCard().toString());
								match.addToDiscardStack(activePlayer.getSelectedCard());
								activePlayer.getSelectedCard().throwThisCard(activePlayer);
								askAgainPlayerName = false;
								askAgainNumCard = false;
								System.out.println("1");
							}
							// ---- Selectionner une autre carte ----
							else if (choice.equals("a")) {
								askAgainPlayerName = false;
								askAgainNumCard = true;
								System.out.println("2");

							}
							// ---- Jouer la carte selectionne ----
							else if (match.getPlayerByName(choice, lp) != null) {
								selectedPlayer = match.getPlayerByName(choice, lp);
								activePlayer.getSelectedCard().playThisCard(activePlayer, selectedPlayer);
								System.out.println("3");
								askAgainPlayerName = false;
								askAgainNumCard = false;
							}
							// ---- Saisie invalide ----
							else {
								System.out.println("Saisi non valide");
								askAgainPlayerName = true;
							}
						}
					}
					// SI AUCUN JOUEUR NE PEUT ETRE CIBLE PAR LA CARTE SELECTIONNE
					else {
						System.out.println("Cette carte ne peut etre joue sur aucun joueur, (a) changer de carte, (d) se defausser");
						String choice = scan.nextLine();
						// ---- Se defausser ----
						if (choice.equals("d")) {
							activePlayer.setSelectedCard(cardIndex);
							System.out.println("Vous vous defausser de : " + activePlayer.getSelectedCard().toString());
							match.addToDiscardStack(activePlayer.getSelectedCard());
							activePlayer.getSelectedCard().throwThisCard(activePlayer);
							askAgainNumCard = false;
						}
						// ---- Selectionner une autre carte ----
						else if (choice.equals("a")) {
							askAgainNumCard = true;
						}
					}
				} catch (SelectedCardNotDefinedException e) {
					e.getMessage();
				}
			}
		}
		System.out.println("Le joueur " + activePlayer.getName() + " a un total de " + activePlayer.getTotalMilage() + " km.");

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

		// On vide la ligne avant d'en lire d'autres car on a utilise
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
