package model.personne;

import java.awt.Rectangle;

import model.Direction;
import model.implement.IDeplacable;
import model.objet.ObjetCollision;

public class Personne extends ObjetCollision implements IDeplacable {
	
	public static final int DEFAULT_VITESSE = 5;
	
	/**
	 * vitesse de déplacement
	 */
	private int vitesse;
	/**
	 * nom
	 */
	private String pseudo;

	/**
	 * Constructeur de personne
	 */
	public Personne(int vitesse, String pseudo, Rectangle hitBox) {
		super(hitBox,"Joueur");
		this.vitesse = vitesse*3;
		this.pseudo = pseudo;
		
	}

	/**
	 * getteur de vitesse
	 * 
	 * @return vitesse
	 */
	public int getVitesse() {
		return vitesse;
	}

	/**
	 * setteur de vitesse
	 * 
	 * @param vitesse
	 */

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * getteur de nom
	 * 
	 * @return nom
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * setteur de nom
	 * 
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public void deplacer(Direction direction) {
		
		if(direction == Direction.EST){
			hitBox.x+= vitesse;
		}else if(direction == Direction.OUEST){

			hitBox.x-= vitesse;
		}else if(direction == Direction.NORD){
			hitBox.y-= vitesse;
		}else if(direction == Direction.SUD){
			hitBox.y+= vitesse;
		}
	}

}