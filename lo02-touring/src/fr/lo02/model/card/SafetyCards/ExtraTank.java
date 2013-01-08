package fr.lo02.model.card.SafetyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class ExtraTank extends Card {

	public ExtraTank() {
		this.setFileName("Citerne.jpg");
		this.setSafetyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		return activePlayer; // La bote peut etre jouee tout le temps, et uniquement sur soi meme
	}

	@Override
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		activePlayer.addToSafetyArea(this);
		activePlayer.setExtraTank(true);
		super.playThisCard(activePlayer, targetedPlayer);
		return null;
	}
}
