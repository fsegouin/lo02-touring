package fr.lo02.model.strategy;

import java.util.ArrayList;
import java.util.Iterator;

import fr.lo02.model.CardList;
import fr.lo02.model.ComputerPlayer;
import fr.lo02.model.Match;
import fr.lo02.model.card.Card;
import fr.lo02.model.exception.SelectedCardNotDefinedException;

public class AttackPriority implements Strategy {

	CardList possibleCard = new CardList();

	public void strategyPlay(Match match, ComputerPlayer computerPlayer) {
		
		//Carte attaque presente dans sa main
		for (Iterator iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c.isRemedyCard())
				possibleCard.addCard(c);
		}
		possibleCard.shuffleCards();
		
		boolean play = false;
		while(play){
			computerPlayer.setSelectedCard(possibleCard.topPick());
			try {
				match.checkCardPlayable(computerPlayer);
			} catch (SelectedCardNotDefinedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
