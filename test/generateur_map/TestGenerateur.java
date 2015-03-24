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

/**
 * The Class TestGenerateur.
 */
public class TestGenerateur {

	/** The image. */
	private BufferedImage image = null;
	
	/** The nb case largeur. */
	private int nbCaseLargeur;
	
	/** The nb case hauteur. */
	private int nbCaseHauteur;

	/**
	 * Instantiates a new test generateur.
	 */
	public TestGenerateur() {
		initImage();
		List<ObjetCollision> listObjetC = initListCollision();
		String json = toJSON(listObjetC);
		System.out.println(json);

		VFichier.sauvegardeFichierTexte(CreerImageGrille.NOM + ".json", json);
	}

	/**
	 * Inits the image.
	 * 
	 * Permet de chercher l'image de test.
	 * 
	 */
	private void initImage() {
		try {
			image = ImageIO.read(new File(CreerImageGrille.NOM + "."
					+ CreerImageGrille.FORMAT));
		} catch (IOException e) {
			System.out.println("Erreur chargement image...");
			System.exit(1);
		}

		nbCaseLargeur = image.getWidth() / CreerImageGrille.TAILLE_CARRE;
		nbCaseHauteur = image.getHeight() / CreerImageGrille.TAILLE_CARRE;
	}

	/**
	 * Inits the list collision.
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
	 * To json.
	 * 
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
			descObj.put("type", obj.getNom());

			objCollisions.add(descObj);
		}

		map.put("carte", objCollisions);
		map.put("nbCaseLargeur", nbCaseLargeur);
		map.put("nbCaseHauteur", nbCaseHauteur);
		map.put("tailleCase", CreerImageGrille.TAILLE_CARRE);

		return Parser.jsonEncoding(map);
	}

	/**
	 * Color to object collision.
	 * 
	 * Permet de traduire le code couleur en un objet collision.
	 *
	 * @param couleur the couleur
	 * @param rect the rect
	 * @return the objet collision
	 */
	private ObjetCollision colorToObjectCollision(Color couleur, Rectangle rect) {
		if (Type.MUR.getCouleur().equals(couleur)) {
			return new Mur(rect);
		}

		return null;
	}

	/**
	 * The Enum Type.
	 */
	public enum Type {
		
		/** The mur. */
		MUR(Color.black);

		/** The couleur. */
		private Color couleur;

		/**
		 * Instantiates a new type.
		 *
		 * @param couleur the couleur
		 */
		Type(Color couleur) {
			this.couleur = couleur;
		}

		/**
		 * Gets the couleur.
		 *
		 * @return the couleur
		 */
		public Color getCouleur() {
			return couleur;
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new TestGenerateur();
	}

}
