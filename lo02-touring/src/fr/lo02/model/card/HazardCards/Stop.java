package fr.lo02.model.card.HazardCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.remedyCards.GoRoll;

public class Stop extends Card {

	public Stop() {
		this.setHazardCard(true);
	}

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		// Si il possede pas la botte "RightOfWay" et que la derniere carte est
		// de type "RemedyCards"
		if (!(targetPlayer.isRightOfWay()) && targetPlayer.getLastCardFromBattle().isRemedyCard()) {
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
