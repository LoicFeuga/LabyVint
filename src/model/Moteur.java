package model;
import java.util.Observable;
import java.util.Vector;

import model.personne.Personne;
import controleur.Controleur;

public class Moteur extends Observable {
	public Controleur _unnamed_Controleur_;
	public Vector<Personne> _listePersonne = new Vector<Personne>();
	public Carte _unnamed_Carte_;

	public Moteur() {
		throw new UnsupportedOperationException();
	}

	public void update() {
		throw new UnsupportedOperationException();
	}
}