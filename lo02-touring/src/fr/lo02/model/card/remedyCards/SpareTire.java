package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class SpareTire extends Card {

	public SpareTire(){
		this.setRemedyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		return null;
	}

}
