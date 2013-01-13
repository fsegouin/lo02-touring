package fr.lo02.model.strategy;

import java.util.HashSet;
import java.util.Iterator;

import fr.lo02.model.CardList;
import fr.lo02.model.ComputerPlayer;
import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.card.Distance;
import fr.lo02.model.card.HazardCards.Accident;
import fr.lo02.model.card.HazardCards.FlatTire;
import fr.lo02.model.card.HazardCards.OutOfGas;
import fr.lo02.model.card.HazardCards.SpeedLimit;
import fr.lo02.model.card.remedyCards.EndOfLimit;
import fr.lo02.model.card.remedyCards.Gasoline;
import fr.lo02.model.card.remedyCards.GoRoll;
import fr.lo02.model.card.remedyCards.Repairs;
import fr.lo02.model.card.remedyCards.SpareTire;
import fr.lo02.model.exception.SelectedCardNotDefinedException;

public class RegularStrategy {
	
	/**
	 * Obtenir un joueur a viser aleatoirement parmis les joueurs compatibles
	 * @param match
	 * @param computerPlayer
	 * @return Renvoie le joueur vise
	 */
	public Player getATargetPlayer(Match match, ComputerPlayer computerPlayer) {
		// TODO Auto-generated method stub
		HashSet<Player> lp = null;
		Player targetedPlayer = null;
		try {
			lp = match.checkCardPlayable(computerPlayer);
		} catch (SelectedCardNotDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(lp != null) {
			int i = 0;
			int random = (int)(Math.random() * (lp.size()+1));
			for (Iterator<Player> iterator = lp.iterator(); iterator.hasNext();) {
				Player aPlayer = (Player) iterator.next();
				if(i == random)
					targetedPlayer = aPlayer;
				i++;
			}
		}
		return targetedPlayer;
	}

	/**
	 * Jouer les cartes de priorite inferieures
	 * @param match
	 * @param computerPlayer
	 * @param played
	 */
	public void playRegular(Match match, ComputerPlayer computerPlayer, boolean played) {
		
		CardList possibleCard = new CardList();
		
		// DEBUG //
		if(computerPlayer.getLastCardFromBattle() != null)
			System.out.println("Status de l'IA : " + computerPlayer.getLastCardFromBattle().toString());
		// DEBUG //
		
		if (!played) {
			for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
				Card c = (Card) iterator.next();
				if (c.isSafetyCard())
					possibleCard.addCard(c);
			}
			if(!(possibleCard.isEmpty())) { // On verifie si l'IA peut jouer une carte attaque
				possibleCard.shuffleCards();
				computerPlayer.setSelectedCard(possibleCard.topPick());
				computerPlayer.getSelectedCard().playThisCard(computerPlayer, computerPlayer);			}
		}
		if (computerPlayer.getLastCardFromBattle() instanceof GoRoll && !played) // L'IA tente de jouer une carte distance
			played = playCard(computerPlayer, new Distance(0));
		if (computerPlayer.getLastCardFromBattle() instanceof SpeedLimit && !played)
			played = playCard(computerPlayer, new EndOfLimit());
		if (computerPlayer.getLastCardFromBattle() instanceof OutOfGas && !played)
			played = playCard(computerPlayer, new Gasoline());
		if (computerPlayer.getLastCardFromBattle() instanceof FlatTire && !played)
			played = playCard(computerPlayer, new SpareTire());
		if (computerPlayer.getLastCardFromBattle() instanceof Accident && !played)
			played = playCard(computerPlayer, new Repairs());
		if (!played)
			played = playCard(computerPlayer, new GoRoll());
		if (!played) { // L'IA doit se defausser si aucune autre option n'est possible
			computerPlayer.setSelectedCard(0);
			System.out.println("L'IA se defausse d'une carte : " + computerPlayer.getSelectedCard().toString());
			match.addToDiscardStack(computerPlayer.getSelectedCard());
			computerPlayer.getSelectedCard().throwThisCard(computerPlayer);
			played = true;
		}
	}
	
	/**
	 * Jouer une carte de type Distance dans la main de l'IA
	 * @param computerPlayer
	 * @param aCard
	 * @return Renvoie un boolean pour indiquer si la carte a pu etre jouee
	 */
	public boolean playCard(ComputerPlayer computerPlayer, Distance aCard) {
		// TODO Auto-generated method stub
		boolean played = false;
		CardList possibleCard = new CardList();
		for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c instanceof Distance)
				possibleCard.addCard(c);
		}
		if(possibleCard.size() != 0) {
			possibleCard.shuffleCards();
			computerPlayer.setSelectedCard(possibleCard.topPick());
			System.out.println("L'IA a joue la carte " + computerPlayer.getSelectedCard().toString());
			computerPlayer.getSelectedCard().playThisCard(computerPlayer, computerPlayer);
			played = true;
		}
		return played;
	}
	
	/**
	 * Jouer une carte de type GoRoll dans la main de l'IA
	 * @param computerPlayer
	 * @param aCard
	 * @return Renvoie un boolean pour indiquer si la carte a pu etre jouee
	 */
	public boolean playCard(ComputerPlayer computerPlayer, GoRoll aCard) {
		// TODO Auto-generated method stub
		boolean played = false;
		CardList possibleCard = new CardList();
		for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c instanceof GoRoll)
				possibleCard.addCard(c);
		}
		if(possibleCard.size() != 0) {
			possibleCard.shuffleCards();
			computerPlayer.setSelectedCard(possibleCard.topPick());
			System.out.println("L'IA a joue la carte " + computerPlayer.getSelectedCard().toString());
			computerPlayer.getSelectedCard().playThisCard(computerPlayer, computerPlayer);
			played = true;
		}
		return played;
	}
	
	/**
	 * Jouer une carte de type EndOfLimit dans la main de l'IA
	 * @param computerPlayer
	 * @param aCard
	 * @return Renvoie un boolean pour indiquer si la carte a pu etre jouee
	 */
	public boolean playCard(ComputerPlayer computerPlayer, EndOfLimit aCard) {
		// TODO Auto-generated method stub
		boolean played = false;
		CardList possibleCard = new CardList();
		for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c instanceof EndOfLimit)
				possibleCard.addCard(c);
		}
		if(possibleCard.size() != 0) {
			possibleCard.shuffleCards();
			computerPlayer.setSelectedCard(possibleCard.topPick());
			System.out.println("L'IA a joue la carte " + computerPlayer.getSelectedCard().toString());
			computerPlayer.getSelectedCard().playThisCard(computerPlayer, computerPlayer);
			played = true;
		}
		return played;
	}
	
	/**
	 * Jouer une carte de type Gasoline dans la main de l'IA
	 * @param computerPlayer
	 * @param aCard
	 * @return Renvoie un boolean pour indiquer si la carte a pu etre jouee
	 */
	public boolean playCard(ComputerPlayer computerPlayer, Gasoline aCard) {
		// TODO Auto-generated method stub
		boolean played = false;
		CardList possibleCard = new CardList();
		for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c instanceof Gasoline)
				possibleCard.addCard(c);
		}
		if(possibleCard.size() != 0) {
			possibleCard.shuffleCards();
			computerPlayer.setSelectedCard(possibleCard.topPick());
			System.out.println("L'IA a joue la carte " + computerPlayer.getSelectedCard().toString());
			computerPlayer.getSelectedCard().playThisCard(computerPlayer, computerPlayer);
			played = true;
		}
		return played;
	}
	
	/**
	 * Jouer une carte de type Repairs dans la main de l'IA
	 * @param computerPlayer
	 * @param aCard
	 * @return Renvoie un boolean pour indiquer si la carte a pu etre jouee
	 */
	public boolean playCard(ComputerPlayer computerPlayer, Repairs aCard) {
		// TODO Auto-generated method stub
		boolean played = false;
		CardList possibleCard = new CardList();
		for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c instanceof Repairs)
				possibleCard.addCard(c);
		}
		if(possibleCard.size() != 0) {
			possibleCard.shuffleCards();
			computerPlayer.setSelectedCard(possibleCard.topPick());
			System.out.println("L'IA a joue la carte " + computerPlayer.getSelectedCard().toString());
			computerPlayer.getSelectedCard().playThisCard(computerPlayer, computerPlayer);
			played = true;
		}
		return played;
	}
	
	/**
	 * Jouer une carte de type SpareTire dans la main de l'IA
	 * @param computerPlayer
	 * @param aCard
	 * @return Renvoie un boolean pour indiquer si la carte a pu etre jouee
	 */
	public boolean playCard(ComputerPlayer computerPlayer, SpareTire aCard) {
		// TODO Auto-generated method stub
		boolean played = false;
		CardList possibleCard = new CardList();
		for (Iterator<Card> iterator = computerPlayer.getHand().iterator(); iterator.hasNext();) {
			Card c = (Card) iterator.next();
			if (c instanceof SpareTire)
				possibleCard.addCard(c);
		}
		if(possibleCard.size() != 0) {
			possibleCard.shuffleCards();
			computerPlayer.setSelectedCard(possibleCard.topPick());
			System.out.println("L'IA a joue la carte " + computerPlayer.getSelectedCard().toString());
			computerPlayer.getSelectedCard().playThisCard(computerPlayer, computerPlayer);
			played = true;
		}
		return played;
	}

}
