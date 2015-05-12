package model;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import model.objet.Objet;
import model.objet.ObjetCollision;
import model.objet.ObjetCollision.Type;
import model.personne.Joueur;

public class Moteur extends Observable {

	private static Moteur moteur = null;
	private String nomJoueur;
	private Joueur joueur;
	private HashMap<Integer, Carte> listCarte;
	private int level;
	
	//RESET
	private HashMap<ObjetCollision, Point> listReset;

	// ------------------------------------------- Singleton
	private Moteur(HashMap<Integer, Carte> listCarteMoteur, String nomJoueur) {
		listCarte = listCarteMoteur;
		this.nomJoueur = nomJoueur;
		listReset = new HashMap<>();
	}
	
	public static Moteur creerMoteur( HashMap<Integer, Carte> listCarteMoteur, String nomJoueur ){
		moteur = new Moteur(listCarteMoteur, nomJoueur);
		return moteur;
	}

	public static Moteur getMoteur(){
		return moteur;
	}
	
	// ------------------------------------------- Update
	/**
	 * Permet de mettre a jour la vue.
	 * 
	 * HashMap : "deplacer"->Point
	 *                 "id"->int
	 */
	private void updateMove(ObjetCollision objC) {
		HashMap<String, HashMap<String, Object>> send = new HashMap<>();
		HashMap<String, Object> description = new HashMap<>();
			
		description.put("point", objC.getDefaultPosition());
		description.put("id",objC.getId());
		
		send.put("deplacer", description);
		
		setChanged();
		notifyObservers(send);
	}
	
	/**
	 * Permet de cacher un objet sur la vue
	 * 
	 * HashMap :   "id"->int
	 */
	private void updateCacher(ObjetCollision objC) {
		HashMap<String, Integer> send = new HashMap<>();
		send.put("cacher", objC.getId());
		
		setChanged();
		notifyObservers(send);
	}
	
	/**
	 * Permet de decacher un objet sur la vue
	 * 
	 * HashMap :   "id"->int
	 */
	private void updateDecacher(ObjetCollision objC) {
		HashMap<String, Integer> send = new HashMap<>();
		send.put("decacher", objC.getId());
		
		setChanged();
		notifyObservers(send);
	}
	
	// ------------------------------------------- Methodes
	public void moveObjet(ObjetCollision objC, Direction direction){
		Rectangle hitBox = objC.getHitBox();
		int vitesse = joueur.getVitesse();
		
		if (direction == Direction.EST) {
			hitBox.x += vitesse;
		} else if (direction == Direction.OUEST) {
			hitBox.x -= vitesse;
		} else if (direction == Direction.NORD) {
			hitBox.y -= vitesse;
		} else if (direction == Direction.SUD) {
			hitBox.y += vitesse;
		}
		
		updateMove(objC);
	}
	/**
	 * Permet de bouger le joueur dans une direction.
	 * @param direction
	 */
	public void moveJoueur(Direction direction){
		joueur.deplacer(direction);
		updateMove(joueur);
	}

	/**
	 * Retourne une liste des objets qui seront en collision avec le joueur si
	 * le joueur se déplace dans la direction donnée en parametre.
	 * @param direction
	 * @return l'objet en collision, null si l'objet n'est pas en collision
	 */
	public  List<ObjetCollision> listObjCollisionJ(Direction direction){
		return listObjCollision(joueur, direction);
	}
	
	/**
	 * Retourne une liste des objets qui seront en collision avec l'objet si
	 * l'objet se déplace dans la direction donnée en parametre.
	 * @param objColl
	 * @param direction
	 * @return
	 */
	public List<ObjetCollision> listObjCollision(ObjetCollision objColl, Direction direction){
		if( direction == null || Direction.NONE.equals(direction)){
			return null;
		}
		
		int vit = joueur.getVitesse();
		Rectangle hitBoxObjColl =  objColl.getHitBox();
		Rectangle hitBox = new Rectangle(hitBoxObjColl.x, hitBoxObjColl.y + hitBoxObjColl.height/2,
				hitBoxObjColl.width, hitBoxObjColl.height/2);
		if(Direction.NORD.equals(direction)){
			hitBox.setLocation(hitBox.x, hitBox.y - vit);
		}
		else if(Direction.SUD.equals(direction)){
			hitBox.setLocation(hitBox.x, hitBox.y + vit );
		}
		else if(Direction.EST.equals(direction)){
			hitBox.setLocation(hitBox.x + vit, hitBox.y);
		}
		else if(Direction.OUEST.equals(direction)){
			hitBox.setLocation(hitBox.x - vit, hitBox.y);
		}
		
		getCarteCourante().removeObjCollision(joueur);
		objColl.setHitBox(hitBox);
		List<ObjetCollision> listObj =  getCarteCourante().estEnCollision(objColl);
		
		objColl.setHitBox(hitBoxObjColl);
		getCarteCourante().addObjCollision(joueur);
		return listObj;
	}
	
	/**
	 * Permet de reset le jeu
	 */
	public void reset(){
		Iterator<ObjetCollision> i = listReset.keySet().iterator();
		
		while(i.hasNext()){
			ObjetCollision obj = i.next();
			Point point = listReset.get(obj);
			obj.setPosition(point);
			
			if( obj.getNomType().equals(Type.Cle.name()) ){
				Carte carte = getCarteCourante();
				
				if( !carte.containObjCollision(obj) ){
					getCarteCourante().addObjCollision(obj);
				}
				
				updateDecacher(obj);
			}
			else{
				updateMove(obj);
			}
		}
		
		joueur.viderInventaire();
	}
	
	/**
	 * Permet de passer au niveau suivant.
	 * @return false si le jeu est fini.
	 */
	public boolean nextLevel(){
		level++;
		if( !listCarte.containsKey(level) ){
			return false;
		}
		
		joueur = (Joueur) getCarteCourante().searchFirstType(Type.Joueur);
		
		//Reset
		listReset.clear();
		saveReset();
		
		return true;
	}
	
	/**
	 * Permet de sauvegarder la position de chaque objets touchables (cles, blocs, etc...) 
	 * de la carte courante.
	 */
	private void saveReset(){
		List<ObjetCollision> listObj = getCarteCourante().getObjetTouchable();
		
		for( ObjetCollision objCol :  listObj ){
			listReset.put(objCol, objCol.getPosition());
		}
		
		listReset.put(joueur, joueur.getPosition());
	}
	
	
	/**
	 * Permet de supprimer un objet sur la care courante.
	 * @param obj
	 */
	public void removeObjet(Objet obj){
		Carte carteCourante = getCarteCourante();
		carteCourante.removeObjCollision(obj);
		updateCacher(obj);
	}
	
	// ------------------------------------------- GETTER
	
	/**
	 * Permet de récupérer les objets qui sont utilisable part le joueur (cles, bloc, etc..), 
	 * sur  la carte courante.
	 * @return
	 */
	public List<ObjetCollision> getObjetTouchable(){
		return getCarteCourante().getObjetTouchable();
	}
	
	/**
	 * Permet de récupérer l'indice du niveau actuel.
	 * @return
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * Renvoie le nom du joueur.
	 * @return
	 */
	public String getNomJoueur(){
		return nomJoueur;
	}
	
	/**
	 * Renvoie le joueur.
	 * @return
	 */
	public Joueur getJoueur(){
		return joueur;
	}
	
	/**
	 * Retourne la carte courante.
	 * @return
	 */
	public Carte getCarteCourante(){
		return listCarte.get(level);
	}
}
