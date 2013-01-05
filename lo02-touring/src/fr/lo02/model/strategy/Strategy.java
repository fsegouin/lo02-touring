package fr.lo02.model.strategy;

import fr.lo02.model.ComputerPlayer;
import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Distance;
import fr.lo02.model.card.HazardCards.SpeedLimit;
import fr.lo02.model.card.remedyCards.EndOfLimit;
import fr.lo02.model.card.remedyCards.Gasoline;
import fr.lo02.model.card.remedyCards.GoRoll;
import fr.lo02.model.card.remedyCards.Repairs;
import fr.lo02.model.card.remedyCards.SpareTire;

public interface Strategy {
	
	/**
	 * Methode appelee par toutes les IA
	 * @param match
	 * @param computerPlayer
	 * @return Retourne un boolean pour indiquer si la carte a pu etre jouee
	 */
	public boolean playPriority(Match match, ComputerPlayer computerPlayer);

	/**
	 * Methode qui joue en priorite les cartes d'apres la strategie adoptee par l'IA
	 * @param match
	 * @param computerPlayer
	 */
	public void strategyPlay(Match match, ComputerPlayer computerPlayer);
	
}
