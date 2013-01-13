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
	 * Obtenir le nombre de joueurs
	 * @return Renvoie le nombre de joueurs total (humains + ordinateur)
	 */
	public int getNbPlayer() {
		return nbComputerPlayer+nbHumanPlayer;
	}

	/**
	 * Obtenir le nombre de joueurs humains
	 * @return Renvoie le nombre de joueurs humains
	 */
	public int getNbHumanPlayer() {
		return nbHumanPlayer;
	}

	/**
	 * Definir le nombre de joueurs humains
	 * @param nbHumanPlayer Nombre de joueurs humains
	 */
	public void setNbHumanPlayer(int nbHumanPlayer) {
		this.nbHumanPlayer = nbHumanPlayer;
	}

	/**
	 * Obtenir le nombre de joueurs ordinateur
	 * @return Renvoie le nombre de joueurs ordinateur
	 */
	public int getNbComputerPlayer() {
		return nbComputerPlayer;
	}

	/**
	 * Definir le nombre de joueurs ordinateur
	 * @param nbComputerPlayer Nombre de joueur ordinateur
	 */
	public void setNbComputerPlayer(int nbComputerPlayer) {
		this.nbComputerPlayer = nbComputerPlayer;
	}

	/**
	 * Obtenir le nom d'un joueur
	 * @return Renvoie le nom du joueur
	 */
	public String[] getNamePlayer() {
		return namePlayer;
	}

	/**
	 * Definir le tableau des noms des joueurs
	 * @param _namePlayer Tableau contenant les noms des joueurs
	 */
	public void setNamePlayerTab(String[] _namePlayer){
		this.namePlayer = _namePlayer;
	}
	
	/*
	 * Definir le nom d'un joueur specifique
	 */
	public void setNamePlayerNumber(int number,String name) {
		this.namePlayer[number] = name;
	}

}
