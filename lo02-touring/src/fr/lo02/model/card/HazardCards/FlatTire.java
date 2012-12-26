package fr.lo02.model.card.HazardCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class FlatTire extends Card {

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		 if (!(targetPlayer.getLastCardFromBattle() instanceof FlatTire)) {
			 p = targetPlayer;
		 }
		return p;
	}

	@Override
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		super.playThisCard(activePlayer, targetedPlayer);
		targetedPlayer.addToBattle(this);
	}

}
