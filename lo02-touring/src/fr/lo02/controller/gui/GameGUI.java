package fr.lo02.controller.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
	
	// --------- INTERFACE DE GAME ---------	
	
	JPanel jpPlayer = new JPanel();
	JLabel jlPlayer = new JLabel("Nombre de joueur humain :");
	JTextField jtPlayer = new JTextField("0");
	
	JPanel jpComputer = new JPanel();
	JLabel jlComputer = new JLabel("Nombre de joueur ordinateur :");
	JTextField jtComputer = new JTextField("0");
	
	JPanel jpPlayerName = new JPanel();
	JLabel jlPlayerName;
	JLabel[] jlComputerName = new JLabel[7];
	JTextField[] jtPlayerName = new JTextField[7];
	
	JPanel jpValidate = new JPanel();
	JButton jbvalidate;

	// --------- IMPORTANT ---------
	private JPanel contentPane;
	private MatchGUI matchGUI;

	/**
	 * Create the frame.
	 */
	public GameGUI() {

		jtPlayer.setPreferredSize(new Dimension(150, 30));
		jtComputer.setPreferredSize(new Dimension(150, 30));

		setTitle("1000Bornes - LO02/UTT - FS & KM");
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		JMenu mnbornes = new JMenu("1000Bornes");
		menuBar.add(mnbornes);

		// New Jframe quand on clique sur "A propos"
		JMenuItem mntmAPropos = new JMenuItem("A propos");
		mntmAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame aboutFrame = new JFrame();
				aboutFrame.setLayout(new FlowLayout());
				ImageIcon imageIcon = new ImageIcon("twitter.png");
				JLabel aboutLabel = new JLabel("Coucou", imageIcon, JLabel.CENTER);
				aboutFrame.add(aboutLabel);
				aboutFrame.pack();
				aboutFrame.setBounds(0, 0, 200, 200);
				aboutFrame.setLocationRelativeTo(null);
				aboutFrame.setVisible(true);
			}
		});
		mnbornes.add(mntmAPropos);

		// Quitte la frame principal quand on clique sur "Quitter"
		JMenuItem mntmQuitter_1 = new JMenuItem("Quitter");
		mntmQuitter_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(-1);
			}
		});
		mnbornes.add(mntmQuitter_1);

		JMenu optionsMenu = new JMenu("Options");
		menuBar.add(optionsMenu);

		JMenuItem mntmRglages = new JMenuItem("R\u00E9glages");
		optionsMenu.add(mntmRglages);

		JMenu helpMenu = new JMenu("Aide");
		menuBar.add(helpMenu);

		JMenuItem mntmRgles = new JMenuItem("R\u00E8gles");
		helpMenu.add(mntmRgles);

		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0,1));
		//contentPane.setLayout(null);
		this.setContentPane(contentPane);

		
		jpPlayer.add(jlPlayer);
		jpPlayer.add(jtPlayer);

		jpComputer.add(jlComputer);
		jpComputer.add(jtComputer);

		jpPlayerName.setLayout(new GridLayout(0, 1));
		jbvalidate = new JButton("Valider");

		jpValidate.add(jbvalidate);

		contentPane.add(jpPlayer);
		contentPane.add(jpComputer);
		contentPane.add(jpPlayerName);
		jpPlayerName.setBackground(Color.ORANGE);
		contentPane.add(jpValidate);
		
		jtPlayer.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent e) {
				int nbPlayer = Integer.parseInt(jtPlayer.getText());
				int nbComputer = Integer.parseInt(jtComputer.getText());
				if (nbPlayer + nbComputer <= 8) {
					jpPlayerName.removeAll();
					for (int i = 0; i < nbPlayer; i++) {
						System.out.println("TEST");
						jlPlayerName = new JLabel("Joueur humain " + i + " : ");
						jpPlayerName.add(jlPlayerName);
						jtPlayerName[i] = new JTextField();
						jtPlayerName[i].setPreferredSize(new Dimension(150, 30));
						jpPlayerName.add(jtPlayerName[i]);
					}
				}
				else{
					//IL A PLUS DE 8 JOUEURS
				}
				jpPlayerName.revalidate();
			}
		});
	}
	
	public void addValidateListener(ActionListener _actionlistener) {
		jbvalidate.addActionListener(_actionlistener);
	}

	public void startMatch() {
		contentPane.removeAll();
		matchGUI = new MatchGUI(getNbPlayer(), getNbComputer());
		this.setContentPane(matchGUI);
		pack();
	}

	
	//=================== GETTER ADN SETTER ==================
	
	public int getNbPlayer() {
		return Integer.parseInt(jtPlayer.getText());
	}

	public int getNbComputer() {
		return Integer.parseInt(jtComputer.getText());
	}

	public String[] getPlayerName() {
		String[] tabPlayerName = new String[8];
		for (int i = 0; i < getNbPlayer()-1; i++) {
			tabPlayerName[i] = jtPlayerName[i].getText();
		}
		return tabPlayerName;
	}
}
