package fr.lo02.controller.gui;

import java.util.Observable;
import java.util.Observer;

import fr.lo02.model.Match;

public class ConsoleMatchGUI implements Observer{

	public ConsoleMatchGUI() {
	}

	@Override
	public void update(Observable o, Object arg) {
		if ((Integer) arg == 1) {
		System.out.println("Etat du jeu : ");
		}
		else {
			
		}
	}

	public void addMatchObserver(Match match) {
		match.addObserver(this);
	}

}
