package fr.lo02.model.card;

<<<<<<< HEAD
import fr.lo02.model.stack.Hand;
import fr.lo02.model.stack.PlayerStack;

public class Card {
=======
import fr.lo02.model.CardList;

public abstract class Card {
>>>>>>> branch 'master' of https://github.com/fsegouin/lo02-touring.git

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
<<<<<<< HEAD
	public boolean playThisCard(Hand from, PlayerStack to) {
		return true;
=======
	public void checkValidMove(Card source, CardList destination) {
		
>>>>>>> branch 'master' of https://github.com/fsegouin/lo02-touring.git
	}

}
