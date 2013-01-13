package fr.lo02.model;

import java.util.Collections;
import java.util.Stack;

import fr.lo02.model.card.Card;

// Generic stack class

public class CardList { // On peut eventuellement extends depuis Stack

	protected Stack<Card> stack;
	
	/**
	 * Constructeur de CardList
	 */
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
	 * Supprime la carte situee en haut de la pile et la retourne
	 * @return Retourne la carte en haut de la pile
	 */
	public Card topPick() {
		return stack.pop();
	}
	
	/**
	 * Ajoute une carte en haut de la pile
	 * @param aCard Carte a ajouter
	 */
	public void toStack(Card aCard) {
		stack.push(aCard);
	}
	
	/**
	 * Test si la pile est vide
	 * @return Vrai si la pile est vide, faux dans le cas contraire
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Renvoie la derniere carte de la pile
	 * @return Derniere carte de la pile, soit la carte situee a l'index size() - 1
	 */
	public Card lastElement() {
		return stack.lastElement();
	}
	
	/**
	 * Ajoute une carte a la CardList
	 * @param c Carte a a jouter
	 */
	public void addCard(Card c){
		this.stack.add(c);
	}
	
	/**
	 * Permet de piocher a un index precis
	 * @param index Donne l'index ou piocher
	 * @return Renvoie la carte situee a cet index
	 */
	
	public Card pickAtIndex(int index) {
		return stack.get(index);
	}
	
	/**
	 * Permet d'obtenir la taille de la pile de cartes
	 * @return Retourne la taille de la pile
	 */
	
	public int size() {
		return stack.size();
	}

	/**
	 * Permet de lister l'ensemble des cartes situee dans la pile
	 */
	
	public String toString() {
		return "CardList [stack=" + stack + "]";
	}
	
	/**
	 * Obtenir le dernier element de la pile
	 * @return Renvoie le dernier element de la pile de carte si elle n'est pas vide
	 */

	public Card getLastElement() {
		Card c = null;
		if (!stack.isEmpty())
			c=stack.lastElement();
		return c;
	}


}
