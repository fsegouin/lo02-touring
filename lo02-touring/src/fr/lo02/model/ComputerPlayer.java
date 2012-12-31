package fr.lo02.model;

import fr.lo02.model.strategy.Strategy;

public class ComputerPlayer extends Player{
	
	private Strategy strategy;

	/**
     * Constructeur par default recevant un objet "Strategie" en paramètre
     * @param Strategy
     */
	public ComputerPlayer(String name, int color,Strategy _strategy) {
		super(name, color);
		this.strategy = _strategy;
	}

	/**
	 * Renvoi la strategy d'un ordinateur
	 * @param strategy
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * Permet de changer la strategy d'un ordinateur
	 * @param strategy
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	
	/**
     * L'odinateur joue via la strategy
	 * @param match 
     */
    public void play(Match match) {
        strategy.strategyPlay(match, this);
    }
}
