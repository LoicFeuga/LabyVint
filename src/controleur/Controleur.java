package controleur;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jeu.Parser;
import model.Direction;
import model.Moteur;
import model.objet.ObjetCollision;
import model.objet.ObjetCollision.Type;
import model.personne.Joueur;
import vue.CarteVue;
import vue.Mur;
import vue.PanelImage;

/**
 * Le controleur
 * 
 * @author  Feuga Loïc, Nicoletti Sébastien,
 */
public class Controleur {
	
	private static final String CHEMIN_MAP = "data/image/map/Map.json";
	private static final String CHEMIN_IMAGE_JOUEUR = "data/image/joueur3.png";
	
	//CONTROLEUR
	private ListenerTouche touche;
	
	//VUE
	private JFrame fenetre;
	private CarteVue vue;
	
	//MOTEUR
	private Moteur moteur;
	private Joueur joueur;

	public Controleur(String nomJoueur) {
		
		HashMap<String, Object> jsonHashMap = initJson(CHEMIN_MAP);
		List<ObjetCollision> listObj = initObjetCollision(jsonHashMap);
		HashMap<Integer, JPanel> listPanel = toListPanel(listObj); 
		
		// init
		initMoteur(listObj, nomJoueur);
		initVue(listPanel);

	}

	public void moveLeft() {
		moteur.getJoueur().deplacer(Direction.OUEST);
		moteur.update();
	}

	public void moveRight() {
		moteur.getJoueur().deplacer(Direction.EST);
		moteur.update();
	}

	public void moveUp() {
		moteur.getJoueur().deplacer(Direction.NORD);
		moteur.update();
	}

	public void moveDown() {
		moteur.getJoueur().deplacer(Direction.SUD);
		moteur.update();
	}

	/**
	 * Permet d'initialiser la fenetre et la carte
	 * 
	 */
	private void initVue(HashMap<Integer, JPanel> listPanel) {
		//Frame
		fenetre = new JFrame("LabyVint");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fenetre.setSize((int)(dim.getWidth()), (int)(dim.getHeight()));
		fenetre.setLocationRelativeTo(null);
		
		//Vue
		vue = new CarteVue(listPanel);
		moteur.addObserver(vue);
		fenetre.setContentPane(vue);
		
		//listener
		touche = new ListenerTouche(this);
		fenetre.addKeyListener(touche);
		
		//init
		fenetre.setVisible(true);
	}

	/**
	 * Permet d'initialiser le moteur.
	 * 
	 * @param nomJoueur
	 * @param listObj
	 */
	private void initMoteur(List<ObjetCollision> listObj, String nomJoueur) {
		joueur.setPseudo(nomJoueur);
		moteur = new Moteur((ArrayList<ObjetCollision>)listObj, joueur);
	}
	
	/**
	 * 
	 * Retourne un HashMap decrivant la carte qui se situe dans 
	 * le fichier dont le chemin est "chemin".
	 * 
	 * @return Hashmap
	 * Hashmap: "nbCaseLargeur" -> long
	 * 			"nbCaseHauteur" -> long
	 *          "carte" -> List<Hashmap>
	 *          	- le Hashmap: -> "x" -> long
	 *          					 "y" -> long
	 *          					 "largeur" -> long
	 *          					 "hauteur" -> long
	 *          					 "type" -> String
	 * 
	 * 
	 */
	private HashMap<String, Object> initJson(String chemin){
		String json = VFichier.chargementFichierTexte(chemin);
		
		if( json == null || json.isEmpty() ){
			System.err.println("ERROR: Controleur.initJson | chargement du fichier:" + CHEMIN_MAP );
		}
		
		return Parser.jsonDecoding(json);
	}
	
	/**
	 * Permet de récupérer la liste des objets collisions
	 * du hashmap qui décrit la carte et initialise 
	 * le joueur.
	 * 
	 * @param hm
	 * @return  List<ObjetCollision>
	 */
	@SuppressWarnings("unchecked")
	private List<ObjetCollision> initObjetCollision(HashMap<String, Object> hm){
		if( hm == null || hm.isEmpty() ){
			return null;
		}
		
		List<ObjetCollision> listObj = new ArrayList<>();
		List<HashMap<String, Object>> carte = (List<HashMap<String, Object>>) hm.get("carte");
		
		for(int i = 0; i < carte.size(); i++){
			HashMap<String, Object> descObj = carte.get(i);
			ObjetCollision obj = ObjetCollision.createObjetCollision(descObj);
			
			if( Type.Joueur.name().equals(obj.getNom()) ){
				joueur = (Joueur) obj;
			}
			
			listObj.add(obj);
		}
		
		return listObj;
	}
	
	/**
	 * Permet de  transformer la list des objets collisions
	 * en une liste de Panel.
	 * 
	 * List de panel : HashMap<Id_Objet_Collision, JPanel> 
	 * 
	 * @param listObj
	 * @return HashMap<Integer, JPanel>
	 */
	private HashMap<Integer, JPanel> toListPanel(List<ObjetCollision> listObj){
		HashMap<Integer, JPanel> listPanel = new HashMap<>();
		
		for( ObjetCollision obj : listObj){
			if( Type.Mur.name().equals(obj.getNom()) ){
				listPanel.put(obj.getId(), new Mur(obj.getHitBox()) );
			}
			else if( Type.Joueur.name().equals(obj.getNom()) ){
				listPanel.put(obj.getId(), 
						new PanelImage(CHEMIN_IMAGE_JOUEUR, obj.getHitBox()));
			}
			
		}
		
		return listPanel;
	}
}