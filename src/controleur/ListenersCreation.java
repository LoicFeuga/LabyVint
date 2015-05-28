package controleur;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import jeu.Parser;
import model.objet.ObjetCollision;
import model.objet.ObjetCollision.Type;
import vue.Case;

public class ListenersCreation implements KeyListener, MouseListener{
	
	private ControleurCreation hamecon;
	private boolean estCliqueG;
	
	public ListenersCreation(ControleurCreation hamecon) {
		this.hamecon = hamecon;
	}
	
	// ------------------------------------------- MouseListener
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPopupMenu popMenu = hamecon.getPopMenu();
		if( popMenu.isShowing() ){
			popMenu.setVisible(false);
			return;
		}
		
		if( e.getButton() == MouseEvent.BUTTON3 ){//clic droit
			popMenu.setLocation(e.getLocationOnScreen().x,e.getLocationOnScreen().y);
			popMenu.setVisible(true);
		}
		else if(e.getButton() == MouseEvent.BUTTON1 
				&& hamecon.getTypeAct() != null){//clic gauche
			hamecon.echangerPanel((JPanel)e.getSource());
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof Case )
			((Case)e.getSource()).setColorBorder(Color.GREEN);
		
		if( estCliqueG && hamecon.getTypeAct() != null ){
			hamecon.echangerPanel((JPanel)e.getSource());
		}
		e.consume();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() instanceof Case )
			((Case)e.getSource()).setColorBorder(Color.BLACK);
		
		e.consume();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if( e.getButton() == MouseEvent.BUTTON1 ){
			estCliqueG = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if( e.getButton() == MouseEvent.BUTTON1 ){
			estCliqueG = false;
		}
	}
	
	// ------------------------------------------- KeyListener
		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {
			if( e.getKeyCode() == KeyEvent.VK_ENTER &&
					compterType(Type.Joueur.name()) == 1){
				lancerJeu();
			}
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE ){
				hamecon.getFenetre().dispose();
			}
		}
		
		/**
		 * Permet de compter le nombre d'objet d'un même type 
		 * dans la liste des objets.
		 * @param type
		 */
		private int compterType(String type){
			int nb = 0;
			for(ObjetCollision obj : hamecon.getListObj() ){
				if(obj.getNomType().equals(type))
					nb++;
			}
			
			return nb;
		}
		
		/**
		 * Permet de lancer le jeu
		 */
		private void lancerJeu(){
			HashMap<String, Object> data = new HashMap<>();
			List<HashMap<String, Object>> carte = new ArrayList<HashMap<String,Object>>();
			data.put("nbCaseHauteur", hamecon.getNbLigne());
			data.put("nbCaseLargeur", hamecon.getNbColonne());
			data.put("carte", carte);
			
			for( ObjetCollision obj : hamecon.getListObj() ){
				HashMap<String, Object> desc = new HashMap<>();
				carte.add(desc);
				
				Rectangle rect = obj.getHitBox();
				desc.put("largeur", rect.width);
				desc.put("hauteur", rect.height);
				desc.put("x", rect.x);
				desc.put("y", rect.y);
				desc.put("type", obj.getNomType());
			}
			 
			new Controleur("Nom", hamecon.getFenetre(), Parser.jsonEncoding(data));
			
			hamecon.getFenetre().setVisible(false);
		}

		@Override
		public void keyTyped(KeyEvent e) {}
}
