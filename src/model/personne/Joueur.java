package model.personne;

import java.awt.Rectangle;
import java.util.ArrayList;

import model.objet.Objet;

public class Joueur extends Personne {
	/**
	 * listeObjet
	 */
	private ArrayList<Objet> listeObjet;
	
	/**
	 * Constructeur de Joueur
	 * @param vitesse de déplacement [0;10] 0 pour immobile, 5 normal
	 * @param nomType du joueur
	 */
	public Joueur(int vitesse, Rectangle hitBox) {
		super(vitesse,hitBox);
		listeObjet = new ArrayList<Objet>();
		super.nomType = Type.Joueur.name();
	}
	
	public void viderInventaire(){
		listeObjet.clear();
	}

	/**
	 * ajout d'objet
	 * @param objet
	 */
	public void ramasser(Objet objet) {
		listeObjet.add(objet);
		objet.onRamasser();
	}

	
}