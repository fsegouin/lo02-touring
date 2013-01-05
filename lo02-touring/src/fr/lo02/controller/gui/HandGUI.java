package fr.lo02.controller.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.stack.Hand;

public class HandGUI extends JPanel implements Observer{

	Player player;
	Hand hand;
	
	JButton[] card = new JButton[5];
	JPanel jpHand_left, jpHand_right;
	JButton limitspeed, battlePlayer;

	public HandGUI(LinkedList<Player> linkedListPlayer) {
		
		for (Iterator iterator = linkedListPlayer.iterator(); iterator.hasNext();) {
			Player player = (Player) iterator.next();
			player.addObserver(this);
		}
		
	    jpHand_left = new JPanel();
	    jpHand_left.setLayout(new FlowLayout());
	    
	    jpHand_right = new JPanel();
	    jpHand_right.setLayout(new FlowLayout());
		
	    this.setLayout(new BorderLayout());
	    this.setSize(1024, 279);
	    //this.setBackground(Color.BLACK);
	    
	    for (int i = 0; i < card.length-1; i++) {
			card[i] = new JButton(new ImageIcon("images/cartes/Null.jpg"));
		    jpHand_left.add(card[i]);
		}
	    card[4] = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpHand_left.add(card[4]);
	    card[4].setVisible(false);
	    //jpHand_left.setBackground(Color.blue);
	    
	    
	    battlePlayer = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpHand_right.add(battlePlayer);
	    battlePlayer.setEnabled(false);
	    limitspeed = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpHand_right.add(limitspeed);
	    limitspeed.setEnabled(false);
	    jpHand_right.setBackground(Color.BLUE);
	    
	    this.add(jpHand_left, BorderLayout.WEST);
	    this.add(jpHand_right, BorderLayout.EAST);
	}

	
	public void update(Observable o, Object arg) {
		player = (Player) o;
		hand = player.getHand();
		int i = 0;
		
		if ((Integer)arg == 1) {
			for (Iterator iterator = hand.iterator(); iterator.hasNext();) {
				Card c = (Card) iterator.next();
				System.out.println(c.getFileName());
				card[i].setIcon(new ImageIcon("images/cartes/" + c.getFileName()));
				i++;
			}
			this.revalidate();
		}
	}


}
