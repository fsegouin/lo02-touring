package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.SpeedLimit;
import fr.lo02.model.card.HazardCards.Stop;

public class EndOfLimit extends Card {

	public EndOfLimit(){
		this.setRemedyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		Player p = null;
		 if (activePlayer.getLastCardFromSpeed() instanceof SpeedLimit) {
			 p = activePlayer;
		 }
		return p;
	}

	@Override
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		super.playThisCard(activePlayer, targetedPlayer);
		for (int i = 0; i < 2; i++) {
			Match.getInstance().addToDiscardStack(activePlayer.getLastCardFromBattle());
			activePlayer.deleteLastCardFromBattle();
		}
	}

}
