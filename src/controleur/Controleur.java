package controleur;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import jeu.Parser;
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
		//HashMap<Point, BufferedImage> listImage = new HashMap<>();
		
		ArrayList<Image> listImage = new ArrayList<>();
		
		//json
		String jsonString = VFichier.chargementFichierTexte("data/testjson.txt");
		HashMap jsonHashMap = Parser.jsonDecoding(jsonString);
		
		//ajout image
		Image image = null;
		
		try {image = ImageIO.read(new File("data/image/mur.png"));}
		catch (IOException e) { e.printStackTrace();}
		
		listImage.add(image);
		
		//init
		vue = new Vue(listImage,jsonHashMap);
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