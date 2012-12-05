package fr.lo02.model;

import fr.lo02.model.stack.Hand;

public class Player {

	private String name;
	private int color;
<<<<<<< HEAD

	PlayerStack myStack = new PlayerStack();

=======
	
	Hand hand = new Hand();
	
>>>>>>> branch 'master' of https://github.com/fsegouin/lo02-touring.git
	public Player(String name, int color) {
		this.name = name;
		this.color = color;
	}
<<<<<<< HEAD

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
=======
	
	public void pickCard(CardList source){
		this.hand.add(source.topPick());
>>>>>>> branch 'master' of https://github.com/fsegouin/lo02-touring.git
	}

}
