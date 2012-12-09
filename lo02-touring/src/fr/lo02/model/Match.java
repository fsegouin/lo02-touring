package fr.lo02.model;

import java.util.ArrayList;
import java.util.Scanner;

import fr.lo02.model.card.Card;
import fr.lo02.model.card.Distance;
import fr.lo02.model.card.HazardCards.*;
import fr.lo02.model.card.remedyCards.*;
import fr.lo02.model.card.SafetyCards.*;
import fr.lo02.model.stack.Hand;

public class Match {

	CardList gameStack = new CardList();

	private int activePlayer = 0;

	private ArrayList<Player> listPlayer = new ArrayList<Player>();

	public Match(int nbComputerPlayer, int nbHumanPlayer, String[] namePlayer) {
		this.playerInit(nbComputerPlayer, nbHumanPlayer, namePlayer);
		this.gameStackInit();
		this.playerStackInit();
		this.playMatch();
	}

	public void playMatch() {

		// WIP //

		Scanner scan = new Scanner(System.in);
		int cardIndex = 0;

		Player player;

		do {

			boolean askAgain = true;

			player = nextPlayer();

			System.out.println("Votre main actuelle est : "
					+ player.showHand());

			Card c = player.pickCard(gameStack);

			System.out.println("Vous piochez : " + c.toString());

			System.out.println("Votre nouvelle main est : "
					+ player.showHand());

			do {
				System.out
						.println("Que souhaitez-vous jouer ? Entrez le numero de la carte.");

				try {
					cardIndex = Integer.parseInt(scan.nextLine());
				} catch (NumberFormatException e) {
					continue;
				}

				if (cardIndex <= (player.hand.size()))
					askAgain = false;

			} while (askAgain);

			player.selectedCard(cardIndex).playThisCard(player, null);

			System.out.println("Kilometres parcourus par " + player.getName()
					+ " : " + player.getTotalMilage());

		} while (player.kmCheck());

	}

	public Player nextPlayer() {
		activePlayer++;

		if (activePlayer > listPlayer.size()) {
			activePlayer = 1;
		}

		Player nextPlayer = listPlayer.get(activePlayer - 1);

		System.out.println("\n" + nextPlayer.getName() + " c'est a vous !");

		return nextPlayer;
	}

	/*
	 * Create the list of player(human and computer)
	 */

	public void playerInit(int nbComputerPlayer, int nbHumanPlayer,
			String[] namePlayer) {

		for (int i = 0; i < nbHumanPlayer; i++) {
			HumanPlayer humanplayer = new HumanPlayer(namePlayer[i], i);
			this.listPlayer.add(humanplayer);
		}
		for (int i = nbHumanPlayer - 1; i < nbComputerPlayer; i++) {
			ComputerPlayer computerplayer = new ComputerPlayer(namePlayer[i], i);
			this.listPlayer.add(computerplayer);
		}
	}

	/**
	 * Permet de distribuer une main a chaque joueur
	 */

	public void playerStackInit() {

		for (Player p : listPlayer) {
			for (int i = 0; i < 4; i++) {
				p.hand.add(gameStack.topPick());
			}
		}

		/*
		 * for (Player p : listPlayer) {
		 * System.out.println(p.myStack.stack.toString()); }
		 * 
		 * System.out.println("Les mains des joueurs ont ete distribuees.");
		 */

	}

	public void gameStackInit() { // Initialise la pioche de jeu principale

		for (int i = 0; i < 2; i++) { // 2 stop cards
			Stop aStop = new Stop();
			gameStack.toStack(aStop);
		}

		for (int i = 0; i < 5; i++) { // 2 roll cards
			GoRoll aGoRoll = new GoRoll();
			gameStack.toStack(aGoRoll);
		}

		/*
		 * for (int i = 0; i < 2; i++) { SpeedLimit aSpeedLimit = new
		 * SpeedLimit(); gameStack.toStack(aSpeedLimit); }
		 * 
		 * for (int i = 0; i < 4; i++) { EndOfLimit anEndOfLimit = new
		 * EndOfLimit(); gameStack.toStack(anEndOfLimit); }
		 * 
		 * for (int i = 0; i < 2; i++) { OutOfGas anOutOfGas = new OutOfGas();
		 * gameStack.toStack(anOutOfGas); }
		 * 
		 * for (int i = 0; i < 2; i++) { FlatTire aFlatTire = new FlatTire();
		 * gameStack.toStack(aFlatTire); }
		 * 
		 * for (int i = 0; i < 2; i++) { Accident anAccident = new Accident();
		 * gameStack.toStack(anAccident); }
		 * 
		 * for (int i = 0; i < 4; i++) { Gasoline aGasoline = new Gasoline();
		 * gameStack.toStack(aGasoline); }
		 * 
		 * for (int i = 0; i < 4; i++) { Repairs aRepairs = new Repairs();
		 * gameStack.toStack(aRepairs); }
		 * 
		 * for (int i = 0; i < 4; i++) { SpareTire aSpareTire = new SpareTire();
		 * gameStack.toStack(aSpareTire); }
		 * 
		 * // 4 bottes du jeu
		 * 
		 * RightOfWay aRightOfWay = new RightOfWay();
		 * gameStack.toStack(aRightOfWay);
		 * 
		 * DrivingAce aDrivingAce = new DrivingAce();
		 * gameStack.toStack(aDrivingAce);
		 * 
		 * ExtraTank anExtraTank = new ExtraTank();
		 * gameStack.toStack(anExtraTank);
		 * 
		 * PunctureProof aPunctureProof = new PunctureProof();
		 * gameStack.toStack(aPunctureProof);
		 */

		// Distance cards

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(25);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(50);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(75);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 9; i++) {
			Distance aDistance = new Distance(100);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 3; i++) {
			Distance aDistance = new Distance(200);
			gameStack.toStack(aDistance);
		}

		gameStack.shuffleCards(); // Shuffle the stack

		System.out.println("Nous avons " + gameStack.getCardCounter()
				+ " cartes dans la pioche principale.");

		System.out.println("Contenu de la pioche :");
		System.out.println(gameStack.toString());

	}

}
