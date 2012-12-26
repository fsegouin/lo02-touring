package fr.lo02.model;
import java.util.Scanner;

public class Game {
	
	private int nbPlayer; //nbPlayer = nbComputerPlayer + nbHumanPlayer
	private int nbHumanPlayer;
	private int nbComputerPlayer;
	private String namePlayer[] = new String[7] ;

	/**
	 * Constructeur par default
	 */
	public Game () {
	}
	
	public Match startMatch() {
		Match match = Match.getInstance();
		Match.getInstance().setNamePlayer(namePlayer);
		Match.getInstance().setNbComputerPlayer(nbComputerPlayer);
		Match.getInstance().setNbHumanPlayer(nbHumanPlayer);
		Match.getInstance().initMatch();
		return match;
	}
		
	/**
	 * Get the number of player
	 * @return the number of player (human + computer)
	 */
	public int getNbPlayer() {
		return nbComputerPlayer+nbHumanPlayer;
	}


	public int getNbHumanPlayer() {
		return nbHumanPlayer;
	}

	public void setNbHumanPlayer(int nbHumanPlayer) {
		this.nbHumanPlayer = nbHumanPlayer;
	}

	public int getNbComputerPlayer() {
		return nbComputerPlayer;
	}

	public void setNbComputerPlayer(int nbComputerPlayer) {
		this.nbComputerPlayer = nbComputerPlayer;
	}

	public String[] getNamePlayer() {
		return namePlayer;
	}

	/*
	 * Set the namePlayer of a specific player
	 */
	public void setNamePlayerNumber(int number,String name) {
		this.namePlayer[number] = name;
	}

}
