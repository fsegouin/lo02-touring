package fr.lo02.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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

public class Match {
	
	private static volatile Match instance = null;

	private CardList gameStack = new CardList();
	private CardList discardStack = new CardList();
	private int activePlayer = 0;
	private ArrayList<Player> listPlayer = new ArrayList<Player>();
	private String[] namePlayer;
	private int nbHumanPlayer;
	private int nbComputerPlayer;

	/**
	 * Le constructeur de match Initialise les joueurs, les stacks de jeu
	 * 
	 * @param nbComputerPlayer
	 * @param nbHumanPlayer
	 * @param namePlayer
	 */
	
	private Match() {
		super();
	}
	
	public void initMatch() {
		this.playerInit(nbComputerPlayer, nbHumanPlayer, namePlayer);
		this.gameStackInit();
		this.playerHandInit();
	}

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
	 * Retourne une liste des joueurs qui peuvent etre cibles
	 * 
	 * @param activePlayer
	 * @return Playerlist where player are playable by this card
	 */
	public HashSet<Player> checkCardPlayable(Player activePlayer) throws SelectedCardNotDefinedException {
		HashSet<Player> lp = new HashSet<Player>();
		Player p = null;
		if (activePlayer.getSelectedCard() != null) {

			for (Iterator<Player> iterator = listPlayer.iterator(); iterator.hasNext();) {
				Player testTargetPlayer = (Player) iterator.next();
				// Test de la carte selectionne par le joueur actif est jouable
				// sur la liste de tout les joueurs
				try {
					p = activePlayer.getSelectedCard().checkValidMove(activePlayer, testTargetPlayer);
				} catch (NotValidCardOnBattleException e) {
					System.out.println(e.getMessage());
					break;
				}
				if (p != null) {
					lp.add(p);
					p = null;
				}
			}
		} else
			throw new SelectedCardNotDefinedException("Vous n'avez pas selectionne de carte a jouer.");
		return lp;
	}
	
	/**
	 * Retourne le player ou va etre jouer la carte
	 * @param playerName Nom du player cible
	 * @param lp Liste des player ciblable
	 * @return Le player cible
	 */
	public Player getPlayerByName(String playerName, HashSet<Player> lp) {
		Player returnedPlayer = null;
		for (Iterator<Player> iterator = lp.iterator(); iterator.hasNext();) {
			Player aPlayer = (Player) iterator.next();
			if(aPlayer.getName().contentEquals(playerName))
				returnedPlayer = aPlayer;
		}
		return returnedPlayer;
	}
	
	public Player nextPlayer() {
		activePlayer++;
		if (activePlayer > listPlayer.size()) {
			activePlayer = 1;
		}
		Player nextPlayer = listPlayer.get(activePlayer - 1);
		return nextPlayer;
	}

	/**
	 * Create the list of player(human and computer)
	 * 
	 * @param nbComputerPlayer
	 * @param nbHumanPlayer
	 * @param namePlayer
	 */
	public void playerInit(int nbComputerPlayer, int nbHumanPlayer, String[] namePlayer) {

		for (int i = 0; i < nbHumanPlayer; i++) {
			HumanPlayer humanplayer = new HumanPlayer(namePlayer[i], i);
			this.listPlayer.add(humanplayer);
		}
		
		if(nbComputerPlayer > 0) {
			Strategy attackPriority = new AttackPriority();
			Strategy defensePriority = new DefensePriority();
			Strategy speedPriority = new SpeedPriority();
			for (int i = nbHumanPlayer - 1; i < nbComputerPlayer; i++) {
				ComputerPlayer computerplayer = new ComputerPlayer(namePlayer[i], i, attackPriority);
				this.listPlayer.add(computerplayer);
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

	public void gameStackInit() { // Initialise la pioche de jeu principale
		for (int i = 0; i < 2; i++) { // 2 stop cards
			Stop aStop = new Stop();
			gameStack.toStack(aStop);
		}
		for (int i = 0; i < 5; i++) { // 2 roll cards
			GoRoll aGoRoll = new GoRoll();
			aGoRoll.setRemedyCard(true); // Permet de definir un feu vert comme une carte de type remedyCard
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
//		for (int i = 0; i < 6; i++) {
//			Distance aDistance = new Distance(25);
//			gameStack.toStack(aDistance);
//		}
//
//		for (int i = 0; i < 6; i++) {
//			Distance aDistance = new Distance(50);
//			gameStack.toStack(aDistance);
//		}
//
//		for (int i = 0; i < 6; i++) {
//			Distance aDistance = new Distance(75);
//			gameStack.toStack(aDistance);
//		}

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

	public CardList getGameStack() {
		return gameStack;
	}

	public CardList getDiscardStack() {
		return discardStack;
	}
	
	public void addToDiscardStack(Card aCard) {
		discardStack.stack.add(aCard);
	}
	
	public String[] getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String[] namePlayer) {
		this.namePlayer = namePlayer;
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

}
