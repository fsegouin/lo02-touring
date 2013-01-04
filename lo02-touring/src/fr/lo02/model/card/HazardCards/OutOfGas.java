package fr.lo02.model.card.HazardCards;

import java.util.Iterator;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.SafetyCards.ExtraTank;
import fr.lo02.model.card.remedyCards.GoRoll;

public class OutOfGas extends Card {

	public OutOfGas() {
		this.setHazardCard(true);
	}

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		// Si il possede pas la botte "ExtraTank" et que la derniere carte est
		// de type "RemedyCards"
		if (!(targetPlayer.isExtraTank()) && targetPlayer.getLastCardFromBattle() instanceof GoRoll) {
			p = targetPlayer;
		}
		return p;
	}

	@Override
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		Card cSafetyCard = null;
		super.playThisCard(activePlayer, targetedPlayer);
		targetedPlayer.addToBattle(this);	
		
		// Recherche si le targetPlayer peut faire un coup fourre
		for (Iterator iterator = targetedPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if(c instanceof ExtraTank) {
				cSafetyCard = c;
			}
		}
		return cSafetyCard;
	}

}
