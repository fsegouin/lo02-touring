package fr.lo02.model.card;

public class Distance extends Card {
	
	int milage;

	public Distance(int milage) {
		super();
		this.milage = milage;
	}

	@Override
	public String toString() {
		return "Distance [milage=" + milage + "]";
	}
	
	

}
