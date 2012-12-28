package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.Accident;
import fr.lo02.model.card.HazardCards.FlatTire;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public class SpareTire extends Card {

	public SpareTire(){
		this.setRemedyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException{
		Player p = null; 
		 if (activePlayer.getLastCardFromBattle() instanceof FlatTire) {
			 p = activePlayer;
		 }
		 else {
			 throw new NotValidCardOnBattleException("Vous n'avais pas de roue crevé !");
		 }
		return p;
	}

}
