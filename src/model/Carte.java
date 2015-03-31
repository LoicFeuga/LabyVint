package model;

import java.util.ArrayList;

import model.objet.ObjetCollision;



public class Carte {
	private ArrayList<ObjetCollision> listObjetCollision;
	
	
	public Carte(ArrayList<ObjetCollision> list){
		this.listObjetCollision = list;
		
	}
	
	/**
	 * Permet de savoir si "objA" est en collision
	 *  avec un des objet de la map.
	 * 
	 * @param hitBox celle qu'on veux tester parmis les autres objets
	 * @return true si il est en collision
	 */
	public boolean estEnCollision(ObjetCollision objA){
		for(int i = 0; i < listObjetCollision.size(); i++){
			ObjetCollision objB = listObjetCollision.get(i);
			if( objA != objB  && objB.isTouch(objA.getPosition())){
				return false;
			}
		}
		return true;
	}
	
}