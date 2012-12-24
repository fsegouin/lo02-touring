package fr.lo02.model.card;

import fr.lo02.model.Player;
import fr.lo02.model.card.HazardCards.SpeedLimit;
import fr.lo02.model.card.remedyCards.GoRoll;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public class Distance extends Card {

	int milage;

	public Distance(int milage) {
		super();
		this.milage = milage;
	}

	public String toString() {
		return "Distance [milage=" + milage + "]";
	}

	/**
	 * Les conditions sont: - La premiere carte de battle est une "RemedyCard"
	 * 						- SpeedLimit a 50
	 * 						- 
	 * @return Le joueur qui peu etre ciblé
	 */
	public Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException {
		Player p = null;
		
		// La premiere carte N'EST PAS un "Feux vert" (aucune autre carte jouer)
		if (activePlayer.battleSize() == 0) {
			throw new NotValidCardOnBattleException("Vous devez poser un \"Feux vert\" pour commencer a poser des kilometres");
		}
		// Si c'est une "remedyCards" (feux vert compris dedans)
		if (activePlayer.getLastCardFromBattle().isRemedyCard()) {
			p = activePlayer;
		}
		// Si il y a une "SpeedLimit" et que la carte est une distance 50
		else if(activePlayer.getLastCardFromBattle() instanceof SpeedLimit) {
			if(this.getMilage() <=50)
			p = activePlayer;
			else throw new NotValidCardOnBattleException("Vous ne pouvez pas poser plus de 50 kilometres");
		}
		//Sinon la voiture doit etre repare
		else throw new NotValidCardOnBattleException("Reparer votre voiture avant de poser des kilometres");
		return p;
	}

	public int getMilage() {
		return milage;
	}

	// DANS CETTE METHODE TU DOIS APPELLER LA METHODE AU DESSUS POUR CHECK SI
	// C'EST JOUABLE ET DONC NE PAS FAIRE LE TEST DANS CELLE CI
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		if (activePlayer.getLastCardFromBattle() instanceof GoRoll) {
			super.playThisCard(activePlayer, targetedPlayer);
			activePlayer.addToDistance(this);
			activePlayer.addMilage(this.milage);
		}

	}

}
