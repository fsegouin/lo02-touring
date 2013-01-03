package fr.lo02.controller.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MatchGUI extends JLayeredPane {

	// --------- INTERFACE DE JEU ---------
    JLayeredPane jlpContenair;
    JPanel jpAdversaire,jpMilieu,jpHand, jpHand_left, jpHand_right, jpPlayer;
	JButton card1, card2, card3, card4, card5;
	JButton battle, limitspeed, battlePlayer;
	JLabel jlNamePlayer,jlMilles;
	
	public MatchGUI(int nbPlayer, int nbComputer) {
		
		this.setPreferredSize(new Dimension(1024, 768));
		
		jpAdversaire = new JPanel();
	    jpAdversaire.setBounds(0,0,1024,200);
	    jpAdversaire.setLayout(new GridLayout(1,7));
	    jpAdversaire.setBackground(Color.GRAY);

	    jpMilieu = new JPanel();
	    jpMilieu.setLayout(new FlowLayout());
	    jpMilieu.setBounds(0,200,1024,279);
	    jpMilieu.setBackground(Color.GREEN);
	    
	    jpHand = new JPanel();
	    jpHand.setLayout(new BorderLayout());
	    jpHand.setBounds(0,479,1024,279);
	    jpHand.setBackground(Color.BLACK);
	    
	    jpHand_left = new JPanel();
	    jpHand_left.setLayout(new FlowLayout());
	    
	    jpHand_right = new JPanel();
	    jpHand_right.setLayout(new FlowLayout());
		
	    //Ajout des 3 grands contenairs
	    this.add(jpAdversaire, new Integer(1));
	    this.add(jpMilieu, new Integer(2));
	    this.add(jpHand, new Integer(3));
		
	    for (int i = 0; i <nbPlayer+nbComputer-1; i++) {
	    	jpPlayer = new JPanel();
	    	jpPlayer.setBackground(Color.ORANGE);
	    	jpPlayer.setLayout(new BoxLayout(jpPlayer, BoxLayout.Y_AXIS));
	    	
	    	jlNamePlayer = new JLabel("Joueur" + i);
	    	jlNamePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	
	    	jlMilles = new JLabel("0 Kilometre");
	    	jlMilles.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	
	    	battlePlayer = new JButton(new ImageIcon("images/thumbnails/Vide.jpg"));
	    	battlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		    
		    jpPlayer.add(jlNamePlayer);
		    jpPlayer.add(jlMilles);
		    jpPlayer.add(battlePlayer);
	    	jpAdversaire.add(jpPlayer);	
		}
	    
	    JButton pioche = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpMilieu.add(pioche, BorderLayout.CENTER);
	    JButton dfausse = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpMilieu.add(dfausse, BorderLayout.CENTER);
	    
	    card1 = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpHand_left.add(card1);
	    card2 = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpHand_left.add(card2);
	    card3 = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpHand_left.add(card3);
	    card4 = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpHand_left.add(card4);
	    card5 = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpHand_left.add(card5);
	    jpHand_left.setBackground(Color.blue);

	    battle = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpHand_right.add(battle);
	    battle.setEnabled(false);
	    limitspeed = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpHand_right.add(limitspeed);
	    limitspeed.setEnabled(false);
	    jpHand_right.setBackground(Color.WHITE);
	     
	    jpHand.add(jpHand_left, BorderLayout.WEST);
	    jpHand.add(jpHand_right, BorderLayout.EAST);
	    
	}

}
