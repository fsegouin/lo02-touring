package fr.lo02.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.lo02.controller.ControllerGameGUI.StartGame;
import fr.lo02.controller.gui.GameGUI;
import fr.lo02.controller.gui.HandGUI;
import fr.lo02.controller.gui.MatchGUI;
import fr.lo02.model.Match;
import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.exception.SelectedCardNotDefinedException;

public class ControllerMatchGUI {

	Match match;
	MatchGUI matchGUI;
	HandGUI handGUI;
	JButton[] card = new JButton[4];
	JButton defausse;
	JButton battlePlayer;
    ArrayList<JPanel> listJpPlayer = new ArrayList<JPanel>();

    
    /**
     * Demarre le controlleur
     * @param _match
     * @param _matchGUI
     */
	public ControllerMatchGUI(Match _match, MatchGUI _matchGUI) {
		this.match = _match;
		this.matchGUI = _matchGUI;
		this.handGUI = matchGUI.getJpHand();
		this.card = handGUI.getCard();
		this.defausse = matchGUI.getDefausse();
		this.battlePlayer = handGUI.getBattlePlayer();
		this.listJpPlayer = matchGUI.getListJpPlayer();
		
		//Listener de la deselection de la carte
		this.handGUI.addMouseListener(new DeselectCardAction());
		//Listener de la pioche
		this.matchGUI.addDiscardListener(new DiscardAction());
		//Listener des cards
		this.handGUI.addBattleListener(new BattleAction());
		for (int i = 0; i < 5; i++) {
			this.handGUI.addCardListener(new CardAction());
		}
		//Listener des adverssaire
		for (Iterator iterator2 = listJpPlayer.iterator(); iterator2.hasNext();) {
			JPanel jp = (JPanel) iterator2.next();
			Component[] c = jp.getComponents();
			JButton jb2 = (JButton) c[2];
			jb2.addActionListener(new BattlePlayerAction());
		}
	}
	
	/**
	 * Cette classe represente les actions effectuées lors d'un clic sur une card
	 * @author Kevin
	 *
	 */
	class CardAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			int numCard = 0;
			// CARD 0
			if (arg0.getSource() == card[0]) {
				card[1].setBackground(Color.BLACK);
				card[1].setEnabled(false);
				card[2].setEnabled(false);
				card[3].setEnabled(false);
				card[4].setEnabled(false);
				numCard = 0;
				selectCard(numCard);
			}
			// CARD 1
			else if (arg0.getSource() == card[1]) {
				card[0].setEnabled(false);
				card[2].setEnabled(false);
				card[3].setEnabled(false);
				card[4].setEnabled(false);
				numCard = 1;
				selectCard(numCard);
			}
			// CARD 2
			else if (arg0.getSource() == card[2]) {
				card[0].setEnabled(false);
				card[1].setEnabled(false);
				card[3].setEnabled(false);
				card[4].setEnabled(false);
				numCard = 2;
				selectCard(numCard);
			}
			// CARD 3
			else if (arg0.getSource() == card[3]) {
				card[0].setEnabled(false);
				card[1].setEnabled(false);
				card[2].setEnabled(false);
				card[4].setEnabled(false);
				numCard = 3;
				selectCard(numCard);
			}
			// CARD 4
			else if (arg0.getSource() == card[4]) {
				card[0].setEnabled(false);
				card[1].setEnabled(false);
				card[2].setEnabled(false);
				card[3].setEnabled(false);
				numCard = 4;
				selectCard(numCard);
			}
			
		}
		
		/**
		 * 
		 * @param numCard
		 */
		public void selectCard(int numCard) {
			match.activePlayer.setSelectedCard(match.activePlayer.getHand().get(numCard));
			try {
				match.checkCardPlayable(match.getActivePlayer());
				defausse.setEnabled(true);
			} catch (SelectedCardNotDefinedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Cette classe represente les actions effectuées lors d'un clic sur la pioche
	 * @author Kevin
	 *
	 */
	class DiscardAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			match.addToDiscardStack(match.getActivePlayer().getSelectedCard());
			match.getActivePlayer().getSelectedCard().throwThisCard(match.getActivePlayer());
		}
	}
	
	/**
	 * Cette classe represente les actions effectuées lors d'un clic sur la pile battle du joueur actif
	 * @author Kevin
	 *
	 */
	class BattleAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == battlePlayer)
			match.getActivePlayer().getSelectedCard().playThisCard(match.getActivePlayer(), match.getActivePlayer());
		}
	}
	
	/**
	 * Cette classe represente les actions effectuées lors d'un clic sur la pile battle des adversaires
	 * @author Kevin
	 *
	 */
	class BattlePlayerAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			for (int i = 0; i < match.getNbPlayer(); i++) {
				JPanel jp = listJpPlayer.get(i);
				Component[] c = jp.getComponents();
				if(arg0.getSource() == c[2]){
				JLabel jl = (JLabel) c[0];
				System.out.println(jl.getText());
				Player selectedPlayer = match.getPlayerByName(jl.getText());
				Card cSafetyCard = match.getActivePlayer().getSelectedCard().playThisCard(match.getActivePlayer(), selectedPlayer);
				// Si une carte est renvoye par playThisCard, c'est que le joueur peut jouer un coup fourre
				}
			}
		}
	}
	
	/**
	 * Cette classe represente les actions effectuées lors d'un clic sur le panel joueur(deselectionne la card active)
	 * @author Kevin
	 *
	 */
	class DeselectCardAction implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < card.length; i++) {
				card[i].setEnabled(true);
			}
			for (Iterator iterator = listJpPlayer.iterator(); iterator.hasNext();) {
				JPanel jp = (JPanel) iterator.next();
				Component[] c = jp.getComponents();
				JButton jb = (JButton)c[2];
					jb.setEnabled(false);
			}
			defausse.setEnabled(false);
			battlePlayer.setEnabled(false);
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

}
