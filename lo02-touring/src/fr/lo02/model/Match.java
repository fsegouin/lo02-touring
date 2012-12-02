package fr.lo02.model;

import fr.lo02.controller.Game;
import fr.lo02.model.card.HazardCards.*;
import fr.lo02.model.card.remedyCards.GoRoll;
import fr.lo02.model.stack.GameStack;

public class Match {
	
	public void GameStackInit() {
		
		GameStack gameStack = new GameStack();
		
		for (int i = 0; i<2; i++) { // 2 stop cards
			Stop aStop = new Stop();
			gameStack.toStack(aStop);
		}
		
		for (int i = 0; i<5; i++) { // 2 roll cards
			GoRoll aGoRoll = new GoRoll();
			gameStack.toStack(aGoRoll);
		}
		
		System.out.println("Nous avons " + gameStack.getCardCounter() + " cartes dans la pioche principale.");
		
	}
	
	public static void main(String[] args) { // ONLY FOR TEST PURPOSES
		Match aMatch = new Match();
		aMatch.GameStackInit();
	}
	

}
