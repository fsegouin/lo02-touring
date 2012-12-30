package fr.lo02.model.stack;

import java.util.ArrayList;
import java.util.Iterator;

import fr.lo02.model.card.Card;
import fr.lo02.model.card.SafetyCards.DrivingAce;

public class Hand extends ArrayList<Card> {
	
	public void removeFromHand(Card aCard) {
		this.remove(aCard);
	}
	
}