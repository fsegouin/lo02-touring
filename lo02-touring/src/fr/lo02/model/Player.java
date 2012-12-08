package fr.lo02.model;

import fr.lo02.model.card.Card;
import fr.lo02.model.stack.DistancePile;
import fr.lo02.model.stack.Hand;

public class Player {

	private final int WINNER_DISTANCE = 1000;
	
	private String name;
	private int color;
	
	protected Hand hand = new Hand(); // Main du joueur
	public DistancePile distancePile = new DistancePile(); // Pile des cartes distance
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
	
	public Card selectedCard(int cardIndex) {
		selectedCard = this.hand.get(cardIndex);
		return selectedCard;
	}
	
	public String showCards() {
		return hand.toString();
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
