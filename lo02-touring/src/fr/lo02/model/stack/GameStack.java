package fr.lo02.model.stack;

import java.util.Collections;

import fr.lo02.model.CardList;

public class GameStack extends CardList {

	public void shuffleCards() {
		Collections.shuffle(stack);
	}

}
