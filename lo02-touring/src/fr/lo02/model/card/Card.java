package fr.lo02.model.card;

import fr.lo02.model.Player;

public abstract class Card {

	public String toString() {
		return this.getClass().getSimpleName();
	}


	 public abstract Player checkValidMove(Player activePlayer, Player targetPlayer);
	
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		activePlayer.removeFromHand(this);
	}
	
}
