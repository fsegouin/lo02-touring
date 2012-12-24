package fr.lo02.model;

import java.util.Collections;
import java.util.Stack;

import fr.lo02.model.card.Card;

// Generic stack class

public class CardList { // On peut eventuellement extends depuis Stack

	protected Stack<Card> stack;
	
	public CardList() {
		super();
		this.stack = new Stack<Card>();
	}

	/**
	 * Melange le paquet de carte
	 */
	public void shuffleCards() {
		Collections.shuffle(stack);
	}
	
	/**
	 * Removes the object at the top of this stack and returns that object as the value of this function.
	 * @return The object at the top of this stack (the last item of the Vector object).
	 */
	public Card topPick() {
		return stack.pop();
	}
	
	/**
	 * Pushes a card onto the top of this stack. This has exactly the same effect as: addElement(item)
	 * @param aCard the Card to be pushed onto this stack.
	 */
	public void toStack(Card aCard) {
		stack.push(aCard);
	}
	
	/**
	 * Tests if this stack is empty.
	 * @return true if and only if this stack contains no items; false otherwise.
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Returns the last card of the stack.
	 * @return the last card of the stack, i.e., the card at index size() - 1.
	 */
	public Card lastElement() {
		return stack.lastElement();
	}
	
	
	public Card pickAtIndex(int index) {
		return stack.get(index);
	}
	
	public int size() {
		return stack.size();
	}

	@Override
	public String toString() {
		return "CardList [stack=" + stack + "]";
	}



}
