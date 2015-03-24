package controleur;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import model.Direction;
import model.Moteur;
import model.objet.ObjetCollision;
import model.parser.ParserJSON;
import model.personne.Joueur;
import vue.Vue;

public class Controleur {
	private JFrame fenetre;
	private Vue vue;
	private ParserJSON parserJSON;

	private Moteur moteur;

	public Controleur() {
		//parser de la carte;
		ArrayList<ObjetCollision> list = new ArrayList();
		Joueur joueur = new Joueur(1,"c",new Rectangle());
		HashMap<Point, BufferedImage> listImage = new HashMap<>();
		
		vue = new Vue(listImage);
		moteur = new Moteur(list,joueur);
		
		ListenerTouche touche = new ListenerTouche(this);

		fenetre = new JFrame("LabyVint");
		fenetre.setSize(500,500);
		//fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.addKeyListener(touche);

		fenetre.setContentPane(vue);
		fenetre.setVisible(true);
	}
	
	public void moveLeft(){
		moteur.getJoueur().deplacer(Direction.OUEST);
		moteur.update();
	}
	
	public void moveRight(){
		moteur.getJoueur().deplacer(Direction.EST);
		moteur.update();
	}
	
	public void moveUp(){
		moteur.getJoueur().deplacer(Direction.NORD);
		moteur.update();
	}
	public void moveDown(){
		moteur.getJoueur().deplacer(Direction.SUD);
		moteur.update();
	}

	public void initVue() {
		throw new UnsupportedOperationException();
	}

	public void initMoteur() {
		throw new UnsupportedOperationException();
	}
}