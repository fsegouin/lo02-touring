package fr.lo02.controller.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.lo02.model.CardList;
import fr.lo02.model.Match;
import fr.lo02.model.Player;

public class MatchGUI extends JLayeredPane implements Observer {

	// --------- INTERFACE DE JEU ---------
    JLayeredPane jlpContenair;
    JPanel jpAdversaire,jpMilieu, jpPlayer;
    ArrayList<JPanel> listJpPlayer = new ArrayList<JPanel>();
    HandGUI jpHand;
	JLabel jlNamePlayer,jlMilles;
	JButton battle;
	
	
	// ------------- UPDATE VIEW ---------------
	private CardList gameStack = new CardList();
	private CardList discardStack = new CardList();
	private int numActivePlayer = 0;
	private Player activePlayer = null;
	private LinkedList<Player> listPlayer = new LinkedList<Player>();
	private Match match;
	
	
	
	public MatchGUI(int nbPlayer, int nbComputer, Match _match) {
		this.match = _match;
		this.match.addObserver(this);
		
		this.setPreferredSize(new Dimension(1024, 768));
		jpAdversaire = new JPanel();
	    jpAdversaire.setBounds(0,0,1024,200);
	    jpAdversaire.setLayout(new GridLayout(1,7));
	    jpAdversaire.setBackground(Color.GRAY);

	    jpHand = new HandGUI(match.getListPlayer());
	    jpHand.setBounds(0,479,1024,279);
	    
	    jpMilieu = new JPanel();
	    jpMilieu.setLayout(new FlowLayout());
	    jpMilieu.setBounds(0,200,1024,279);
	    jpMilieu.setBackground(Color.GREEN);
	    

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
	    	
	    	battle = new JButton(new ImageIcon("images/thumbnails/Vide.jpg"));
	    	battle.setAlignmentX(Component.CENTER_ALIGNMENT);
		    
		    jpPlayer.add(jlNamePlayer);
		    jpPlayer.add(jlMilles);
		    jpPlayer.add(battle);
	    	jpAdversaire.add(jpPlayer);
	    	listJpPlayer.add(jpPlayer);
		}
	    
	    JButton pioche = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpMilieu.add(pioche, BorderLayout.CENTER);
	    JButton dfausse = new JButton(new ImageIcon("images/cartes/Null.jpg"));
	    jpMilieu.add(dfausse, BorderLayout.CENTER);
	    
	}

	
	public void update(Observable o, Object arg) {
		int countNbPlayer = 0;
		int indexActiveplayer = match.getIndexOfActivePlayer();
		listPlayer = match.getListPlayer();
		ListIterator<Player> lt = listPlayer.listIterator(indexActiveplayer+1);
		Iterator iterator = listJpPlayer.iterator();
		
		while (countNbPlayer != listPlayer.size()-1){

			// recupere le premier player(et son index) aprés activePlayer(1er tour de boucle)
			Player player = (Player) lt.next();
			// recupere le premier panel des adversaires
			JPanel jpPlayer = (JPanel) iterator.next();
			
			Component[] c = jpPlayer.getComponents();
			for (int i = 0; i < c.length; i++) {
				// Mise a jour non du joueur
				JLabel jl0 = (JLabel) c[0];
				jl0.setText(player.getName());
				
				// Mise a jour des kilometres
				JLabel jl1 = (JLabel) c[1];
				jl1.setText(String.valueOf(player.getTotalMilage()));
				
				if(player.getLastCardFromBattle() == null) {
					JButton jb2 = (JButton) c[2];
					jb2.setIcon( new ImageIcon("images/thumbnails/Vide.jpg"));
					jb2.setEnabled(false);
				}
			}	
			countNbPlayer++;
		} 
	}
	
	
	//----- GETTER AND SETTER ------
	public HandGUI getJpHand() {
		return jpHand;
	}

}
