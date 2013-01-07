package fr.lo02.controller.gui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.exception.SelectedCardNotDefinedException;

public class ConsoleHandGUI implements Observer {

	public ConsoleHandGUI() {
		// TODO Auto-generated constructor stub
	}
	
	public void addHandObserver(LinkedList<Player> linkedList) {
		for (Iterator iterator = linkedList.iterator(); iterator.hasNext();) {
			Player player = (Player) iterator.next();
			player.addObserver(this);
		}
	}

	public void update(Observable arg0, Object arg1) {
		Match match = Match.getInstance();
		Player activePlayer = match.getActivePlayer();
		int cardIndex = 0;
		HashSet<Player> lp = null;
		boolean askAgainNumCard = true;
		boolean askAgainPlayerName = true;
		Scanner scan = new Scanner(System.in);
		Player selectedPlayer = null;
		
		System.out.println("\n" + activePlayer.getName() + " c'est a vous");
		System.out.println("-------------------------------------------------------");
		if(activePlayer.getLastCardFromBattle() != null)
		System.out.println("Votre etat est : " + activePlayer.getLastCardFromBattle().toString());
		System.out.println("Votre main est" +
				" : " + activePlayer.showHand());
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
						System.out.println("JOUEUR ACTIF : " +match.getActivePlayer().getName());
						int i = 0;
						//deroule les joueurs ciblable
						for (Iterator<Player> iterator = lp.iterator(); iterator.hasNext();) {
							Player player = (Player) iterator.next();
							i++;
							System.out.println(i + " - " + player.getName());
						}
						askAgainPlayerName = true;
						// ACTION AVEC LA CARTE SELECTIONNE
						while (askAgainPlayerName) {
							System.out.println("Tapez le nom du joueur a cibler, (a) pour changer de carte, (d) pour vous defausser de cette carte.");
							String choice = scan.nextLine();
							
							// ---- Se defausser ----
							if (choice.equals("d")) {
								activePlayer.setSelectedCard(cardIndex);
								System.out.println("Vous vous defaussez de : " + activePlayer.getSelectedCard().toString());
								match.addToDiscardStack(activePlayer.getSelectedCard());
								activePlayer.getSelectedCard().throwThisCard(activePlayer);
								askAgainPlayerName = false;
								askAgainNumCard = false;
							}
							// ---- Selectionner une autre carte ----
							else if (choice.equals("a")) {
								askAgainPlayerName = false;
								askAgainNumCard = true;
							}
							// ---- Jouer la carte selectionne ----
							else if (match.getPlayerByName(choice) != null) {
								selectedPlayer = match.getPlayerByName(choice);
								Card cSafetyCard = activePlayer.getSelectedCard().playThisCard(activePlayer, selectedPlayer);
								// Si une carte est renvoye par playThisCard, c'est que le joueur peut jouer un coup fourre
								//---- Demande de coup fourre ----
								if(cSafetyCard != null) {
									targetPlayerCoupFourre(match, selectedPlayer);
									selectedPlayer.coupFourre(cSafetyCard);
								}
								askAgainPlayerName = false;
								askAgainNumCard = false;
							}
							// ---- Saisie invalide ----
							else {
								System.out.println("Saisie non valide.");
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
	}

	/**
	 * Permet de lancer un coup fourre
	 * @param match
	 * @param selectedPlayer
	 */
	public static void targetPlayerCoupFourre(Match match, Player selectedPlayer) {
		Scanner scan = new Scanner(System.in);
		System.out.println(selectedPlayer.getName() +" voulez vous jouer votre coup fourre ?");
		String choice = scan.nextLine();
		//scan.close();
		if (choice.equals("oui")) {
			System.out.println("Le joueur " + selectedPlayer.getName() +" fait un coup fourre");
		match.setNextPlayer(selectedPlayer);
		}
	}
	
}

