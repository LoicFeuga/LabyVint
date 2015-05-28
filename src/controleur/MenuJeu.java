package controleur;
/*  Classe de menu de lancement de l'exemple de jeu.
 *  Cette classe hérite de la classe abstraite MenuAbstrait en définissant les méthodes :
 *     - nomOptions qui renvoie la liste des options possibles pour le menu 
 *     - lancerOption qui associe une action à chaque option du menu
 *     - wavAccueil() qui renvoie le nom du fichier wav lu lors de l'accueil dans le menu
 *     - wavAide() qui renvoie le nom du fichier wav lu lors de l'activation de la touche F1
 */

 

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import devintAPI.MenuAbstrait;

public class MenuJeu extends MenuAbstrait {

	private static final long serialVersionUID = 1L;

	/** constructeur
	 * @param title : le nom du jeu 
	 */
	public MenuJeu(String title) {
		super(title);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				System.exit(1);
			}
		});
		
	}

	/** renvoie le nom des options du menu
     * vous pouvez définir autant d'options que vous voulez
     **/
	protected String[] nomOptions() {
		String[] noms = {"Jeu","Création", "Quitter"};
		return noms;
	}

	/** lance l'action associée au bouton
	 * la numérotation est celle du tableau renvoyé par nomOption
	 */
	protected void lancerOption(int i) {
		switch (i){  
		case 0 : 
			setVisible(false);
			new Controleur("VJoueur", this);
			break;
		case 1 : 
			setVisible(false);
			new ControleurCreation(this);
			break;
		case 2 : System.exit(0);
		default: System.err.println("action non définie");
		}
	} 

	// renvoie le fichier wave contenant le message d'accueil
	// ces fichiers doivent être placés dans ressources/sons/
	protected  String wavAccueil() {
		return "../ressources/sons/accueil.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavRegleJeu() {
		return "../ressources/sons/accueil.wav";
	}
	
}
