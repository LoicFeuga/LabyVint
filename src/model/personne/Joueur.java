package model.personne;

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
	 * @param vitesse
	 * @param nom
	 */
	public Joueur(int vitesse, String nom) {
		super(vitesse, nom);
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