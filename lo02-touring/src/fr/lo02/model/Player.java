package fr.lo02.model;

import java.util.Observable;

import fr.lo02.model.card.Card;
import fr.lo02.model.stack.DistancePile;
import fr.lo02.model.stack.Hand;

public class Player extends Observable {

	private final int WINNER_DISTANCE = 1000;

	private String name;
	private int color;
	protected Hand hand = new Hand(); // Main du joueur
	private DistancePile distancePile = new DistancePile();// Pile distance
	private CardList battlePile = new CardList(); // Pile des cartes battle
	private CardList speedPile = new CardList(); // Pile des cartes limite devitesse
	private CardList safetyArea = new CardList();
	
	private boolean drivingAce = false;
	private boolean punctureProof = false;
	private boolean rightOfWay = false;
	private boolean extraTank = false;
	private boolean active = false;

	private Card selectedCard;

	public Player(String name, int color){ 
		this.name = name;
		this.color = color; // NOT USED FOR NOW
	}

	/**
	 * Pioche une carte, la supprime de sa pile donnee en parametre puis l'ajoute
	 * dans la main du joueur
	 * @param source Carte a piocher depuis cette pile
	 * @return Carte piochee
	 */
	public Card pickCard(CardList source) {
		Card c = source.topPick();
		this.hand.add(c);
		return c;
	}
	
	/**
	 * Le joueur realise un coup fourre
	 * @param cSafetyCard La carte qui permet le coup fourre (en main)
	 */
	public void coupFourre(Card cSafetyCard) {
		System.out.println("--- ! COUP FOURRE ! ---");
		Match match = Match.getInstance();
		
		//Etape 1 : Il joue sa SafetyCard sur lui meme
		cSafetyCard.playThisCard(this, null);
		
		//Etape 2: Supprime la HazardCard
		match.addToDiscardStack(this.getLastCardFromBattle());
		this.deleteLastCardFromBattle();

		// Etape 3 : Le joueur avance de 300km et pioche//
		this.pickCard(match.getGameStack());
		this.addMilage(300);
	}
	
	/**
	 * Ajouter une carte sur la pile distance
	 * @param aCard Carte a ajouter
	 */

	public void addToDistance(Card aCard) {
		this.distancePile.toStack(aCard);
	}
	
	/**
	 * Ajouter une carte sur la pile vitesse
	 * @param aCard Carte a ajouter
	 */

	public void addToSpeed(Card aCard) {
		this.speedPile.toStack(aCard);
	}
	
	/**
	 * Ajouter une carte sur la pile bataille
	 * @param aCard Carte a ajouter
	 */

	public void addToBattle(Card aCard) {
		this.battlePile.toStack(aCard);
	}
	
	/**
	 * Ajouter une carte sur la pile des bottes
	 * @param aCard Carte a ajouter
	 */
	
	public void addToSafetyArea(Card aCard) {
		this.safetyArea.toStack(aCard);
	}
	
	/**
	 * Supprimer une carte depuis la main du joueur
	 * @param aCard Carte a supprimer
	 */

	public void removeFromHand(Card aCard) {
		this.hand.removeFromHand(aCard);
	}
	
	/**
	 * Ajouter une distance au kilometrage total du joueur
	 * @param km Entier a ajouter
	 */

	public void addMilage(int km) {
		this.distancePile.addToTotalMilage(km);
	}
	
	/**
	 * Verification du kilometrage du joueur
	 * @return Renvoie vrai si le joueur a depasse la limite du jeu, faux dans le cas contraire
	 */

	public boolean kmCheck() {
		if (distancePile.getTotalMilage() >= WINNER_DISTANCE)
			return true;
		else
			return false;
	}

	/*
	 * --------------- SIZE -----------------------
	 */
	
	/**
	 * Obtenir la taille de la main du joueur
	 * @return Renvoie la taille
	 */

	public int handSize() {
		return hand.size();
	}
	
	/**
	 * Obtenir la taille de la pile bataille du joueur
	 * @return Renvoie la taille
	 */

	public int battleSize() {
		return battlePile.size();
	}

	/*
	 * --------------- TO STRING -----------------------
	 */
	
	/**
	 * Afficher la main du joueur
	 * @return Renvoie la main du joueur
	 */

	public String showHand() {
		return hand.toString();
	}
	
	/**
	 * Afficher la pile distance du joueur
	 * @return Renvoie la pile
	 */

	public String showDistance() {
		return distancePile.toString();
	}
	
	/**
	 * Afficher la pile bataille du joueur
	 * @return Renvoie la pile
	 */

	public String showBattle() {
		return battlePile.toString();
	}

	/*
	 * --------------- GETTERS AND SETTERS -----------------------
	 */
	
	/**
	 * Supprimer la derniere carte de la pile bataille
	 */

	public void deleteLastCardFromBattle() {
		this.battlePile.stack.remove(this.battleSize() - 1);
	}
	
	/**
	 * Supprimer la derniere carte de la pile vitesse
	 */
	
	public void deleteLastCardFromSpeed() {
		this.speedPile.stack.remove(this.battleSize() - 1);
	}
	
	/**
	 * Obtenir la derniere carte de la pile bataille
	 * @return Carte de la pile
	 */
	
	public Card getLastCardFromBattle() {
		Card c = null;
		if (!battlePile.isEmpty())
			c = battlePile.lastElement();
		return c;
	}
	
	/**
	 * Obtenir la derniere carte de la pile vitesse
	 * @return Carte de la pile
	 */

	public Card getLastCardFromSpeed() {
		Card c = null;
		if (!speedPile.isEmpty())
			c = speedPile.lastElement();
		return c;
	}
	
	
	//---selected card---
	
	/**
	 * Definir la carte selectionnee par le joueur d'apres un index
	 * @param cardIndex Index de la carte
	 * @return Renvoie la carte a l'index precise
	 */
	public Card setSelectedCard(int cardIndex) {
		this.selectedCard = this.hand.get(cardIndex);
		return selectedCard;
	}
	
	/**
	 * Definir la carte selectionnee par le joueur d'apres la carte
	 * @param c Carte
	 * @return Renvoie la carte
	 */
	
	public Card setSelectedCard(Card c) {
		this.selectedCard = c;
		return selectedCard;
	}
	
	/**
	 * Obtenir la carte selectionnee par le jouru
	 * @return Renvoie la carte
	 */

	public Card getSelectedCard() {
		return selectedCard;
	}


	/**
	 * Obtenir le nom du joueur
	 * @return Renvoie le nom du joueur
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Definir le nom du joueur
	 * @param name Nom du joueur
	 */

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Obtenir la main du joueur
	 * @return Renvoie la main du joueur
	 */

	public Hand getHand() {
		return hand;
	}
	
	/**
	 * Obtenir le kilometrage total du joueur
	 * @return Renvoie le kilometrage
	 */

	public int getTotalMilage() {
		return distancePile.getTotalMilage();
	}
	
	/**
	 * Interroger si le joueur possède une botte DrivingAce
	 * @return Vrai si possede la botte, faux dans le cas contraire
	 */
	
	public boolean isDrivingAce() {
		return drivingAce;
	}

	/**
	 * Definir un joueur comme possedant une botte DrivingAce
	 * @param _drivingAce Booleen pour definir la presence de la botte
	 */
	
	public void setDrivingAce(boolean _drivingAce) {
		drivingAce = _drivingAce;
	}
	
	/**
	 * Interroger si le joueur possède une botte PunctureProof
	 * @return Vrai si possede la botte, faux dans le cas contraire
	 */

	public boolean isPunctureProof() {
		return punctureProof;
	}
	
	/**
	 * Definir un joueur comme possedant une botte PunctureProof
	 * @param punctureProof Booleen pour definir la presence de la botte
	 */

	public void setPunctureProof(boolean puntureProof) {
		punctureProof = puntureProof;
	}
	
	/**
	 * Interroger si le joueur possède une botte RightOfWay
	 * @return Vrai si possede la botte, faux dans le cas contraire
	 */

	public boolean isRightOfWay() {
		return rightOfWay;
	}
	
	/**
	 * Definir un joueur comme possedant une botte RightOfWay
	 * @param _rightOfWay Booleen pour definir la presence de la botte
	 */

	public void setRightOfWay(boolean _rightOfWay) {
		rightOfWay = _rightOfWay;
	}
	
	/**
	 * Interroger si le joueur possède une botte ExtraTank
	 * @return Vrai si possede la botte, faux dans le cas contraire
	 */

	public boolean isExtraTank() {
		return extraTank;
	}
	
	/**
	 * Definir un joueur comme possedant une botte ExtraTank
	 * @param _extraTank Booleen pour definir la presence de la botte
	 */

	public void setExtraTank(boolean _extraTank) {
		extraTank = _extraTank;
	}
	
	/**
	 * Interroger si le joueur est actif
	 * @return Vrai si le joueur est actif, faux dans le cas contraire
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Definir un joueur comme etant actif et notifie ses observers
	 * @param active Vrai si actif, faux dans le cas contraire
	 */

	public void setActive(boolean active) {
		setChanged();
		notifyObservers(1);
		this.active = active;
	}
}
