package fr.lo02.model;

import fr.lo02.model.stack.PlayerStack;

public class Player {
	
	private String name;
	private int color;
	
	PlayerStack myStack = new PlayerStack();
	
	public Player(String name, int color) {
		this.name = name;
		this.color = color;
	}
	
	

}
