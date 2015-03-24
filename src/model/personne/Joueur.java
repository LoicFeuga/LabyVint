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
	 * Constructeur de Joueur
	 * @param vitesse de déplacement [0;10] 0 pour immobile, 5 normal
	 * @param nom du joueur
	 */
	public Joueur(int vitesse, String nom, Rectangle hitBox) {
		super(vitesse, nom,hitBox);
		listeObjet = new ArrayList<Objet>();
	}

	/**
	 * Deplacement
	 * @param direction
	 */
	public void deplacer(Direction direction) {
		throw new UnsupportedOperationException();
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
}