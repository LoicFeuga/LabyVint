package controleur;

import javax.swing.JFrame;

import model.Moteur;
import model.parser.ParserJSON;
import vue.Vue;

public class Controleur {
	private JFrame fenetre;
	private Vue vue;
	private ParserJSON parserJSON;

	public Moteur moteur;

	public void Controller() {
		 moteur = new Moteur();
	}

	public void initVue() {
		throw new UnsupportedOperationException();
	}

	public void initMoteur() {
		throw new UnsupportedOperationException();
	}
}