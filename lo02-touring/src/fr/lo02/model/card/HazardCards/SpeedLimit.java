package fr.lo02.model.card.HazardCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.remedyCards.GoRoll;

public class SpeedLimit extends Card {

	public SpeedLimit() {
		this.setHazardCard(true);
	}

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		// Si la pile speed est vide ( = null) ou si il y a un "EndOfLimit"
		if (!(targetPlayer.getLastCardFromSpeed() instanceof SpeedLimit)) {
			p = targetPlayer;
		}
		return p;
	}

	@Override
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		super.playThisCard(activePlayer, targetedPlayer);
		targetedPlayer.addToSpeed(this);
	}

}