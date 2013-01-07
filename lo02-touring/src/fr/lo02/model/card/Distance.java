package fr.lo02.model.card;

import fr.lo02.model.Player;
import fr.lo02.model.card.HazardCards.SpeedLimit;
import fr.lo02.model.card.remedyCards.GoRoll;
import fr.lo02.model.card.HazardCards.Stop;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public class Distance extends Card {

	int milage;

	public Distance(int milage) {
		super();
		switch (milage) {
		case 25:
			this.setFileName("Speed25.jpg");
			break;
		case 50:
			this.setFileName("Speed50.jpg");
			break;
		case 75:
			this.setFileName("Speed75.jpg");
			break;
		case 100:
			this.setFileName("Speed100.jpg");
			break;
		case 200:
			this.setFileName("Speed200.jpg");
			break;
		default:
			this.setFileName("Null.jpg");
		}
		this.milage = milage;
	}

	public String toString() {
		return "Distance [milage=" + milage + "]";
	}

	/**
	 * Les conditions sont: - La premiere carte de battle est une "RemedyCard" -
	 * SpeedLimit a 50 -
	 * 
	 * @return Le joueur qui peut etre cible
	 */
	public Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException {
		Player p = null;
		// La premiere carte N'EST PAS un "Feux vert" (aucune autre carte jouer)
		if (activePlayer.battleSize() == 0) {
			throw new NotValidCardOnBattleException("Vous devez poser un \"Feu Vert\" pour commencer a poser des kilometres.");
		}
		// Si c'est une carte Stop
		else if (activePlayer.getLastCardFromBattle() instanceof Stop)
			throw new NotValidCardOnBattleException("Vous avez un Feu Rouge, veuillez poser un Feu Vert pour continuer.");
		// Si il y a une "SpeedLimit" et que la carte est une distance 50
		else if (activePlayer.getLastCardFromSpeed() instanceof SpeedLimit) {
			if (this.getMilage() <= 50)
				p = activePlayer;
			else
				throw new NotValidCardOnBattleException("Vous ne pouvez pas poser plus de 50 kilometres (speedLimit).");
		}
		// Si c'est une "remedyCards" (feux vert compris dedans)
		else if (activePlayer.getLastCardFromBattle() instanceof GoRoll) {
			p = activePlayer;
		}
		// Sinon la voiture doit etre repare
		else {
			throw new NotValidCardOnBattleException("Reparez votre voiture avant de poser des kilometres.");
		}
		return p;
	}

	public int getMilage() {
		return milage;
	}

	// DANS CETTE METHODE TU DOIS APPELLER LA METHODE AU DESSUS POUR CHECK SI
	// C'EST JOUABLE ET DONC NE PAS FAIRE LE TEST DANS CELLE CI
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		if (activePlayer.getLastCardFromBattle() instanceof GoRoll) {
			activePlayer.addToDistance(this);
			activePlayer.addMilage(this.milage);
			System.out.println("KILOMETRE" + activePlayer.getTotalMilage());
			super.playThisCard(activePlayer, targetedPlayer);
		}
		return null;
	}

}
