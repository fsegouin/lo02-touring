package fr.lo02.model.card;

import fr.lo02.model.CardList;

public abstract class Card {

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	public void checkValidMove(Card source, CardList destination) {
		
	}

}
