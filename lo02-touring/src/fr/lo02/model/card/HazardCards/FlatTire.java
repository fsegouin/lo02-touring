package fr.lo02.model.card.HazardCards;

import java.util.Iterator;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.SafetyCards.PunctureProof;
import fr.lo02.model.card.remedyCards.GoRoll;

public class FlatTire extends Card {

	public FlatTire() {
		this.setFileName("Creve.jpg");
		this.setHazardCard(true);
	}

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		// Si il possede pas la botte "PunctureProof" et que la derniere carte est
		// type "RemedyCards"
		if (!(targetPlayer.isPunctureProof()) && targetPlayer.getLastCardFromBattle() instanceof GoRoll) {
			p = targetPlayer;
		}
		return p;
	}

	@Override
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		Card cSafetyCard = null;
		targetedPlayer.addToBattle(this);	
		super.playThisCard(activePlayer, targetedPlayer);

		// Recherche si le targetPlayer peut faire un coup fourre
		for (Iterator iterator = targetedPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if(c instanceof PunctureProof) {
				cSafetyCard = c;
			}
		}
		return cSafetyCard;
	}

}
