package controleur;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Moteur;
import model.objet.ObjetCollision;
import model.parser.ParserJSON;
import model.personne.Joueur;
import vue.Vue;

public class Controleur {
	private JFrame fenetre;
	private Vue vue;
	private ParserJSON parserJSON;

	public Moteur moteur;

	public void Controller() {
		//parser de la carte;
		ArrayList<ObjetCollision> list = new ArrayList();
		Joueur joueur = new Joueur(1,"c",new Rectangle());
		moteur = new Moteur(list,joueur);
	}

	public void initVue() {
		throw new UnsupportedOperationException();
	}

	public void initMoteur() {
		throw new UnsupportedOperationException();
	}
}