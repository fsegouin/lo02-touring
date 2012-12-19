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
	private DistancePile distancePile = new DistancePile(); // Pile des cartes distance
	// private CardList discardPile = new CardList(); // Pile des cartes distance
	private CardList battlePile = new CardList(); // Pile des cartes distance
	protected Card selectedCard;

	public Player(String name, int color) {
		this.name = name;
		this.color = color; // NOT USED FOR NOW
	}
	
	/*
	 * Add pick a card from "source" and add this card in your hand
	 * Then your return the card picked
	 */
	public Card pickCard(CardList source){
		Card c = source.topPick();
		this.hand.add(c);
		return c;
	}
	
	public void canBeTargetedBy(Card aCard, Player activePlayer) {
		
	}
	
	public void addToDistance(Card aCard) {
		this.distancePile.stack.add(aCard);
	}

/*	public void addToDiscard(Card aCard) {
		this.discardPile.stack.add(aCard);
	}*/

	public void addToBattle(Card aCard) {
		this.battlePile.stack.add(aCard);
	}
	
	public Card selectedCard(int cardIndex) {
		selectedCard = this.hand.get(cardIndex);
		return selectedCard;
	}
	
	public String showHand() {
		return hand.toString();
	}

	public String showDistance() {
		return distancePile.toString();
	}

	public String showBattle() {
		return battlePile.toString();
	}
	
	public Card getLastCardFromBattle() {
		return battlePile.stack.lastElement();
	}
	
	public void removeFromHand(Card aCard) {
		this.hand.removeFromHand(aCard);
	}
	
	public int handSize() {
		return hand.size();
	}

	public void addMilage(int km) {
		this.distancePile.addToTotalMilage(km);
	}
	
	public int getTotalMilage() {
		return distancePile.getTotalMilage();
	}
	
	public boolean kmCheck() {
		if (distancePile.getTotalMilage() >= WINNER_DISTANCE)
			return false;
		else
			return true;
	}

	/*
	 *  ---------------  GETTERS AND SETTERS  -----------------------
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
