package fr.lo02.model;

import fr.lo02.model.stack.Hand;

public class Player {
	
	private String name;
	private int color;
	
	Hand hand = new Hand();
	
	public Player(String name, int color) {
		this.name = name;
		this.color = color;
	}
	
	public void pickCard(CardList source){
		this.hand.add(source.topPick());
	}

}
