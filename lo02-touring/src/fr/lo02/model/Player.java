package fr.lo02.model;

import fr.lo02.model.card.Card;
import fr.lo02.model.stack.DistancePile;
import fr.lo02.model.stack.Hand;

public class Player {

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

	protected Card selectedCard;

	public Player(String name, int color){ 
		this.name = name;
		this.color = color; // NOT USED FOR NOW
	}

	/**
	 * Pioche une carte(la supprime) depuis le stack en parametre puis l'ajoute
	 * dans la main du joueur
	 * 
	 * @param source
	 *            Pioche depuis ce stack
	 * @return La carte qui a �t� pioch�
	 */
	public Card pickCard(CardList source) {
		Card c = source.topPick();
		this.hand.add(c);
		return c;
	}
	
	public void coupFourre() {
		System.out.println("--- ! COUP FOURRE ! ---");
		
		// Etape 1 : On defausse l'attaque //
		Match.getInstance().addToDiscardStack(this.getLastCardFromBattle());
		this.deleteLastCardFromBattle();
		
		// Etape 2 : Le joueur pioche une nouvelle carte dans la pioche ou la defausse (pas encore OP) //
		this.pickCard(Match.getInstance().getGameStack()); // Pioche uniquement dans la pioche
		
		// Etape 3 : Le joueur avance de 300km //
		this.addMilage(300);
	}

	public void addToDistance(Card aCard) {
		this.distancePile.toStack(aCard);
	}

	public void addToSpeed(Card aCard) {
		this.speedPile.toStack(aCard);
	}

	public void addToBattle(Card aCard) {
		this.battlePile.toStack(aCard);
	}
	
	public void addToSafetyArea(Card aCard) {
		this.safetyArea.toStack(aCard);
	}

	public void removeFromHand(Card aCard) {
		this.hand.removeFromHand(aCard);
	}

	public void addMilage(int km) {
		this.distancePile.addToTotalMilage(km);
	}

	public boolean kmCheck() {
		if (distancePile.getTotalMilage() >= WINNER_DISTANCE)
			return false;
		else
			return true;
	}

	/*
	 * --------------- SIZE -----------------------
	 */

	public int handSize() {
		return hand.size();
	}

	public int battleSize() {
		return battlePile.size();
	}

	/*
	 * --------------- TO STRING -----------------------
	 */

	public String showHand() {
		return hand.toString();
	}

	public String showDistance() {
		return distancePile.toString();
	}

	public String showBattle() {
		return battlePile.toString();
	}

	/*
	 * --------------- GETTERS AND SETTERS -----------------------
	 */

	public void deleteLastCardFromBattle() {
		this.battlePile.stack.remove(this.battleSize() - 1);
	}
	
	public Card getLastCardFromBattle() {
		Card c = null;
		if (!battlePile.isEmpty())
			c = battlePile.lastElement();
		return c;
	}

	public Card getLastCardFromSpeed() {
		Card c = null;
		if (!speedPile.isEmpty())
			c = speedPile.lastElement();
		return c;
	}
	
	
	//---selected card---
	public Card setSelectedCard(int cardIndex) {
		this.selectedCard = this.hand.get(cardIndex);
		;
		return selectedCard;
	}

	public Card getSelectedCard() {
		return selectedCard;
	}


	//---  ---
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hand getHand() {
		return hand;
	}

	public int getTotalMilage() {
		return distancePile.getTotalMilage();
	}
	
	public boolean isDrivingAce() {
		return drivingAce;
	}

	public void setDrivingAce(boolean _drivingAce) {
		drivingAce = _drivingAce;
	}

	public boolean isPunctureProof() {
		return punctureProof;
	}

	public void setPunctureProof(boolean puntureProof) {
		punctureProof = puntureProof;
	}

	public boolean isRightOfWay() {
		return rightOfWay;
	}

	public void setRightOfWay(boolean _rightOfWay) {
		rightOfWay = _rightOfWay;
	}

	public boolean isExtraTank() {
		return extraTank;
	}

	public void setExtraTank(boolean _extraTank) {
		extraTank = _extraTank;
	}
}
