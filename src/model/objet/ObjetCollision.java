package model.objet;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Class ObjetCollision, 
 * Elle représente un objet qui peut entrer en collision avec un autre 
 *   objet, en lui attribuant une hitBox en rectangle.
 * 
 * 
 * @author user
 *
 */
public class ObjetCollision {
	/**
	 * La hitBox de l'objet, un getter récupérera la position
	 */
	private Rectangle  hitBox;
	/**
	 * Identifiant
	 */
	private int id;
	/**
	 * Nombre total d'objet 
	 */
	private static int nbTotalObjet = 0;

	/**
	 * Constructeur par défaut
	 */
	public ObjetCollision() {
		id = nbTotalObjet;
		incrementNbTotal();
		
		
	}

	/**
	 * Méthode qui permet de savoir si un objet est touché par un  
	 *   rectangle passé en paramètre
	 * @param rect l'ennemis, ou le bloc 
	 * @return true si touché, false sinon
	 */
	public boolean isTouch(Rectangle rect) {
		return this.getHitBox().intersects(rect);
	}
	
	/**
	 * Méthode qui permet de savoir si un objet est touché par un  
	 *   autre objet passé en paramètre
	 * @param o1 l'objet qui contient une hitbox
	 * @return true si touché, false sinon
	 */
	public boolean isTouch(ObjetCollision o1){
		return this.getHitBox().intersects(o1.getHitBox());
	}

	/**
	 * Permet de savoir la position de l'objet par rapport à sa hitBox
	 * 
	 * @return un point position
	 */
	public Point getPosition() {
		int d1 = (int) (hitBox.getX() + (hitBox.getWidth() / 2));
		int d2 =  (int) (hitBox.getY() + (hitBox.getHeight() / 2));
		Point p1 = new Point(d1,d2);
		return p1;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int aId) {
		this.id = aId;
	}

	public int getNbTotalObjet() {
		return this.nbTotalObjet;
	}

	public void setNbTotalObjet(int aNbTotalObjet) {
		this.nbTotalObjet = aNbTotalObjet;
	}

	public void setPosition(Point aPosition) {
		//set hit box
	}
	
	/**
	 * Permet d'initialiser une hitBox
	 * @param rect
	 */
	public void setHitBox(Rectangle rect){
		this.hitBox = rect;
	}
	
	public Rectangle getHitBox(){
		return this.hitBox;
	}

	/**
	 * Augmente le nombre maximal d'objet
	 */
	public void incrementNbTotal() {
		nbTotalObjet++;
	}
}