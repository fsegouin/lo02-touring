
package fr.lo02.controller;

import java.util.Scanner;

import fr.lo02.model.Match;


public class Game {
	
	private int nbPlayer; //nbPlayer = nbComputerPlayer + nbHumanPlayer
	private int nbHumanPlayer;
	private int nbComputerPlayer;
	private String namePlayer[] = new String[7] ;

	
	public Game () {
		
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entrer un nombre de joueur humain :");
		game.setNbHumanPlayer(sc.nextInt());
		System.out.println(game.getNbHumanPlayer());
		
		System.out.println("Entrer un nombre de joueur ordinateur :");
		game.setNbComputerPlayer(sc.nextInt());
		System.out.println(game.getNbComputerPlayer());
		
		//On vide la ligne avant d'en lire d'autres car on a utilisé sc.nextInt() avant
	    sc.nextLine();
		
		//Add the name of each HumanPlayer
		for (int i = 0; i < game.getNbHumanPlayer(); i++) {
			System.out.println("Nom du joueur :" + (i+1));
			game.setNamePlayerNumber(i, sc.nextLine());
		}
		game.startMatch();
	}

	public void startMatch() {
		Match match = new Match(this.nbComputerPlayer, this.nbHumanPlayer, this.namePlayer);
					
	}
	

	/*
	 * nbPlayer = nbComputerPlayer + nbHumanPlayer
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
