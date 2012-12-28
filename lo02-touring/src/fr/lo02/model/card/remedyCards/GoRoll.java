package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.Stop;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public class GoRoll extends Card {
	
	public GoRoll(){
		this.setRemedyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException {
		 Player p = null;
		 if (activePlayer.getLastCardFromBattle() instanceof Stop || activePlayer.getLastCardFromBattle() == null) {
			 p = activePlayer;
		 }
		 else {
			 throw new NotValidCardOnBattleException("Vous n'etes pas stoppe par un feu rouge.");
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
 