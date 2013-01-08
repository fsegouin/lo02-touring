package fr.lo02.controller.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
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
import fr.lo02.model.Game;
import fr.lo02.model.Match;
import fr.lo02.model.stack.Hand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GameGUI extends JFrame {
	
	Game game;
	
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
	public GameGUI(Game _game) {
		
		this.game = _game;
		jtPlayer.setPreferredSize(new Dimension(150, 30));
		jtComputer.setPreferredSize(new Dimension(150, 30));

		setTitle("1000Bornes - LO02/UTT - FS & KM");
		this.setSize(1024, 600);
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
				JLabel aboutLabel = new JLabel("1000Bornes", new ImageIcon("images/logo/1000bornes.175x175.jpg"), JLabel.CENTER);
				aboutLabel.setVerticalTextPosition(JLabel.BOTTOM);
				aboutLabel.setHorizontalTextPosition(JLabel.CENTER);
				JLabel aboutThirdLabel = new JLabel("Kevin Maciolek & Florent Segouin");
				JLabel aboutFourthLabel = new JLabel("Janvier 2013");
				aboutFrame.add(aboutLabel);
				aboutFrame.add(aboutThirdLabel);
				aboutFrame.add(aboutFourthLabel);
				aboutFrame.pack();
				aboutFrame.setBounds(0, 0, 250, 300);
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
		mntmRgles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.jeuxdujardin.fr/files/regles/mb-express---rdj.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		helpMenu.add(mntmRgles);

		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout());
		//contentPane.setLayout(null);
		this.setContentPane(contentPane);

		
		jpPlayer.add(jlPlayer);
		jpPlayer.add(jtPlayer);

		jpComputer.add(jlComputer);
		jpComputer.add(jtComputer);
		JPanel jpadder = new JPanel();
		jpadder.setBounds(0, 0, 150, 150);
		
		jpadder.add(jpPlayer);
		jpadder.add(jpComputer);

		jpPlayerName.setLayout(new GridLayout(0, 1));
		jpPlayerName.setBounds(0, 0, 200, 200);
		
		jbvalidate = new JButton("Valider");
		jpValidate.add(jbvalidate);
		jpValidate.setBounds(0, 0, 100, 100);
		
		contentPane.add(jpadder, new Integer(1));
//		contentPane.add(jpPlayer, BorderLayout.NORTH);
//		contentPane.add(jpComputer, BorderLayout.NORTH);
		contentPane.add(jpPlayerName,new Integer(2));
		jpPlayerName.setBackground(Color.ORANGE);
		contentPane.add(jpValidate, new Integer(3));
		
		jtPlayer.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent e) {
				int nbPlayer = Integer.parseInt(jtPlayer.getText());
				int nbComputer = Integer.parseInt(jtComputer.getText());
				if (nbPlayer + nbComputer <= 8) {
					jpPlayerName.removeAll();
					for (int i = 0; i < nbPlayer; i++) {
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
	

	
	// Ajoute un listener sur "Validate"
	public void addValidateListener(ActionListener _actionlistener) {
		jbvalidate.addActionListener(_actionlistener);
	}

	public MatchGUI startMatch(Match _match) {
		contentPane.removeAll();
		matchGUI = new MatchGUI(getNbPlayer(), getNbComputer(), _match);
		this.setContentPane(matchGUI);
		pack();
		return matchGUI;
		}

	
	//=================== GETTER ADN SETTER ==================
	
	public JPanel getHandGUI() {
		return matchGUI.getJpHand();
	}
	
	public JLayeredPane getMatchGUI(){
		return matchGUI;
	}
	
	public int getNbPlayer() {
		return Integer.parseInt(jtPlayer.getText());
	}

	public int getNbComputer() {
		return Integer.parseInt(jtComputer.getText());
	}

	public String[] getPlayerName() {
		String[] tabPlayerName = new String[7];
		for (int i = 0; i < getNbPlayer(); i++) {
			tabPlayerName[i] = jtPlayerName[i].getText();
		}
		return tabPlayerName;
	}
}
