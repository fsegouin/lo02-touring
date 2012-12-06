package fr.lo02.model;

import fr.lo02.model.stack.DistancePile;
import fr.lo02.model.stack.Hand;

public class Player {

	private String name;
	private int color;
	
	public Hand hand = new Hand(); // Main du joueur
	public DistancePile distancePile = new DistancePile(); // Pile des cartes distance
	public CardList discardPile = new CardList(); // Pile de la defausse
	
	public Player(String name, int color) {
		this.name = name;
		this.color = color; // NOT USED FOR NOW
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void pickCard(CardList source){
		this.hand.add(source.topPick());
	}
	
	public String showCards() {
		return hand.toString();
	}
	
	public boolean kmCheck() {
		if (distancePile.getTotalMilage() >= 1000)
			return false;
		else
			return true;
	}

}
