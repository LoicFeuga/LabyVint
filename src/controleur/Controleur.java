package controleur;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jeu.Parser;
import model.Carte;
import model.Moteur;
import model.objet.ObjetCollision;
import model.objet.ObjetCollision.Type;
import vue.CarteVue;
import vue.Mur;
import vue.PanelImage;

/**
 * Le controleur
 * 
 * @author  Feuga Loïc, Nicoletti Sébastien,
 */
public class Controleur {
	
	private static final String PATH_JSON = "./data/cartes/";
	private static final String CHEMIN_IMAGE_JOUEUR = "data/image/joueur.png";
	private static final String CHEMIN_IMAGE_PORTE = "data/image/door.png";
	private static final String NOM = "NOM";
	
	//CONTROLEUR
	private ListenerTouche touche;
	
	//VUE
	private JFrame fenetre;
	private HashMap<Integer, CarteVue> listCarteVue;
	
	//MOTEUR
	private Moteur moteur;

	public Controleur(String nomJoueur) {	
		init();
		nextLevel();
	}
	
	private void init(){
		listCarteVue = new HashMap<>();
		HashMap<Integer, Carte> listCarteMoteur = new HashMap<>();
		
		File dossierCarte = new File(PATH_JSON);
		for( File fichier : dossierCarte.listFiles() ){
			int id = Integer.parseInt(fichier.getName());
			HashMap<String, Object> jsonHashMap = initJson(fichier.getPath());
			
			ArrayList<ObjetCollision> objCollision = initObjetCollision(jsonHashMap);
			listCarteMoteur.put(id, new Carte(objCollision));
			listCarteVue.put(id, new CarteVue(toListPanel(objCollision)) ); 
		}
		
		initFrame();
		moteur = new Moteur(listCarteMoteur, NOM);
	}

	/**
	 * Permet d'initialiser la fenetre et la carte
	 * 
	 */
	private void initFrame() {
		//Frame
		fenetre = new JFrame("LabyVint");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fenetre.setSize((int)(dim.getWidth()), (int)(dim.getHeight()));
		fenetre.setLocationRelativeTo(null);
		
		//listener
		touche = new ListenerTouche(this);
		fenetre.addKeyListener(touche);
		
		//init
		fenetre.setVisible(true);
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
			System.err.println("ERROR: Controleur.initJson | chargement du fichier:" + chemin );
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
	private ArrayList<ObjetCollision> initObjetCollision(HashMap<String, Object> hm){
		if( hm == null || hm.isEmpty() ){
			return null;
		}
		
		ArrayList<ObjetCollision> listObj = new ArrayList<>();
		List<HashMap<String, Object>> carte = (List<HashMap<String, Object>>) hm.get("carte");
		
		for(int i = 0; i < carte.size(); i++){
			HashMap<String, Object> descObj = carte.get(i);
			ObjetCollision obj = ObjetCollision.createObjetCollision(descObj);
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
			if( Type.Mur.name().equals(obj.getNomType()) ){
				listPanel.put(obj.getId(), new Mur(obj.getHitBox()) );
			}
			else if( Type.Joueur.name().equals(obj.getNomType()) ){
				listPanel.put(obj.getId(), 
						new PanelImage(CHEMIN_IMAGE_JOUEUR, obj.getHitBox()));
			}
			else if(  Type.Porte.name().equals(obj.getNomType()) ){
				listPanel.put(obj.getId(), 
						new PanelImage(CHEMIN_IMAGE_PORTE, obj.getHitBox()) );
			}
			
		}
		
		return listPanel;
	}
	
	/**
	 * Permet de passer au niveau suivant.
	 */
	public void nextLevel(){
		moteur.deleteObserver(listCarteVue.get(moteur.getLevel()));
		if( moteur.nextLevel() ){
			CarteVue carte = listCarteVue.get(moteur.getLevel());
			carte.setZOrder(moteur.getJoueur().getId(), 1);
			fenetre.setContentPane(carte);
			moteur.addObserver(carte);
			fenetre.validate();
		}
		else{
			System.out.println("Le jeu est fini...");
			fenetre.dispose();
			touche.arreter();
			System.exit(0);
		}
	}
	
	// ------------------------------------------- GETTER
	
	public Moteur getMoteur(){
		return moteur;
	}
}