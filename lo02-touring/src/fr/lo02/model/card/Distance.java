package fr.lo02.model.card;

import fr.lo02.model.CardList;
import fr.lo02.model.Player;
import fr.lo02.model.stack.Hand;

public class Distance extends Card {

	int milage;

	public Distance(int milage) {
		super();
		this.milage = milage;
	}

	@Override
	public String toString() {
		return "Distance [milage=" + milage + "]";
	}

	@Override
	public void playThisCard(Player activePlayer, Hand sourceHand,
			CardList destinationStack) {
		super.playThisCard(activePlayer, sourceHand, destinationStack);
		activePlayer.distancePile.addToTotalMilage(this.milage);
	}

}
