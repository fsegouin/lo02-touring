package fr.lo02.controller.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import fr.lo02.model.card.Card;
import fr.lo02.model.card.HazardCards.SpeedLimit;
import fr.lo02.model.card.remedyCards.EndOfLimit;

public class MatchGUI extends JLayeredPane implements Observer {

	// --------- INTERFACE DE JEU ---------
    JLayeredPane jlpContenair;
    JPanel jpAdversaire,jpMilieu, jpPlayer, jpIcone;
    ArrayList<JPanel> listJpPlayer = new ArrayList<JPanel>();
    HandGUI jpHand;
	JLabel jlNamePlayer,jlMilles;
	JButton battle;
	HashSet<Player> lp = new HashSet<Player>();
	JButton pioche,defausse;
	JLabel limit50;
	JLabel drivingAce,extraTank,punctureProof,rightOfWay;
	
	// ------------- UPDATE VIEW ---------------
	private CardList gameStack = new CardList();
	private CardList discardStack = new CardList();
	private int numActivePlayer = 0;
	private Player activePlayer = null;
	private LinkedList<Player> listPlayer = new LinkedList<Player>();
	private Match match;
	
	
	/**
	 * Consturcteur par defaut
	 * @param nbPlayer Le nombre de joueur
	 * @param nbComputer Le nombre d'ordinateur
	 * @param _match Le match(modele) lancé
	 */
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
		
	    for (int i = 0; i <nbPlayer+nbComputer; i++) {
	    	jpPlayer = new JPanel();
	    	jpPlayer.setBackground(Color.ORANGE);
	    	jpPlayer.setLayout(new BoxLayout(jpPlayer, BoxLayout.Y_AXIS));
	    	
	    	jlNamePlayer = new JLabel("Joueur" + i);
	    	jlNamePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	
	    	jlMilles = new JLabel("0 Kilometre");
	    	jlMilles.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	
	    	battle = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/Vide.jpg")));
	    	battle.setOpaque(false);
	    	battle.setContentAreaFilled(false);
	    	battle.setBorderPainted(false);
	    	battle.setAlignmentX(Component.CENTER_ALIGNMENT);
		    
	    	jpIcone = new JPanel(new FlowLayout());
	    	jpIcone.setBackground(Color.ORANGE);
	    	jpIcone.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	drivingAce = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/drive.jpg")));
	    	drivingAce.setVisible(false);
	    	extraTank = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/tank.jpg")));
	    	extraTank.setVisible(false);
	    	punctureProof = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/proof.jpg")));
	    	punctureProof.setVisible(false);
	    	rightOfWay = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/way.jpg")));
	    	rightOfWay.setVisible(false);
	    	limit50 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/limit50.jpg")));
	    	limit50.setVisible(false);
	    	
	    	jpIcone.add(limit50);
	    	jpIcone.add(drivingAce);
	    	jpIcone.add(extraTank);
	    	jpIcone.add(punctureProof);
	    	jpIcone.add(rightOfWay);
	    	
		    jpPlayer.add(jlNamePlayer);
		    jpPlayer.add(jlMilles);
		    jpPlayer.add(battle);
		    jpPlayer.add(jpIcone);
	    	jpAdversaire.add(jpPlayer);
	    	listJpPlayer.add(jpPlayer);
		}
	    
	    defausse = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/cartes/Vide.jpg")));
	    defausse.setOpaque(false);
	    defausse.setContentAreaFilled(false);
	    defausse.setBorderPainted(false);
	    jpMilieu.add(defausse, BorderLayout.CENTER);
	    pioche = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/cartes/Null.jpg")));
		pioche.setOpaque(false);
		pioche.setContentAreaFilled(false);
		pioche.setBorderPainted(false);
	    jpMilieu.add(pioche, BorderLayout.CENTER);

	    
	}


	public void update(Observable o, Object arg) {
		int countNbPlayer = 0;
		int indexActiveplayer = match.getIndexOfActivePlayer();

		// ---------- arg = 1 / MAJ BARRE DU HAUT ADVERSAIRE -----------
		if ((Integer) arg == 1) {
			listPlayer = match.getListPlayer();
			Iterator iterator = listJpPlayer.iterator();
			for (Iterator lp = listPlayer.iterator(); lp.hasNext();) {
				Player player = (Player) lp.next();
				JPanel jp = (JPanel) iterator.next();

				// ------- MAJ D'UN PANEL ADVERSAIRE------
				Component[] c = jp.getComponents();
				for (int i = 0; i < c.length; i++) {
					// Mise a jour non du joueur
					JLabel jl0 = (JLabel) c[0];
					jl0.setText(player.getName());

					// Mise a jour des kilometres
					JLabel jl1 = (JLabel) c[1];
					jl1.setText(String.valueOf(player.getTotalMilage() + " km"));

					if (player.getLastCardFromBattle() == null) {
						JButton jb2 = (JButton) c[2];
						jb2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/Vide.jpg")));
						jb2.setEnabled(false);
					} else {
						JButton jb2 = (JButton) c[2];
						jb2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/thumbnails/" + player.getLastCardFromBattle().getFileName())));
						jb2.setEnabled(false);
					}
					
					//Mise a jour des icones d'un ADVERSAIRE
					JPanel jp3 = (JPanel) c[3];
					Component[] icone = jp3.getComponents();
					JLabel[] ic = new JLabel[5];
					for (int j = 0; j < ic.length; j++) {
						ic[j] = (JLabel) icone[j];
					}
					if (player.getLastCardFromSpeed() instanceof SpeedLimit)
						ic[0].setVisible(true);
					else if (player.getLastCardFromSpeed() instanceof EndOfLimit)
						ic[0].setVisible(false);
					if (player.isDrivingAce())
						ic[1].setVisible(true);
					if (player.isExtraTank())
						ic[2].setVisible(true);
					if (player.isPunctureProof())
						ic[3].setVisible(true);
					if (player.isRightOfWay())
						ic[4].setVisible(true);
				}
				countNbPlayer++;
			}
			// ---------- MAJ DEFAUSSE -----------
			defausse.setEnabled(false);
			Card c = null;
			ImageIcon img = null;
			c = match.getDiscardStack().getLastElement();
			if (c != null) {
				img = new ImageIcon(getClass().getClassLoader().getResource("images/cartes/" + c.getFileName()));
			} else {
				img = new ImageIcon(getClass().getClassLoader().getResource("images/cartes/Vide.jpg"));
			}
			defausse.setIcon(img);
			
			//-----  arg = 2  /ACTIVE LES JOUEURS CIBLABLE QUAND UNE CARTE EST SELECTIONNE ------
		} else if ((Integer) arg == 2) {
			lp = match.getLp();
			// parcour des joueurs ciblable
			for (Iterator iterator = lp.iterator(); iterator.hasNext();) {
				Player player = (Player) iterator.next();
				// --------- JOUER SUR ADVERSAIRE ----------
				// parcour des panel representant les differents joueurs
				for (Iterator iterator2 = listJpPlayer.iterator(); iterator2.hasNext();) {
					JPanel jp = (JPanel) iterator2.next();
					Component[] c = jp.getComponents();
					JLabel jl0 = (JLabel) c[0];
					if (jl0.getText() == player.getName()) {
						JButton jb2 = (JButton) c[2];
						jb2.setEnabled(true);
					}
				}
				// --------- JOUER SUR SOIS MEME ----------
				if (player == match.getActivePlayer()) {
					jpHand.setBattlePlayerPLayable();
				}
			}
		}
	}

	public void addDiscardListener(ActionListener _DiscardActionListener) {
		defausse.addActionListener(_DiscardActionListener);
}
	
	
	//----- GETTER AND SETTER ------
	public HandGUI getJpHand() {
		return jpHand;
	}


	public JButton getPioche() {
		return pioche;
	}


	public JButton getDefausse() {
		return defausse;
	}

	
	public ArrayList<JPanel> getListJpPlayer() {
		return listJpPlayer;
	}

}
