package fr.lo02.model.card.SafetyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class RightOfWay extends Card {

	public boolean checkValidMove(Player activePlayer, Player targetPlayer) {
		return false;
	}

}
