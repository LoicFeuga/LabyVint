package model;

import java.util.ArrayList;
import java.util.List;

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
	public ObjetCollision estEnCollision(ObjetCollision objA){
		for(int i = 0; i < listObjetCollision.size(); i++){
			ObjetCollision objB = listObjetCollision.get(i);
			if( objA != objB  && objB.isTouch(objA.getHitBox())){
				return objB;
			}
		}
		return null;
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
	
	/**
	 * Permet de supprimer un objet collision.
	 * @param obj
	 */
	public void removeObjCollision(ObjetCollision obj){
		listObjetCollision.remove(obj);
	}
	
	/**
	 * Permet de rajouter un objet collision dans la liste de collision.
	 * @param obj
	 */
	public void addObjCollision(ObjetCollision obj){
		listObjetCollision.add(obj);
	}
	
	/**
	 * Permet de récupérer les objets qui sont utilisable part le joueur (cles, bloc, etc..), 
	 * sur la carte courante.
	 * @return
	 */
	public List<ObjetCollision> getObjetTouchable(){
		List<ObjetCollision> listObj = new ArrayList<>();
		
		for( int i = 0; i < listObjetCollision.size(); i++){
			ObjetCollision objC = listObjetCollision.get(i);
			if( !objC.getNomType().equals(Type.Mur.name()) &&
				!objC.getNomType().equals(Type.Joueur.name()) &&
				!objC.getNomType().equals(Type.Ennemis.name()) ){
				
				listObj.add(objC);
			}
			
		}
		
		return listObj;
	}
}