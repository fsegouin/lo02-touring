package fr.lo02.controller.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("1000Bornes - LO02/UTT - FS & KM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
	    
	    JMenu mnbornes = new JMenu("1000Bornes");
	    menuBar.add(mnbornes);
	    
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
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
