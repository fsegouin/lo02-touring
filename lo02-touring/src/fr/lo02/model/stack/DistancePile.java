package fr.lo02.model.stack;

import fr.lo02.model.CardList;

public class DistancePile extends CardList {
	
	private int totalMilage;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DistancePile [stack=" + stack + "]";
	}

	public int getTotalMilage() {
		return totalMilage;
	}

	public void setTotalMilage(int totalMilage) {
		this.totalMilage = totalMilage;
	}
	
	public void addToTotalMilage(int km) {
		this.totalMilage += km;
	}
	
}
