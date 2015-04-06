package generateur_map;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import controleur.VFichier;
import jeu.Parser;
import model.objet.Mur;
import model.objet.ObjetCollision;
import model.objet.Porte;
import model.personne.Joueur;

/**
 * la Class TestGenerateur.
 * 
 * Elle permet de créer un fichier json à partir d'une grille
 * créer par la class "CreerImageGrille"
 * 
 * @author Nicoletti Sébastien
 */
public final class TestGenerateur {
	
	private static final String PATH_IMAGE = "./data/image/cartes/";
	private static final String PATH_JSON = "./data/cartes/";

	private BufferedImage image = null;
	private int nbCaseLargeur;
	private int nbCaseHauteur;

	public TestGenerateur() {
		File dossierImage = new File(PATH_IMAGE);
		for (File fichierImage : dossierImage.listFiles()){
			initImage(fichierImage);
			List<ObjetCollision> listObjetC = initListCollision();
			String nom = fichierImage.getName();
			nom = nom.substring(0,nom.lastIndexOf("."));
			VFichier.sauvegardeFichierTexte(PATH_JSON + nom, toJSON(listObjetC));
		}
	}

	/**
	 * 
	 * Permet de chercher l'image de test.
	 * 
	 */
	private void initImage(File fichier) {
		try {
			image = ImageIO.read(fichier);
		} catch (IOException e) {
			System.out.println("Erreur chargement image...");
			System.exit(1);
		}

		nbCaseLargeur = image.getWidth() / CreerImageGrille.TAILLE_CARRE;
		nbCaseHauteur = image.getHeight() / CreerImageGrille.TAILLE_CARRE;
	}

	/**
	 * 
	 * Permet de créer la liste des objets collisions.
	 *
	 * @return the list
	 */
	private List<ObjetCollision> initListCollision() {
		List<ObjetCollision> listObjetC = new ArrayList<ObjetCollision>();

		for (int y = 0; y < nbCaseHauteur; y++) {
			for (int x = 0; x < nbCaseLargeur; x++) {
				int xLocation = (x * CreerImageGrille.TAILLE_CARRE);
				int yLocation = (y * CreerImageGrille.TAILLE_CARRE);
				int xRGB = xLocation + CreerImageGrille.TAILLE_CARRE / 2;
				int yRGB = yLocation + CreerImageGrille.TAILLE_CARRE / 2;

				int rgb = image.getRGB(xRGB, yRGB);
				Color couleur = new Color(rgb);

				Rectangle rect = new Rectangle(xLocation, yLocation,
						CreerImageGrille.TAILLE_CARRE,
						CreerImageGrille.TAILLE_CARRE);

				ObjetCollision obj = colorToObjectCollision(couleur, rect);
				if (obj != null) {
					listObjetC.add(obj);
				}
			}
		}

		return listObjetC;
	}

	/**
	 * Permet de parser nos données en JSON
	 * 
	 * Hashmap: "nbCaseLargeur" -> long
	 * 			"nbCaseHauteur" -> long
	 *          "carte" -> List<Hashmap>
	 *          	- le Hashmap: -> "x" -> long
	 *          					 "y" -> long
	 *          					 "largeur" -> long
	 *          					 "hauteur" -> long
	 *          					 "type" -> String
	 *
	 * @param listObjetC the list objet c
	 * @return the string
	 */
	private String toJSON(List<ObjetCollision> listObjetC) {
		HashMap<String, Object> map = new HashMap<>();
		List<HashMap<String, Object>> objCollisions = new ArrayList<>();

		for (ObjetCollision obj : listObjetC) {
			HashMap<String, Object> descObj = new HashMap<>();
			descObj.put("x", obj.getHitBox().x);
			descObj.put("y", obj.getHitBox().y);
			descObj.put("largeur", obj.getHitBox().width);
			descObj.put("hauteur", obj.getHitBox().height);
			descObj.put("type", obj.getNomType());

			objCollisions.add(descObj);
		}

		map.put("carte", objCollisions);
		map.put("nbCaseLargeur", nbCaseLargeur);
		map.put("nbCaseHauteur", nbCaseHauteur);
		map.put("tailleCase", CreerImageGrille.TAILLE_CARRE);

		return Parser.jsonEncoding(map);
	}

	/**
	 * 
	 * Permet de traduire le code couleur en un objet collision.
	 *
	 * @param couleur the couleur
	 * @param rect the rect
	 * @return the objet collision
	 */
	private ObjetCollision colorToObjectCollision(Color couleur, Rectangle rect) {
		if (Type.Mur.getCouleur().equals(couleur)) return new Mur(rect);
		else if( Type.Joueur.getCouleur().equals(couleur) ) return new Joueur(5, rect);
		else if( Type.Porte.getCouleur().equals(couleur) ) return new Porte(rect); 

		return null;
	}

	/**
	 * The Enum Type.
	 */
	public enum Type {
		
		Mur(Color.black),
		Joueur(Color.blue),
		Porte(Color.green);
		
		private Color couleur;

		Type(Color couleur) {
			this.couleur = couleur;
		}

		public Color getCouleur() {
			return couleur;
		}
	}

	public static void main(String[] args) {
		new TestGenerateur();
	}

}
