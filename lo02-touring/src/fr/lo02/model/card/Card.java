package fr.lo02.model.card;

import fr.lo02.model.Player;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public abstract class Card {
	
	private boolean remedyCard = false;
	private boolean hazardCard = false;
	private boolean SafetyCard = false;

	public String toString() {
		return this.getClass().getSimpleName();
	}

	public abstract Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException;
	
	public void throwThisCard(Player activePlayer) {
		activePlayer.removeFromHand(this);
	}
	
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		activePlayer.removeFromHand(this);
		return null;
	}

	/*
	 * --------------- GETTERS AND SETTERS -----------------------
	 */
	
	public boolean isRemedyCard() {
		return remedyCard;
	}
	public void setRemedyCard(boolean remedyCard) {
		this.remedyCard = remedyCard;
	}


	public boolean isHazardCard() {
		return hazardCard;
	}
	public void setHazardCard(boolean hazardCard) {
		this.hazardCard = hazardCard;
	}


	public boolean isSafetyCard() {
		return SafetyCard;
	}
	public void setSafetyCard(boolean safetyCard) {
		SafetyCard = safetyCard;
	}
}
