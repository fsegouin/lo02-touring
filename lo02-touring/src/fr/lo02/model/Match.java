package fr.lo02.model;

import java.util.ArrayList;
import java.util.Scanner;

import fr.lo02.model.card.Distance;
import fr.lo02.model.card.HazardCards.*;
import fr.lo02.model.card.remedyCards.*;
import fr.lo02.model.card.SafetyCards.*;

public class Match {

	CardList cardlist = new CardList();

	private ArrayList<Player> listPlayer = new ArrayList<Player>();

	public Match(int nbComputerPlayer, int nbHumanPlayer, String[] namePlayer) {
		this.playerInit(nbComputerPlayer, nbHumanPlayer, namePlayer);
		this.gameStackInit();
		this.playerStackInit();
		//TEST ONLY
		this.playMatch();
	}

	public void playMatch() {

		// DEBUG PURPOSES -- TEST ONLY//
		
		Scanner sc = new Scanner(System.in);
		int nextCardIndex;
		
		System.out.println("C'est au tour de " + listPlayer.get(0).getName());
		System.out.println("Votre main actuelle est : " + listPlayer.get(0).hand.toString());
		System.out.println("Que souhaitez-vous jouer ? Entrez le numero de la carte.");
		nextCardIndex = sc.nextInt();
		
		System.out.println(listPlayer.get(0).hand.get(nextCardIndex).toString()); // TEST
		
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

	// Permet de distribuer une main a chaque joueur
	public void playerStackInit() { 

		for (Player p : listPlayer) {
			for (int i = 0; i < 4; i++) {
				p.hand.add(cardlist.topPick());
			}
		}
		
		// DEBUGGING PURPOSES
		
//		for (Player p : listPlayer) {
//			System.out.println(p.myStack.stack.toString());
//		}
//
//		System.out.println("Les mains des joueurs ont ŽtŽ distribuees.");
		
		// DEBUGGING PURPOSES

	}

	public void gameStackInit() { // Initialise la pioche de jeu principale

		for (int i = 0; i < 2; i++) { // 2 stop cards
			Stop aStop = new Stop();
			cardlist.toStack(aStop);
		}

		for (int i = 0; i < 5; i++) { // 2 roll cards
			GoRoll aGoRoll = new GoRoll();
			cardlist.toStack(aGoRoll);
		}

		for (int i = 0; i < 2; i++) {
			SpeedLimit aSpeedLimit = new SpeedLimit();
			cardlist.toStack(aSpeedLimit);
		}

		for (int i = 0; i < 4; i++) {
			EndOfLimit anEndOfLimit = new EndOfLimit();
			cardlist.toStack(anEndOfLimit);
		}

		for (int i = 0; i < 2; i++) {
			OutOfGas anOutOfGas = new OutOfGas();
			cardlist.toStack(anOutOfGas);
		}

		for (int i = 0; i < 2; i++) {
			FlatTire aFlatTire = new FlatTire();
			cardlist.toStack(aFlatTire);
		}

		for (int i = 0; i < 2; i++) {
			Accident anAccident = new Accident();
			cardlist.toStack(anAccident);
		}

		for (int i = 0; i < 4; i++) {
			Gasoline aGasoline = new Gasoline();
			cardlist.toStack(aGasoline);
		}

		for (int i = 0; i < 4; i++) {
			Repairs aRepairs = new Repairs();
			cardlist.toStack(aRepairs);
		}

		for (int i = 0; i < 4; i++) {
			SpareTire aSpareTire = new SpareTire();
			cardlist.toStack(aSpareTire);
		}

		// 4 bottes du jeu

		RightOfWay aRightOfWay = new RightOfWay();
		cardlist.toStack(aRightOfWay);

		DrivingAce aDrivingAce = new DrivingAce();
		cardlist.toStack(aDrivingAce);

		ExtraTank anExtraTank = new ExtraTank();
		cardlist.toStack(anExtraTank);

		PunctureProof aPunctureProof = new PunctureProof();
		cardlist.toStack(aPunctureProof);

		// Distance cards

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(25);
			cardlist.toStack(aDistance);
		}

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(50);
			cardlist.toStack(aDistance);
		}

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(75);
			cardlist.toStack(aDistance);
		}

		for (int i = 0; i < 9; i++) {
			Distance aDistance = new Distance(100);
			cardlist.toStack(aDistance);
		}

		for (int i = 0; i < 3; i++) {
			Distance aDistance = new Distance(200);
			cardlist.toStack(aDistance);
		}

		cardlist.shuffleCards(); // Shuffle the stack

		System.out.println("Nous avons " + cardlist.getCardCounter()
				+ " cartes dans la pioche principale.");
//		System.out.println("Contenu de la pioche :");
//		System.out.println(gameStack.toString());

		System.out.println("Contenu de la pioche :");
		System.out.println(cardlist.toString());

	}

}
