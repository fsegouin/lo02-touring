package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.OutOfGas;
import fr.lo02.model.card.HazardCards.SpeedLimit;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public class Gasoline extends Card {

	public Gasoline(){
		this.setRemedyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException{
		Player p = null;
		 if (activePlayer.getLastCardFromBattle() instanceof OutOfGas) {
			 p = activePlayer;
		 }
		 else {
			 throw new NotValidCardOnBattleException("Vous n'etes pas en panne d'essence !");
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
