package fr.lo02.model.card.SafetyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class RightOfWay extends Card {

	public RightOfWay() {
		this.setFileName("Prioritaire.jpg");
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		return activePlayer; // La bote peut etre jouee tout le temps, et uniquement sur soi meme
	}

	@Override
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		activePlayer.addToSafetyArea(this);
		activePlayer.setRightOfWay(true);
		super.playThisCard(activePlayer, targetedPlayer);
		return null;
	}

}
