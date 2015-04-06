package model;

import java.util.ArrayList;

import model.objet.ObjetCollision;
import model.objet.ObjetCollision.Type;


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
			if( objA != objB  && objB.isTouch(objA.getHitBox())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Renvoie le 1er élement que l'on trouve contenant le "type".
	 * 
	 * @param type
	 * @return
	 */
	public ObjetCollision searchFirstType(Type type){
		if( type == null ){
			return null;
		}
		
		for( ObjetCollision obj : listObjetCollision ){
			if( type.name().equals( obj.getNomType() ) ){
				return obj;
			}
		}
		
		return null;
	}
	
}