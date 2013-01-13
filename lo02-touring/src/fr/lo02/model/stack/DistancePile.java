package fr.lo02.model.stack;

import fr.lo02.model.CardList;

public class DistancePile extends CardList {
	
	private int totalMilage;

	/**
	 * Permet d'afficher les cartes contenues dans la pile Distance
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "DistancePile [stack=" + stack + "]";
	}

	/**
	 * Obtenir le kilometrage total de la pile distance
	 * @return Renvoie le kilometrage
	 */
	public int getTotalMilage() {
		return totalMilage;
	}
	
	/**
	 * Definir le kilometrage total
	 * @param totalMilage Kilometrage a definir
	 */

	public void setTotalMilage(int totalMilage) {
		this.totalMilage = totalMilage;
	}
	
	/**
	 * Ajouter des kilometres
	 * @param km Kilometres a ajouter
	 */
	public void addToTotalMilage(int km) {
		this.totalMilage += km;
	}
	
}
