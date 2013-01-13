package fr.lo02.model.stack;

import java.util.ArrayList;

import fr.lo02.model.card.Card;

public class Hand extends ArrayList<Card> {
	
	/**
	 * Permet de supprimer une carte de la main
	 * @param aCard Carte a supprimer
	 */
	public void removeFromHand(Card aCard) {
		this.remove(aCard);
	}
	
}