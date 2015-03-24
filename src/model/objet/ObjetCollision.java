package model.objet;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 * Class ObjetCollision, 
 * Elle représente un objet qui peut entrer en collision avec un autre 
 *   objet, en lui attribuant une hitBox en rectangle.
 * 
 * 
 * @author Loïc Feuga
 * 
 * @version V0.0.1 Nicoletti Sébastien
 * 	- Ajout de la méthode createObjetCollision.
 *  - Ajout de l'enum Type.
 *
 */
public class ObjetCollision {
	/**
	 * La hitBox de l'objet, un getter récupérera la position
	 */
	protected Rectangle hitBox;
	/**
	 * Identifiant
	 */
	private int id;
	/**
	 * Nombre total d'objet 
	 */
	private static int nbTotalObjet = 0;

	protected String nom;
	
	/**
	 * Constructeur par défaut
	 */
	public ObjetCollision(Rectangle hitBox,String nom) {
		id = nbTotalObjet;
		incrementNbTotal();
		this.hitBox = hitBox;	
		this.nom = nom;
	}

	public ObjetCollision(Rectangle hitBox) {
		id = nbTotalObjet;
		incrementNbTotal();
		this.hitBox = hitBox;	
		this.nom = Type.ObjetCollision.name();
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
	
	/**
	 * Permet de récupérer le point en haut à gauche de la hitBox
	 * @return
	 */
	public Point getDefaultPosition(){		
		return new Point(hitBox.x, hitBox.y);
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
	
	
	public String getNom(){
		return nom;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	/**
	 * 
	 * Permet de créer un objet collision avec un hashmap de description.
	 * 
	 * le Hashmap: -> "x" -> long
	 *          	  "y" -> long
	 *          	  "largeur" -> long
	 *          	  "hauteur" -> long
	 *          	  "type" -> String
	 * 
	 * @param descObj
	 * @return ObjetCollision
	 */
	public static ObjetCollision createObjetCollision(HashMap<String, Object> descObj){
		if( descObj == null || descObj.isEmpty()){
			return null;
		}
		
		String type = (String)descObj.get("type");
		long x = 0, y = 0, largeur = 0, hauteur = 0;
		if( descObj.containsKey("x") ) x = (long)descObj.get("x");
		if( descObj.containsKey("y") ) y = (long)descObj.get("y");
		if( descObj.containsKey("largeur") )largeur = (long)descObj.get("largeur");
		if( descObj.containsKey("hauteur") )hauteur = (long)descObj.get("hauteur");
		
		Rectangle rect = new Rectangle( (int)x, (int)y, (int)largeur, (int)hauteur);
		
		if( Type.Bloc.name().equals(type) )return new Bloc(rect);
		if( Type.Cle.name().equals(type) )return new Cle(rect);
		if( Type.Mur.name().equals(type) )return new Mur(rect);
		
		return new ObjetCollision(rect);
	}
	
	////////////////////////////////////ENUM
	public enum Type{
		ObjetCollision, Bloc, Cle, Mur;
	}
}