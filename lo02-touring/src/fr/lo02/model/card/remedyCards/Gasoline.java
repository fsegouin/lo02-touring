package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.OutOfGas;
import fr.lo02.model.card.HazardCards.SpeedLimit;

public class Gasoline extends Card {

	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		 if (activePlayer.getLastCardFromBattle() instanceof OutOfGas) {
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
