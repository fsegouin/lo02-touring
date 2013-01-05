package fr.lo02.model.card.HazardCards;

import java.util.Iterator;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.SafetyCards.RightOfWay;
import fr.lo02.model.card.remedyCards.GoRoll;

public class SpeedLimit extends Card {

	public SpeedLimit() {
		this.setFileName("Limite_50.jpg");
		this.setHazardCard(true);
	}

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		// Si la pile speed est vide ( = null) ou si il y a un "EndOfLimit"
		if (!(targetPlayer.isRightOfWay()) && targetPlayer.getLastCardFromBattle() instanceof GoRoll) {
			p = targetPlayer;
		}
		return p;
	}

	
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		Card cSafetyCard = null;
		super.playThisCard(activePlayer, targetedPlayer);
		targetedPlayer.addToBattle(this);	
		
		// Recherche si le targetPlayer peut faire un coup fourre
		for (Iterator iterator = targetedPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if(c instanceof RightOfWay) {
				cSafetyCard = c;
			}
		}
		return cSafetyCard;
	}

}