package fr.lo02.model.card.HazardCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class OutOfGas extends Card {

	public OutOfGas(){
		this.setHazardCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		 if (!(targetPlayer.getLastCardFromBattle() instanceof OutOfGas)) {
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
