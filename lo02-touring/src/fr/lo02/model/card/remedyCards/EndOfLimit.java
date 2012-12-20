package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class EndOfLimit extends Card {

	public boolean checkValidMove(Player activePlayer, Player targetPlayer) {
		return false;
	}

}
