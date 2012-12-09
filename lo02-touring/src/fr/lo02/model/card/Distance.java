package fr.lo02.model.card;

import fr.lo02.model.Player;

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
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		super.playThisCard(activePlayer, targetedPlayer);
		activePlayer.addToDistance(this);
		activePlayer.addMilage(this.milage);
	}
	
	

}
