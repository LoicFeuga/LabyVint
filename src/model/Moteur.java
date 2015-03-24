package model;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

import model.personne.Joueur;
import model.personne.Personne;
import controleur.Controleur;

public class Moteur extends Observable {

	public ArrayList<Personne> listePersonne = new ArrayList<Personne>();
	public Carte carte;

	public Moteur() {
		carte = new Carte();
		listePersonne = new ArrayList();
		Joueur joueur = new Joueur(5,"Joueur");
	}

	public void update() {
		throw new UnsupportedOperationException();
	}
}