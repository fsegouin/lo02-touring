package fr.lo02.model;

import java.util.Stack;

import fr.lo02.model.card.Card;
import fr.lo02.model.stack.DistancePile;
import fr.lo02.model.stack.Hand;

public class Player {

	private final int WINNER_DISTANCE = 1000;

	private String name;
	private int color;
	protected Hand hand = new Hand(); // Main du joueur
	private DistancePile distancePile = new DistancePile(); // Pile des cartes
															// distance
	// private CardList discardPile = new CardList(); // Pile des cartes
	// distance
	private CardList battlePile = new CardList(); // Pile des cartes distance
	protected Card selectedCard;

	public Player(String name, int color) {
		this.name = name;
		this.color = color; // NOT USED FOR NOW
	}

	/**
	 * Pioche une carte depuis le stack en parametre puis l'ajoute dans la main du joueur
	 * @param source Pioche depuis ce stack
	 * @return La carte qui a été pioché
	 */
	public Card pickCard(CardList source) {
		Card c = source.topPick();
		this.hand.add(c);
		return c;
	}

	public void addToDistance(Card aCard) {
		this.distancePile.toStack(aCard);
	}

	/*
	 * public void addToDiscard(Card aCard) { this.discardPile.stack.add(aCard);
	 * }
	 */

	public void addToBattle(Card aCard) {
		this.battlePile.toStack(aCard);
	}

	public Card getLastCardFromBattle() {
		Card c = null;
		if (!battlePile.isEmpty())
			c = battlePile.lastElement();
		return c;
	}

	public void removeFromHand(Card aCard) {
		this.hand.remove(aCard);
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
	
	public Card setSelectedCard(int cardIndex) {
		this.selectedCard = this.hand.get(cardIndex);
		;
		return selectedCard;
	}

	public Card getSelectedCard() {
		return selectedCard;
	}

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
}
