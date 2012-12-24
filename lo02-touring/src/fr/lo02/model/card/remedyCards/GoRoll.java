package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.Stop;

public class GoRoll extends Card {

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		 Player p = null;
		 if (activePlayer.getLastCardFromBattle() instanceof Stop || activePlayer.getLastCardFromBattle() == null) {
			 p = activePlayer;
		 }
		return p;
	}

	@Override
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		super.playThisCard(activePlayer, targetedPlayer);
		activePlayer.addToBattle(this);
	}

}
 