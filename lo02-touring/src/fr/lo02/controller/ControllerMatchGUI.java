package fr.lo02.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.lo02.controller.ControllerGameGUI.StartGame;
import fr.lo02.controller.gui.GameGUI;
import fr.lo02.controller.gui.HandGUI;
import fr.lo02.controller.gui.MatchGUI;
import fr.lo02.model.Match;
import fr.lo02.model.exception.SelectedCardNotDefinedException;

public class ControllerMatchGUI {

	Match match;
	MatchGUI matchGUI;
	HandGUI handGUI;
	JButton[] card = new JButton[4];
	JButton defausse;
	JButton battlePlayer;
	
	public ControllerMatchGUI(Match _match, MatchGUI _matchGUI) {
		this.match = _match;
		this.matchGUI = _matchGUI;
		this.handGUI = matchGUI.getJpHand();
		this.card = handGUI.getCard();
		this.defausse = matchGUI.getDefausse();
		this.battlePlayer = handGUI.getBattlePlayer();
		
		this.matchGUI.addDiscardListener(new DiscardAction());
		this.handGUI.addBattleListener(new BattleAction());
		for (int i = 0; i < 5; i++) {
			this.handGUI.addCardListener(new CardAction());
		}

	}
	
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

	class DiscardAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			match.addToDiscardStack(match.getActivePlayer().getSelectedCard());
			match.getActivePlayer().getSelectedCard().throwThisCard(match.getActivePlayer());
		}
	}
	
	class BattleAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == battlePlayer)
			match.getActivePlayer().getSelectedCard().playThisCard(match.getActivePlayer(), match.getActivePlayer());
		}
	}
}
