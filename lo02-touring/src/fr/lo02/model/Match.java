package fr.lo02.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import fr.lo02.model.card.Card;
import fr.lo02.model.card.Distance;
import fr.lo02.model.card.HazardCards.*;
import fr.lo02.model.card.SafetyCards.*;
import fr.lo02.model.card.remedyCards.*;
import fr.lo02.model.exception.NotValidCardOnBattleException;
import fr.lo02.model.exception.SelectedCardNotDefinedException;
import fr.lo02.model.strategy.AttackPriority;
import fr.lo02.model.strategy.DefensePriority;
import fr.lo02.model.strategy.SpeedPriority;
import fr.lo02.model.strategy.Strategy;

public class Match extends Observable {
	
	private static volatile Match instance = null;

	private CardList gameStack = new CardList();
	private CardList discardStack = new CardList();
	private int numActivePlayer = 0;
	public Player activePlayer = null;
	private LinkedList<Player> listPlayer = new LinkedList<Player>();
	private String[] namePlayer;
	private int nbHumanPlayer;
	private int nbComputerPlayer;
	//Joueur cibable = lp
	HashSet<Player> lp = new HashSet<Player>();
	
	private Match() {
		super();
	}
	
	/**
	 * Initiliase le match
	 */
	
	public void initMatch() {
		this.playerInit(nbComputerPlayer, nbHumanPlayer, namePlayer);
		this.gameStackInit();
		this.playerHandInit();
	}

	public void changed() {
		setChanged();
		notifyObservers(1);
	}
	
	/**
	 * Effectue le tour du prochain joueur
	 */
	public void next() {
		activePlayer = nextPlayer();
		changed();
		if(!this.testEndOfGame()) {
			if (activePlayer instanceof HumanPlayer) {
				activePlayer.pickCard(this.getGameStack());
				activePlayer.setActive(true);
			} 
			else if (activePlayer instanceof ComputerPlayer){
				((ComputerPlayer) activePlayer).play(this);
			}
		}
		else {
		}
	}
	
	/**
	 * Singleton de Match
	 * @return Renvoie l'instance de Match
	 */
	public final static Match getInstance() {
        if (Match.instance == null) {
           synchronized(Match.class) {
             if (Match.instance == null) {
               Match.instance = new Match();
             }
           }
        }
        return Match.instance;
    }

	/**
	 * Retourne une liste des joueurs qui peuvent etre ciblables
	 * @param activePlayer Joueur actif (celui qui lance l'attaque)
	 * @return lp Liste de joueurs ou les joueurs peuvent etre ciblables par cette attaque
	 */
	public HashSet<Player> checkCardPlayable(Player activePlayer) throws SelectedCardNotDefinedException {
		Player p = null;
		lp.clear();
		if (activePlayer.getSelectedCard() != null) {

			for (Iterator<Player> iterator = listPlayer.iterator(); iterator.hasNext();) {
				Player testTargetPlayer = (Player) iterator.next();
				// Test si la carte selectionne par le joueur actif est jouable
				// sur la liste des joueurs
				try {
					if (!activePlayer.equals(testTargetPlayer)) // empeche que l'active player soit un targetplayer pendant le parcours de listPlayer
					p = activePlayer.getSelectedCard().checkValidMove(activePlayer, testTargetPlayer);
				} catch (NotValidCardOnBattleException e) {
					//System.out.println(e.getMessage());
				}
				if (p != null) {
					lp.add(p);
					p = null;
				}
			}

		} else
			throw new SelectedCardNotDefinedException("Vous n'avez pas selectionne de carte a jouer.");
		
		setChanged();
		notifyObservers(2);
		return lp;
	}
	
	/**
	 * Retourne le player ou va etre joue la carte
	 * @param playerName Nom du joueur cible
	 * @param lp Liste des joueurs ciblable
	 * @return Le joueur cible
	 */
	public Player getPlayerByName(String playerName) {
		Player returnedPlayer = null;
		for (Iterator<Player> iterator = lp.iterator(); iterator.hasNext();) {
			Player aPlayer = (Player) iterator.next();
			if(aPlayer.getName().contentEquals(playerName))
				returnedPlayer = aPlayer;
		}
		return returnedPlayer;
	}
	
	/**
	 * Definir le prochain joueur
	 * @param nextPlayer Prochain joueur a jouer
	 */
	public void setNextPlayer(Player nextPlayer) {
		numActivePlayer = listPlayer.indexOf(nextPlayer);
	}
	
	/**
	 * Obtenir le prochain joueur d'apres la liste des joueurs
	 * @return Renvoie le prochain joueur
	 */
	public Player nextPlayer() {
		// Ex: pr 4 joueurs - listPlayer.size = 4 mais activePlayer = 3 (0,1,2,3)
		if (numActivePlayer > listPlayer.size()-1) {
			numActivePlayer = 0;
		}
		Player nextPlayer = listPlayer.get(numActivePlayer);
		numActivePlayer++;

		return nextPlayer;
	}

	/**
	 * Test si le joueur actif a atteint 1000km ou si la pioche est vide
	 * @return Vrai pour la fin de la partie, faux dans le cas contraire
	 */
	public boolean testEndOfGame(){
		if(listPlayer.get(numActivePlayer-1).kmCheck() || gameStack.isEmpty()) {
			System.out.println("Un joueur a depasse les 1000 Bornes ou la pioche est vide !");
			return true;
		}
		else
			return false;
	}

	/**
	 * Creer la liste des joueurs (humains et ordinateur)
	 * @param nbComputerPlayer Nombre de joueurs ordinateur
	 * @param nbHumanPlayer Nombre de joueurs humains
	 * @param namePlayer Noms des joueurs
	 */
	public void playerInit(int nbComputerPlayer, int nbHumanPlayer, String[] namePlayer) {

		for (int i = 0; i < nbHumanPlayer; i++) {
			HumanPlayer humanplayer = new HumanPlayer(namePlayer[i], i);
			this.listPlayer.add(humanplayer);
		}

		if(nbComputerPlayer > 0) {
			Strategy attackPriority = new AttackPriority();
			Strategy defensePriority = new DefensePriority(); // Non utilise mais implemente
			Strategy speedPriority = new SpeedPriority(); // Non utilise mais implemente
			for (int i = 0; i < nbComputerPlayer; i++) {
				ComputerPlayer computerPlayer = new ComputerPlayer("Computer" + i, 0, attackPriority);
				this.listPlayer.add(computerPlayer);
			}
		}
	}

	/**
	 * Permet de distribuer une main a chaque joueur
	 */
	
	public void playerHandInit() {
		for (Player p : listPlayer) {
			for (int i = 0; i < 4; i++) {
				p.hand.add(gameStack.topPick());
			}
		}
	}
	
	/**
	 * Initilisation de la pile de jeu avec toutes les cartes d'apres les regles officielles du jeu
	 */

	public void gameStackInit() { // Initialise la pioche de jeu principale
		for (int i = 0; i < 2; i++) { // 2 stop cards
			Stop aStop = new Stop();
			gameStack.toStack(aStop);
		}
		for (int i = 0; i < 5; i++) { // 2 roll cards
			GoRoll aGoRoll = new GoRoll();
			gameStack.toStack(aGoRoll);
		}

		for (int i = 0; i < 2; i++) {
			SpeedLimit aSpeedLimit = new SpeedLimit();
			gameStack.toStack(aSpeedLimit);
		}
		
		 
		for (int i = 0; i < 4; i++) {
			EndOfLimit anEndOfLimit = new EndOfLimit();
			gameStack.toStack(anEndOfLimit); 
		}
		  
		for (int i = 0; i < 2; i++) {
			OutOfGas anOutOfGas = new OutOfGas();
			gameStack.toStack(anOutOfGas); 
		}
		  
		for (int i = 0; i < 2; i++) {
			FlatTire aFlatTire = new FlatTire();
			gameStack.toStack(aFlatTire);
		}
		  
		for (int i = 0; i < 2; i++) {
			Accident anAccident = new Accident();
			gameStack.toStack(anAccident);
		}
		  
		for (int i = 0; i < 4; i++) {
			Gasoline aGasoline = new Gasoline();
			gameStack.toStack(aGasoline);
		}
		  
		for (int i = 0; i < 4; i++) {
			Repairs aRepairs = new Repairs();
			gameStack.toStack(aRepairs);
		}
		  
		for (int i = 0; i < 4; i++) {
			SpareTire aSpareTire = new SpareTire();
			gameStack.toStack(aSpareTire);
		}
		  
		 // 4 bottes du jeu
		  
		  RightOfWay aRightOfWay = new RightOfWay();
		  gameStack.toStack(aRightOfWay);
		  
		  DrivingAce aDrivingAce = new DrivingAce();
		  gameStack.toStack(aDrivingAce);
		  
		  ExtraTank anExtraTank = new ExtraTank();
		  gameStack.toStack(anExtraTank);
		  
		  PunctureProof aPunctureProof = new PunctureProof();
		  gameStack.toStack(aPunctureProof);
		 
		
		// Distance cards
		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(25);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(50);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 6; i++) {
			Distance aDistance = new Distance(75);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 9; i++) {
			Distance aDistance = new Distance(100);
			gameStack.toStack(aDistance);
		}

		for (int i = 0; i < 3; i++) {
			Distance aDistance = new Distance(200);
			gameStack.toStack(aDistance);
		}

		gameStack.shuffleCards(); // Shuffle the stack
	}
	
	/**
	 * Obtenir la pioche du jeu
	 * @return Renvoie la pioche
	 */

	public CardList getGameStack() {
		return gameStack;
	}
	
	/**
	 * Obtenir la defausse du jeu
	 * @return Renvoie la defausse
	 */

	public CardList getDiscardStack() {
		return discardStack;
	}
	
	/**
	 * Ajouter une carte a la defausse
	 * @param aCard Carte a ajouter
	 */
	
	public void addToDiscardStack(Card aCard) {
		discardStack.stack.add(aCard);
	}
	
	/**
	 * Obtenir le nom des joueurs
	 * @return Renvoie les noms
	 */
	
	public String[] getNamePlayer() {
		return namePlayer;
	}
	
	/**
	 * Definir le nom des joueurs
	 * @param namePlayer Nom des joueurs
	 */

	public void setNamePlayer(String[] namePlayer) {
		this.namePlayer = namePlayer;
	}
	
	/**
	 * Obtenir le nombre de joueurs humains
	 * @return Renvoie le nombre de joueurs
	 */

	public int getNbHumanPlayer() {
		return nbHumanPlayer;
	}
	
	/**
	 * Definir le nombre de joueurs humains
	 * @param nbHumanPlayer Nombre de joueurs
	 */

	public void setNbHumanPlayer(int nbHumanPlayer) {
		this.nbHumanPlayer = nbHumanPlayer;
	}
	
	/**
	 * Obtenir le nombre de joueurs ordinateur
	 * @return Renvoie le nombre de joueurs
	 */

	public int getNbComputerPlayer() {
		return nbComputerPlayer;
	}
	
	/**
	 * Definir le nombre de joueurs ordinateur
	 * @param nbComputerPlayer Nombre de joueurs
	 */

	public void setNbComputerPlayer(int nbComputerPlayer) {
		this.nbComputerPlayer = nbComputerPlayer;
	}
	
	/**
	 * Obtenir l'index des joueurs actifs
	 * @return Renvoie l'index des joueurs actifs
	 */
	
	public int getIndexOfActivePlayer () {
		return listPlayer.indexOf(activePlayer);
	}
	
	/**
	 * Obtenir la liste des joueurs
	 * @return Renvoie la liste des joueurs
	 */

	public LinkedList<Player> getListPlayer() {
		return listPlayer;
	}
	
	/**
	 * Obtenir le joueur actif dans ce tour
	 * @return Renvoie le joueur actif
	 */
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	
	/**
	 * Obtenir la liste des joueurs ciblables
	 * @return Renvoie la liste des joueurs
	 */

	public HashSet<Player> getLp() {
		return lp;
	}
	
	/**
	 * Obtenir le nombre total de joueurs
	 * @return Renvoie le nombre de joueurs
	 */

	public int getNbPlayer() {
		return getNbComputerPlayer()+getNbHumanPlayer();
	}
	
}
