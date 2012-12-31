package fr.lo02.model.strategy;

import fr.lo02.model.ComputerPlayer;
import fr.lo02.model.Match;

public interface Strategy {

	public void strategyPlay(Match match, ComputerPlayer computerPlayer);
	
}
