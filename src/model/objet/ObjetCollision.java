package model.objet;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Class ObjetCollision, 
 * Elle représente un objet qui peut entrer en collision avec un autre 
 *   objet, en lui attribuant une hitBox en rectangle.
 * 
 * 
 * @author Loïc Feuga
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

	/**
	 * Méthode qui permet de savoir combien d'objetCollision
	 *   ont été créer.
	 * Attention ce n'est pas les tableaux, 
	 *   4 = 4 objet créer 
	 *  
	 * @return
	 */
	public int getNbTotalObjet() {
		return this.nbTotalObjet;
	}

	public void setNbTotalObjet(int aNbTotalObjet) {
		this.nbTotalObjet = aNbTotalObjet;
	}

	/**
	 * Déplacement de la position de la hitBox 
	 *   donc de la position du joueur, ennemis ou objet
	 *   
	 * @param aPosition
	 */
	public void setPosition(Point op) {
		Point p1 = new Point((int) hitBox.getX(),(int) hitBox.getY());
		
		int diffX = 0;
		int diffY = 0;
		//hitBox.setRect(op.getX()+(hitBox.getWidth()/2),op.getY()+(hitBox.getHeight()/2),hitBox.getWidth(), hitBox.getHeight());

		
		if(op.getX() >= p1.getX()){
			diffX = (int) ((int) (op.getX() - p1.getX()) - (hitBox.getWidth() / 2));			
			hitBox.setRect((p1.getX() + diffX) , p1.getY(),hitBox.getWidth(), hitBox.getHeight());
			p1.setLocation(p1.getX() + diffX, p1.getY());
		}else{
			diffX = (int) ((int) (p1.getX() - op.getX()) + (hitBox.getWidth() / 2)) ;
			hitBox.setRect(p1.getX() - diffX, p1.getY(),hitBox.getWidth(), hitBox.getHeight());
			p1.setLocation(p1.getX() - diffX, p1.getY());
		}

		if(op.getY() >= p1.getY()){
			diffY = (int) ((int) (op.getY() - p1.getY()) - (hitBox.getHeight() /2 ));
			hitBox.setRect(p1.getX(), p1.getY() + diffY,hitBox.getWidth(), hitBox.getHeight());
		
		}
		else{
			diffY = (int) ((int) (p1.getY() - op.getY()) + (hitBox.getWidth() / 2)) ;
			hitBox.setRect(p1.getX(), p1.getY() - diffY,hitBox.getWidth(), hitBox.getHeight());
		}
		
		
	}
	
	/**
	 * Permet d'initialiser une hitBox
	 * @param rect
	 */
	public void setHitBox(Rectangle rect){
		this.hitBox = rect;
	}
	
	/**
	 * Obligatoire avant de mettre à supprimer toutes les références
	 *  vers cette objet
	 */
	public void supprimer(){
		nbTotalObjet--;
		hitBox = null;
		id = 0;
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