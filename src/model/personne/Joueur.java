package model.personne;

import java.awt.Rectangle;
import java.util.ArrayList;

import model.Direction;
import model.objet.Objet;

public class Joueur extends Personne {

	/**
	 * listeObjet
	 */
	private ArrayList<Objet> listeObjet;
	
	/**
	 * Permet de garder en mémoire la dernière direction choisit
	 */
	private Direction derniereDirection;

	/**
	 * Constructeur de Joueur
	 * @param vitesse de déplacement [0;10] 0 pour immobile, 5 normal
	 * @param nom du joueur
	 */
	public Joueur(int vitesse, String nom, Rectangle hitBox) {
		super(vitesse, nom,hitBox);
		listeObjet = new ArrayList<Objet>();
		derniereDirection = Direction.NONE;
	}

	/**
	 * Permet de se déplacer de direction 
	 * @param direction
	 */
	public void deplacer(Direction direction) {
		derniereDirection = direction;
		super.deplacer(direction);
	}
	
	/**
	 * Permet d'annuler le dernier déplacement en déplacement  dans 
	 *   le sens inverse
	 */
	public void annulerDeplacement(){
		if(derniereDirection == Direction.EST){
			deplacer(Direction.EST);
		}else if(derniereDirection == Direction.OUEST){
			deplacer(Direction.OUEST);
		}else if(derniereDirection == Direction.NORD){
			deplacer(Direction.SUD);
		}else if(derniereDirection == Direction.SUD){
			deplacer(Direction.NORD);
		}
	}

	/**
	 * ajout d'objet
	 * @param objet
	 */
	public void ajoutObjet(Objet objet) {
		throw new UnsupportedOperationException();
	}

	/**
	 * utilisation d'un objet
	 * @param objet
	 */
	public void utiliser(Objet objet) {
		throw new UnsupportedOperationException();
	}
	
	public Direction getDirection(){
		return derniereDirection;
	}
}