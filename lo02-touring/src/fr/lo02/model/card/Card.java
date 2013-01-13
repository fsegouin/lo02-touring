package fr.lo02.model.card;

import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.exception.NotValidCardOnBattleException;

public abstract class Card {
	
	private boolean remedyCard = false;
	private boolean hazardCard = false;
	private boolean SafetyCard = false;
	private String fileName = "Null.jpg";
	private Match match;
	
	/**
	 * Afficher la carte courante
	 */
	public String toString() {
		return this.getClass().getSimpleName();
	}


	/**
	 * Verification de la validite d'une action
	 * @param activePlayer Joueur actif
	 * @param targetPlayer Joueur cible
	 * @return Renvoie le joueur ciblable
	 * @throws NotValidCardOnBattleException Exception si une carte ne peut pas etre jouee
	 */
	public abstract Player checkValidMove(Player activePlayer, Player targetPlayer) throws NotValidCardOnBattleException;
	
	
	/**
	 * Jeter la carte selectionnee et declenche le tour suivant
	 * @param activePlayer Joueur actif
	 */
	public void throwThisCard(Player activePlayer) {
		activePlayer.removeFromHand(this);
		Match.getInstance().next();
		Match.getInstance().changed();
	}
	
	/**
	 * Jouer la carte selectionnee et declenche le tour suivant
	 * @param activePlayer Joueur actif
	 * @param targetedPlayer Joueur cible
	 * @return Renvoie une carte dans le cas d'un coup fourre, null dans le cas contraire
	 */
	public Card playThisCard(Player activePlayer, Player targetedPlayer) {
		activePlayer.removeFromHand(this);
		Match.getInstance().next();
		Match.getInstance().changed();
		return null;
	}

	/*
	 * --------------- GETTERS AND SETTERS -----------------------
	 */
	
	/**
	 * Obtenir le chemin du visuel de la carte
	 * @return Renvoie le chemin
	 */
	
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Definir le chemin du visuel de la carte
	 * @param fileName Chemin a fournir
	 */

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Savoir si la carte est de type RemedyCard
	 * @return Vrai si c'est le cas, faux dans le cas contraire
	 */
	
	public boolean isRemedyCard() {
		return remedyCard;
	}
	
	/**
	 * Definir la carte de type RemedyCard
	 * @param remedyCard Vrai si c'est le cas, faux dans le cas contraire
	 */
	
	public void setRemedyCard(boolean remedyCard) {
		this.remedyCard = remedyCard;
	}

	/**
	 * Savoir si la carte est de type HazardCard
	 * @return Vrai si c'est le cas, faux dans le cas contraire
	 */

	public boolean isHazardCard() {
		return hazardCard;
	}
	
	/**
	 * Definir la carte de type HazardCard
	 * @param hazardCard Vrai si c'est le cas, faux dans le cas contraire
	 */
	
	public void setHazardCard(boolean hazardCard) {
		this.hazardCard = hazardCard;
	}

	/**
	 * Savoir si la carte est de type SafetyCard
	 * @return Vrai si c'est le cas, faux dans le cas contraire
	 */

	public boolean isSafetyCard() {
		return SafetyCard;
	}
	
	/**
	 * Definir la carte de type SafetyCard
	 * @param safetyCard Vrai si c'est le cas, faux dans le cas contraire
	 */
	
	public void setSafetyCard(boolean safetyCard) {
		SafetyCard = safetyCard;
	}
}
