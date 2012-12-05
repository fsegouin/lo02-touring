package fr.lo02.model.card;

import fr.lo02.model.stack.Hand;
import fr.lo02.model.stack.PlayerStack;

public class Card {

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	public boolean playThisCard(Hand from, PlayerStack to) {
		return true;
	}

}
