package model;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Observable;

import model.objet.ObjetCollision.Type;
import model.personne.Joueur;

public class Moteur extends Observable {

	private String nomJoueur;
	private Joueur joueur;
	private HashMap<Integer, Carte> listCarte;
	private int level;

	public Moteur(HashMap<Integer, Carte> listCarteMoteur, String nomJoueur) {
		listCarte = listCarteMoteur;
		this.nomJoueur = nomJoueur;
	}


	/**
	 * Permet de mettre a jour la vue.
	 * 
	 * HashMap : "deplacer"->Point
	 *                 "id"->int
	 */
	private void updateMove() {
		HashMap<String, HashMap<String, Object>> send = new HashMap<>();
		HashMap<String, Object> description = new HashMap<>();
			
		description.put("point", joueur.getDefaultPosition());
		description.put("id",joueur.getId());
		
		send.put("deplacer", description);
		
		setChanged();
		notifyObservers(send);
	}
	
	/**
	 * Permet de bouger le joueur dans une direction.
	 * @param direction
	 */
	public void moveJoueur(Direction direction){
		
		if( joueurEstDeplacable(direction) ){
			joueur.deplacer(direction);
			updateMove();
		}
	}

	/**
	 * Permet de savoir sur le joueur est déplacable
	 * @param direction
	 * @return
	 */
	private boolean joueurEstDeplacable(Direction direction){
		if( direction == null || Direction.NONE.equals(direction)){
			return false;
		}
		
		int vit = joueur.getVitesse();
		Rectangle hitBoxJoueur =  joueur.getHitBox();
		Rectangle hitBox = new Rectangle(hitBoxJoueur.x, hitBoxJoueur.y + hitBoxJoueur.height/2,
				hitBoxJoueur.width, hitBoxJoueur.height/2);
		if(Direction.NEST.equals(direction)){
			hitBox.setLocation(hitBox.x + vit, hitBox.y - vit);
		}
		else if(Direction.NOUEST.equals(direction)){
			hitBox.setLocation(hitBox.x -vit, hitBox.y - vit );
		}
		else if(Direction.SEST.equals(direction)){
			hitBox.setLocation(hitBox.x + vit, hitBox.y + vit);
		}
		else if(Direction.SOUEST.equals(direction)){
			hitBox.setLocation(hitBox.x - vit, hitBox.y + vit);
		}
		else if(Direction.NORD.equals(direction)){
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
		
		joueur.setHitBox(hitBox);
		boolean r =  getCarteCourante().estEnCollision(joueur);
		joueur.setHitBox(hitBoxJoueur);
		return r;
	}
	
	/**
	 * Retourne la carte courante.
	 * @return
	 */
	public Carte getCarteCourante(){
		return listCarte.get(level);
	}
	
	/**
	 * Permet de passer au niveau suivant.
	 * @return false si le jeu est fini.
	 */
	public boolean nextLevel(){
		level++;
		
		if(  !listCarte.containsKey(level)){
			return false;
		}
		
		joueur = (Joueur) getCarteCourante().searchFirstType(Type.Joueur);
		return true;
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
	
	public Joueur getJoueur(){
		return joueur;
	}
}