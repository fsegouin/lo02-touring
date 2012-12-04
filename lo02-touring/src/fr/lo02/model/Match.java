package fr.lo02.model;

import fr.lo02.controller.Game;
import fr.lo02.model.card.Distance;
import fr.lo02.model.card.HazardCards.*;
import fr.lo02.model.card.remedyCards.*;
import fr.lo02.model.card.SafetyCards.*;
import fr.lo02.model.stack.GameStack;

public class Match {
	
	// Kikoo après les 2 tests
	
	GameStack gameStack = new GameStack();

	public void GameStackInit() {

		for (int i = 0; i < 2; i++) { // 2 stop cards
			Stop aStop = new Stop();
			gameStack.toStack(aStop);
		}

		for (int i = 0; i < 5; i++) { // 2 roll cards
			GoRoll aGoRoll = new GoRoll();
			gameStack.toStack(aGoRoll);
		}

		for (int i = 0; i < 2; i++) {
			SpeedLimit aSpeedLimit = new SpeedLimit();
			gameStack.toStack(aSpeedLimit);
		}

		for (int i = 0; i < 4; i++) {
			EndOfLimit anEndOfLimit = new EndOfLimit();
			gameStack.toStack(anEndOfLimit);
		}

		for (int i = 0; i < 2; i++) {
			OutOfGas anOutOfGas = new OutOfGas();
			gameStack.toStack(anOutOfGas);
		}

		for (int i = 0; i < 2; i++) {
			FlatTire aFlatTire = new FlatTire();
			gameStack.toStack(aFlatTire);
		}

		for (int i = 0; i < 2; i++) {
			Accident anAccident = new Accident();
			gameStack.toStack(anAccident);
		}

		for (int i = 0; i < 4; i++) {
			Gasoline aGasoline = new Gasoline();
			gameStack.toStack(aGasoline);
		}

		for (int i = 0; i < 4; i++) {
			Repairs aRepairs = new Repairs();
			gameStack.toStack(aRepairs);
		}

		for (int i = 0; i < 4; i++) {
			SpareTire aSpareTire = new SpareTire();
			gameStack.toStack(aSpareTire);
		}

		// 4 bottes du jeu

		RightOfWay aRightOfWay = new RightOfWay();
		gameStack.toStack(aRightOfWay);

		DrivingAce aDrivingAce = new DrivingAce();
		gameStack.toStack(aDrivingAce);

		ExtraTank anExtraTank = new ExtraTank();
		gameStack.toStack(anExtraTank);

		PunctureProof aPunctureProof = new PunctureProof();
		gameStack.toStack(aPunctureProof);

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

	public static void main(String[] args) { // ONLY FOR TEST PURPOSES
		Match aMatch = new Match();
		aMatch.GameStackInit();
	}

}
