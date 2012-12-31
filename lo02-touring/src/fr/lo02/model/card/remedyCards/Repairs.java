package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.Accident;
import fr.lo02.model.card.HazardCards.Stop;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public class Repairs extends Card {

	public Repairs(){
		this.setRemedyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException{
		Player p = null; 
		 if (activePlayer.getLastCardFromBattle() instanceof Accident) {
			 p = activePlayer;
		 }
		 else {
			 throw new NotValidCardOnBattleException("Vous n'etes pas accidenté.");
		 }
		return p;
	}
	
	@Override
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		super.playThisCard(activePlayer, targetedPlayer);
		for (int i = 0; i < 2; i++) {
			Match.getInstance().addToDiscardStack(activePlayer.getLastCardFromBattle());
			activePlayer.deleteLastCardFromBattle();
		}
		return null;
	}

}
