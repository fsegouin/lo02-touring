package fr.lo02.model.card.remedyCards;

import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.OutOfGas;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public class Gasoline extends Card {

	public Gasoline(){
		this.setFileName("Essence.jpg");
		this.setRemedyCard(true);
	}
	
	public Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException{
		Player p = null;
		 if (activePlayer.getLastCardFromBattle() instanceof OutOfGas) {
			 p = activePlayer;
		 }
		 else {
			 throw new NotValidCardOnBattleException("Vous n'etes pas en panne d'essence.");
		 }
		return p;
	}

	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		targetedPlayer.addToBattle(this);
		super.playThisCard(activePlayer, targetedPlayer);

		for (int i = 0; i < 2; i++) {
			Match.getInstance().addToDiscardStack(activePlayer.getLastCardFromBattle());
			activePlayer.deleteLastCardFromBattle();
		}
		return null;
	}

}
