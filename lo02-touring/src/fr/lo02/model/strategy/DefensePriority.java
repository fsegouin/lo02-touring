package fr.lo02.model.strategy;

import java.util.Iterator;

import fr.lo02.model.CardList;
import fr.lo02.model.ComputerPlayer;
import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;

public class DefensePriority extends RegularStrategy implements Strategy{

	private CardList possibleCard = new CardList();
	private Player targetedPlayer = null;
	private boolean played = false;

	public void strategyPlay(Match match, ComputerPlayer computerPlayer) {

		System.out.println("");
		System.out.println("--- Tour de " + computerPlayer.getName() + " ---");
		
		computerPlayer.pickCard(match.getGameStack());
		
		played = playPriority(match, computerPlayer);
		
		playRegular(match, computerPlayer, played);

	}
	@Override
	public boolean playPriority(Match match, ComputerPlayer computerPlayer) {
		// TODO Auto-generated method stub
		boolean played = false;
		// Verification si une carte attaque est presente dans la main
		for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c.isSafetyCard())
				possibleCard.addCard(c);
		}
		if(!(possibleCard.isEmpty())) { // On verifie si l'IA peut jouer une carte attaque
			possibleCard.shuffleCards();
			computerPlayer.setSelectedCard(possibleCard.topPick());
			targetedPlayer = getATargetPlayer(match, computerPlayer);
			if(targetedPlayer != null) {
				System.out.println("L'IA a joue la carte " + computerPlayer.getSelectedCard().toString() + " sur " + targetedPlayer.getName());
				computerPlayer.getSelectedCard().playThisCard(computerPlayer, targetedPlayer);
				played = true;
			}
		}
		return played;
	}

}
