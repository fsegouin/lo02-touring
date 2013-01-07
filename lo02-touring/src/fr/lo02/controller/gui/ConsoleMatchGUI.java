package fr.lo02.controller.gui;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import fr.lo02.model.Match;
import fr.lo02.model.Player;

public class ConsoleMatchGUI implements Observer{

	public ConsoleMatchGUI() {
	}

	@Override
	public void update(Observable o, Object arg) {
		if ((Integer) arg == 1) {
			System.out.println();
			System.out.println("-------------------------------------------------------");
			System.out.println("Etat du jeu");
			System.out.println("-------------------------------------------------------");
			Match currentMatch = Match.getInstance();
			for (Iterator iterator = currentMatch.getListPlayer().iterator(); iterator.hasNext();) {
				Player aPlayer = (Player) iterator.next();
				System.out.println("--- " + aPlayer.getName() + " ---");
				System.out.println("Main : " + aPlayer.getHand().toString());
				if(aPlayer.getTotalMilage() != 0)
					System.out.println("Kilometres au compteur : " + aPlayer.getTotalMilage());
				if(aPlayer.getLastCardFromBattle() != null)
					System.out.println("Etat actuel : " + aPlayer.getLastCardFromBattle().toString());
				if(aPlayer.getLastCardFromSpeed() != null)
					System.out.println("Limite de vitesse : " + aPlayer.getLastCardFromSpeed().toString());
			}
			System.out.println("-------------------------------------------------------");
		}
		else {
			
		}
	}

	public void addMatchObserver(Match match) {
		match.addObserver(this);
	}

}
