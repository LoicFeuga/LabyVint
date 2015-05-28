package controleur;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.objet.Bloc;
import model.objet.Cle;
import model.objet.ObjetCollision;
import model.objet.ObjetCollision.Type;
import model.objet.Porte;
import model.personne.Joueur;
import model.personne.Personne;
import vue.Case;
import vue.CreationCarteVue;
import vue.Mur;
import vue.PanelImage;

public class ControleurCreation implements ActionListener{
	
	private static final int SIZE_CASE = 50;
		
	private static final Font FONT = new Font("Arial", Font.BOLD, 20);
	private static final String CHEMIN_IMAGE_JOUEUR = "../ressources/images/joueur.png";
	private static final String CHEMIN_IMAGE_PORTE = "../ressources/images/door.png";
	private static final String CHEMIN_IMAGE_CLE = "../ressources/images/cle.png";
	private static final String CHEMIN_IMAGE_BLOC = "../ressources/images/caisse.png";
	private static final String CHEMIN_IMAGE_MUR = "../ressources/images/mur.png";
	private static final String CHEMIN_IMAGE_GOMME = "../ressources/images/gomme.jpg";
	
	private JFrame fenetre;
	private JFrame fenParent;
	
	private CreationCarteVue vue;
	private JPopupMenu popMenu;
	
	private List<ObjetCollision> listObj ;
	
	private int nbColonne;
	private int nbLigne;
	
	private String typeAct;
	
	private ListenersCreation listeners;
	public ControleurCreation(JFrame fenParent) {
		this.fenParent = fenParent;
		listeners = new ListenersCreation(this);
		listObj = new  ArrayList<>();
		initPopMenu();
		initView();
	}
	
	private void initPopMenu(){
		popMenu = new JPopupMenu();
		JMenuItem box = new JMenuItem(Type.Bloc.name());
		box.setFont(FONT);
		popMenu.add(box);
		JMenuItem cles  = new JMenuItem(Type.Cle.name());
		cles.setFont(FONT);
		popMenu.add(cles);
		JMenuItem mur = new JMenuItem(Type.Mur.name());
		mur.setFont(FONT);
		popMenu.add(mur);
		JMenuItem perso = new JMenuItem(Type.Joueur.name());
		perso.setFont(FONT);
		popMenu.add(perso);
		JMenuItem porte = new JMenuItem(Type.Porte.name());
		porte.setFont(FONT);
		popMenu.add(porte);
		JMenuItem gomme = new JMenuItem("Effacer");
		gomme.setFont(FONT);
		popMenu.add(gomme);
		
		// ------------------------------------------- listener
		box.addActionListener(this);
		cles.addActionListener(this);
		mur.addActionListener(this);
		perso.addActionListener(this);
		porte.addActionListener(this);
		gomme.addActionListener(this);
	}
	
	/**
	 * Permet d'initialiser la vue.
	 */
	private void initView(){
		//fenetre
		fenetre = new JFrame("Création carte");
		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fenetre.setSize((int)dim.getWidth(), (int)dim.getHeight());
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.addKeyListener(listeners);
		
		fenetre.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				fenParent.setVisible(true);
				popMenu.setVisible(false);
			}
		});
		
		//CarteVue
		nbColonne = (int) (dim.getWidth()/SIZE_CASE);
		nbLigne = (int) (dim.getHeight()/SIZE_CASE);
		vue = new CreationCarteVue(nbColonne, nbLigne, SIZE_CASE, SIZE_CASE);
		vue.setMouseListener(listeners);
		
		fenetre.add(vue, BorderLayout.CENTER);
		fenetre.setVisible(true);
	}

	// ------------------------------------------- MOUSE
	
	public void echangerPanel( JPanel c ){
		JPanel panel = null;
		Point pos = c.getLocation();
		
		if( objetExiste(pos.getLocation(), typeAct) )return;
		
		if( typeAct.equals(Type.Bloc.name())){
			Rectangle rect = new Rectangle(pos.x, pos.y, SIZE_CASE, SIZE_CASE);
			panel = new PanelImage(CHEMIN_IMAGE_BLOC, rect);
			listObj.add(new Bloc(rect));
		}
		else if( typeAct.equals(Type.Cle.name())){
			Rectangle rect = new Rectangle(pos.x, pos.y, SIZE_CASE, SIZE_CASE);
			panel = new PanelImage(CHEMIN_IMAGE_CLE, rect);
			listObj.add(new Cle(rect));
		}
		else if( typeAct.equals(Type.Mur.name())){
			Rectangle rect = new Rectangle(pos.x, pos.y, SIZE_CASE, SIZE_CASE);
			panel = new Mur(rect);
			listObj.add(new model.objet.Mur(rect));
		}
		else if( typeAct.equals(Type.Joueur.name())){
			Rectangle rect = new Rectangle(pos.x, pos.y, SIZE_CASE, 2*SIZE_CASE);
			panel = new PanelImage(CHEMIN_IMAGE_JOUEUR, rect);
			vue.removePanel(new Point(panel.getLocation().x, panel.getLocation().y+SIZE_CASE));
			listObj.add(new Joueur(Personne.DEFAULT_VITESSE, rect));
		}
		else if(typeAct.equals(Type.Porte.name()) ){
			Rectangle rect = new Rectangle(pos.x, pos.y, SIZE_CASE, 2*SIZE_CASE);
			panel = new PanelImage(CHEMIN_IMAGE_PORTE, rect );
			vue.removePanel(new Point(panel.getLocation().x, panel.getLocation().y+SIZE_CASE));
			listObj.add( new Porte(rect));
		}
		else{
			panel = new Case();
			panel.setBounds(pos.x, pos.y, SIZE_CASE, SIZE_CASE);
		}
		
		panel.addMouseListener(listeners);
		replacePanel(pos, panel);
		
		vue.repaint();
	}
	
	/**
	 * Remplace le panel a la position "pos" par le nouveau panel.
	 * @param pos
	 * @param panel
	 */
	private void replacePanel(Point pos, JPanel panel){
		JPanel oldPanel = vue.getPanel(pos);
		
		removeObjetColl(pos);
		vue.removePanel(oldPanel);
		vue.addPanel(panel);
		vue.verifierCase(listeners);
	}
	
	/**
	 * Supprime l'objet se trouvant à la position p
	 * @param p
	 */
	private void removeObjetColl(Point p){
		Iterator<ObjetCollision> i = listObj.iterator();
		while(i.hasNext()){
			if( i.next().getPosition().equals(p) ){
				i.remove();
				return;
			}
		}
	}
	
	/**
	 * Permet de savoir s il existe deja un objet de même type qui se trouve
	 * sur la même position.
	 * 
	 * @param point
	 * @param type
	 * @return
	 */
	private boolean objetExiste(Point point, String type){
		for( ObjetCollision obj : listObj ){
			if( obj.getDefaultPosition().equals(point) 
					&& obj.getNomType().equals(type)){
				return true;
			}
		}
		
		return false;
	}
	
	// ------------------------------------------- Listener menu
	@Override
	public void actionPerformed(ActionEvent e) {
		popMenu.setVisible(false);
		setCursor(e.getActionCommand());
		typeAct = e.getActionCommand();
	}
	
	/**
	 * Change le curseur de la sourie selon le type d'objet selectionné.
	 * @param typeObjet
	 */
	private void setCursor(String typeObjet){
		if( typeObjet.equals(Type.Bloc.name())){
			fenetre.setCursor(creerCursor(CHEMIN_IMAGE_BLOC, new Point(15,15)));
		}
		else if( typeObjet.equals(Type.Cle.name())){
			fenetre.setCursor(creerCursor(CHEMIN_IMAGE_CLE, new Point(15,15)));
		}
		else if( typeObjet.equals(Type.Mur.name())){
			fenetre.setCursor(creerCursor(CHEMIN_IMAGE_MUR, new Point(15,15)));
		}
		else if( typeObjet.equals(Type.Joueur.name())){
			fenetre.setCursor(creerCursor(CHEMIN_IMAGE_JOUEUR, new Point(10,20)));
		}
		else if( typeObjet.equals(Type.Porte.name())){
			fenetre.setCursor(creerCursor(CHEMIN_IMAGE_PORTE, new Point(10,20)));
		}
		else{
			fenetre.setCursor(creerCursor(CHEMIN_IMAGE_GOMME, new Point(15,15)));
		}
	}
	
	/**
	 * Permet de creer un curseur à partir d'une image.
	 * @param chemin de l'image.
	 * @return
	 */
	private Cursor creerCursor(String chemin, Point taille){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage(chemin);
		return tk.createCustomCursor(img, taille, "curseur");
	}

	// ------------------------------------------- GETTER
	public JFrame getFenetre() {
		return fenetre;
	}

	public JPopupMenu getPopMenu() {
		return popMenu;
	}

	public List<ObjetCollision> getListObj() {
		return listObj;
	}

	public int getNbColonne() {
		return nbColonne;
	}

	public int getNbLigne() {
		return nbLigne;
	}

	public String getTypeAct() {
		return typeAct;
	}
}
