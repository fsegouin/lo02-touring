package fr.lo02.model;

import java.util.Stack;

import fr.lo02.model.card.Card;

// Generic stack class

public class CardList { // On peut eventuellement extends depuis Stack

	protected Stack<Card> stack;
	
	public CardList() {
		super();
		this.stack = new Stack<Card>();
	}

	public Card topPick() {
		return stack.pop();
	}

	public void toStack(Card aCard) {
		stack.push(aCard);
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public Card pickAtIndex(int index) {
		return stack.get(index);
	}
	
	public int getCardCounter() {
		return stack.size();
	}

	@Override
	public String toString() {
		return "CardList [stack=" + stack + "]";
	}

}
