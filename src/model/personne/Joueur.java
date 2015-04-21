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
	 * @param nomType du joueur
	 */
	public Joueur(int vitesse, Rectangle hitBox) {
		super(vitesse,hitBox);
		listeObjet = new ArrayList<Objet>();
		derniereDirection = Direction.NONE;
		super.nomType = Type.Joueur.name();
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
			deplacer(Direction.OUEST);
		}else if(derniereDirection == Direction.OUEST){
			deplacer(Direction.EST);
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
	public void ramasser(Objet objet) {
		listeObjet.add(objet);
		objet.onRamasser();
	}

	
	public Direction getDirection(){
		return derniereDirection;
	}
}