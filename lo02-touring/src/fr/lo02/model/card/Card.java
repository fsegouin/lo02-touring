package fr.lo02.model.card;

import fr.lo02.model.CardList;
import fr.lo02.model.Player;
import fr.lo02.model.stack.Hand;

public abstract class Card {

	public String toString() {
		return this.getClass().getSimpleName();
	}

	/*
	 * public void checkValidMove(Card source, CardList destination) { //appelle
	 * a la methode presente dans chaque card //si elle retourne True >>>
	 * //playThisCard(); //sinon... }
	 */

	public void playThisCard(Player activePlayer, Hand sourceHand,
			CardList destinationStack) {

		sourceHand.remove(this);

		// Il faut implemanter destinationStack.toStack() dans la surcharge de
		// la methode sur chaque classe de carte

	}
}
