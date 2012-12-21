package fr.lo02.model.card;

import fr.lo02.model.Player;
import fr.lo02.model.card.remedyCards.GoRoll;

public class Distance extends Card {

	int milage;

	public Distance(int milage) {
		super();
		this.milage = milage;
	}

	public String toString() {
		return "Distance [milage=" + milage + "]";
	}

	 public Player checkValidMove(Player activePlayer, Player targetPlayer) {
		 Player p = null;
		 if (activePlayer.getLastCardFromBattle() instanceof GoRoll) {
			 p = activePlayer;
			 System.out.println("Vous n'avez pas de feu vert.");
		 }
		return p;
	 }
	 
	 
	// DANS CETTE METHODE TU DOIS APPELLER LA METHODE AU DESSUS POUR CHECK SI C'EST JOUABLE ET DONC NE PAS FAIRE LE TEST DANS CELLE CI
	public void playThisCard(Player activePlayer, Player targetedPlayer) {
		// TODO Auto-generated method stub
		if(activePlayer.getLastCardFromBattle() instanceof GoRoll) {
			super.playThisCard(activePlayer, targetedPlayer);
			activePlayer.addToDistance(this);
			activePlayer.addMilage(this.milage);
		}
		
	}
	
	

}
