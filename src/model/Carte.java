package model;

import java.awt.Rectangle;
import java.util.ArrayList;

import model.objet.ObjetCollision;



public class Carte {
	private ArrayList<ObjetCollision> listObjetCollision;
	
	
	public Carte(ArrayList<ObjetCollision> list){
		this.listObjetCollision = list;
		
	}
	
	/**
	 * Permet de savoir si la nouvelle position de hitBox 
	 *  peut être accepté
	 *  
	 * C'est à dire, si aucun ObjetCollision n'est pas déjà présent 
	 * 
	 * S'il y a un Objet, le moteur ne devrait pas update
	 * 
	 * Sinon il pourra
	 * 
	 * 
	 * @param hitBox celle qu'on veux tester parmis les autres objets
	 * @return true si on peux = il y a aucun objet or false
	 */
	public boolean canMoveToNewDirection(Rectangle hitBox){
		for(int i = 0; i < listObjetCollision.size(); i++){
			if(listObjetCollision.get(i).isTouch(hitBox)){
				return false;
			}
		}
		return true;
	}
	
}