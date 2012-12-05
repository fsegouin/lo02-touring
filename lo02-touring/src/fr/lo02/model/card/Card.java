package fr.lo02.model.card;

import fr.lo02.model.CardList;
import fr.lo02.model.stack.Hand;

public abstract class Card {

	public String toString() {
		return this.getClass().getSimpleName();
	}
	

	
	public void checkValidMove(Card source, CardList destination) {
		//appelle a la methode presente dans chaque card
		//si elle retourne True >>>
		//playThisCard();
		//sinon...
	}
	
	public boolean playThisCard(Hand from, CardList to) {
		return true;
	}

}
