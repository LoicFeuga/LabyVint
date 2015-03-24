package model;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

import model.objet.ObjetCollision;
import model.personne.Joueur;
import model.personne.Personne;
import controleur.Controleur;

public class Moteur extends Observable {

	//private ArrayList<Personne> listePersonne = new ArrayList<Personne>();
	private Joueur joueur;
	private Carte carte;

	public Moteur(ArrayList<ObjetCollision> list, Joueur joueur) {
		carte = new Carte(list);
		//listePersonne = new ArrayList();
		this.joueur = joueur;
		//listePersonne.add(j);
	}

	public void update() {
		throw new UnsupportedOperationException();
	}
}