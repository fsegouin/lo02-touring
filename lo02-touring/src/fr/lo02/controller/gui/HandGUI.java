package fr.lo02.controller.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import fr.lo02.model.Player;
import fr.lo02.model.card.Card;
import fr.lo02.model.stack.Hand;

public class HandGUI extends JPanel implements Observer{

	Player player;
	Hand hand;
	JLabel namePlayer, kmPlayer;
	JButton[] card = new JButton[5];
	JPanel jpHand_left,jpHand_right,jpHand_rightTop,jpHand_right_Bot,jpHand_leftTop,jpHand_left_Bot;
	JButton limitspeed, battlePlayer;
	JTextArea textAreaInfo;
	JScrollPane scrollPane;
	
	public HandGUI(LinkedList<Player> linkedListPlayer) {
			  
		for (Iterator iterator = linkedListPlayer.iterator(); iterator.hasNext();) {
			Player player = (Player) iterator.next();
			player.addObserver(this);
		}
		
	    jpHand_left = new JPanel();
	    jpHand_left.setLayout(new BorderLayout());
	    jpHand_leftTop = new JPanel();
	    jpHand_left_Bot = new JPanel();
	    
	    jpHand_right = new JPanel();
	    jpHand_right.setLayout(new BorderLayout());  
		jpHand_rightTop = new JPanel();
		jpHand_rightTop.setLayout(new FlowLayout());	
		jpHand_right_Bot = new JPanel();
		jpHand_right_Bot.setLayout(new FlowLayout());
		
	    this.setLayout(new BorderLayout());
	    this.setSize(1024, 279);
	    
	    for (int i = 0; i < card.length; i++) {
			card[i] = new JButton(new ImageIcon("images/cartes/Null.jpg"));
			card[i].setOpaque(false);
			card[i].setContentAreaFilled(false);
			card[i].setBorderPainted(false);
		    jpHand_leftTop.add(card[i]);
		}	 
	    
	    textAreaInfo = new JTextArea();
	    textAreaInfo.setEditable(false);
        scrollPane = new JScrollPane(textAreaInfo);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(650, 80));
        jpHand_left_Bot.add(scrollPane);
        //Redirige les System.out.print vers le textArea
	    this.redirectSystemStreams();        
        
	    kmPlayer = new JLabel();
	    kmPlayer.setFont(new Font("Serif", Font.PLAIN, 36));
	    namePlayer = new JLabel();
	    namePlayer.setFont(new Font("Serif", Font.PLAIN, 36));
	    jpHand_right_Bot.add(namePlayer);
	    jpHand_right_Bot.add(kmPlayer);
	    
	    jpHand_left.add(jpHand_leftTop, BorderLayout.NORTH);
	    jpHand_left.add(jpHand_left_Bot, BorderLayout.SOUTH);
	    
	    battlePlayer = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpHand_rightTop.add(battlePlayer);
	    battlePlayer.setEnabled(false);
	    limitspeed = new JButton(new ImageIcon("images/cartes/Vide.jpg"));
	    jpHand_rightTop.add(limitspeed);
	    limitspeed.setEnabled(false);
	    
	    jpHand_right.add(jpHand_rightTop, BorderLayout.NORTH);
	    jpHand_right.add(jpHand_right_Bot, BorderLayout.SOUTH);
	    this.add(jpHand_left, BorderLayout.WEST);
	    this.add(jpHand_right, BorderLayout.EAST);
	    
	}

	
	public void update(Observable o, Object arg) {
		player = (Player) o;
		hand = player.getHand();
		int i = 0;
		if ((Integer)arg == 1) {
			namePlayer.setText(player.getName());
			kmPlayer.setText(" - "+player.getTotalMilage()+" km");
			for (Iterator iterator = hand.iterator(); iterator.hasNext();) {
				Card c = (Card) iterator.next();
				card[i].setIcon(new ImageIcon("images/cartes/" + c.getFileName()));
				card[i].setEnabled(true);
				i++;
			}
			if (player.getLastCardFromBattle() == null) {
				battlePlayer.setIcon(new ImageIcon("images/cartes/Vide.jpg"));
				battlePlayer.setEnabled(false);
			}
			else {
				battlePlayer.setIcon(new ImageIcon("images/cartes/"+player.getLastCardFromBattle().getFileName()));
				battlePlayer.setEnabled(false);
			}

			this.revalidate();
		}
	}
	

	// Ajoute un listener sur "Validate"
	public void addCardListener(ActionListener _CardActionListener) {
			for (int i = 0; i < card.length; i++) {
				card[i].addActionListener(_CardActionListener);
			}
	}
	
	// Ajoute un listener sur "Validate"
	public void addBattleListener(ActionListener _BattleActionListener) {
				battlePlayer.addActionListener(_BattleActionListener);
	}
	
	public JButton getLimitspeed() {
		return limitspeed;
	}


	public JButton getBattlePlayer() {
		return battlePlayer;
	}


	//------------ GETTER AND SETTER --------------
	public JButton[] getCard() {
		return card;
	}
	
	public void setBattlePlayerPLayable() {
		battlePlayer.setEnabled(true);
	}
	
	//
	private void updateTextArea(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textAreaInfo.append(text);
			}
		});
	}

	private void redirectSystemStreams() {
		// Redirection des system.out.println vers le TextArea
		OutputStream out = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				updateTextArea(String.valueOf((char) b));
			}

			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				updateTextArea(new String(b, off, len));
			}

			@Override
			public void write(byte[] b) throws IOException {
				write(b, 0, b.length);
			}
		};
		System.setOut(new PrintStream(out, true));
	}
	
}
