package fr.lo02.model.card.HazardCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class Stop extends Card {

	public boolean checkValidMove(Player activePlayer, Player targetPlayer) {
		return false;
	}

}
