package model.personne;

import model.Moteur;
import model.implement.IDeplacable;
import model.objet.ObjetCollision;

public class Personne extends ObjetCollision implements IDeplacable {
	private int _vitesse;
	private String _nom;
	public Moteur _unnamed_Moteur_;

	public int getVitesse() {
		return this._vitesse;
	}

	public void setVitesse(int aVitesse) {
		this._vitesse = aVitesse;
	}

	public String getNom() {
		return this._nom;
	}

	public void setNom(String aNom) {
		this._nom = aNom;
	}

	public void deplacer(Object aDirection direction) {
		throw new UnsupportedOperationException();
	}
}