package fr.lo02.model.card.HazardCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.remedyCards.GoRoll;

public class Stop extends Card {

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		if (targetPlayer.getLastCardFromBattle() instanceof GoRoll)
			p = targetPlayer;
		return p;
	}

}
