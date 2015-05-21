package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import model.objet.ObjetCollision;
import model.personne.Ennemi;

public class Pattern extends TimerTask{
	
	private Ennemi ennemi;
	private List<HashMap<String, Long>> deplacement;
	
	private int position;
	private Direction direcASuivre;
	private Point nextPosition;
	
	public Pattern(Ennemi ennemi, List<HashMap<String, Long>> deplacement) {
		this.ennemi = ennemi;
		this.deplacement = deplacement;
	}

	@Override
	public void run() {
		Moteur moteur = Moteur.getMoteur();
		if( nextPosition == null || nextPosition.equals(ennemi.getPosition()) || direcASuivre == null){
			nextPosition = (Point) ennemi.getPosition().clone();
			prepareNextPosition();
		}
		
		List<ObjetCollision> listObj = moteur.listObjCollision(ennemi, direcASuivre);
		if( !listObj.isEmpty() ){
			if( listObj.contains(moteur.getJoueur())){
				moteur.reset();
			}
			return;
		}
		
		deplacerEnnemi();
	}
	
	/**
	 * Permet de deplacer un ennemi tout en mettant à jour la vue.
	 */
	private void deplacerEnnemi(){
		Moteur moteur = Moteur.getMoteur();
		if( direcASuivre.equals(Direction.NORD) ){
			if( (ennemi.getPosition().y - ennemi.getVitesse()) < nextPosition.y)ennemi.setPosition(nextPosition);
			else ennemi.deplacer(direcASuivre);
		}
		else if( direcASuivre.equals(Direction.SUD) ){
			if( (ennemi.getPosition().y + ennemi.getVitesse()) > nextPosition.y)ennemi.setPosition(nextPosition);
			else ennemi.deplacer(direcASuivre);
		}
		if( direcASuivre.equals(Direction.EST) ){
			if( (ennemi.getPosition().x + ennemi.getVitesse()) > nextPosition.x)ennemi.setPosition(nextPosition);
			else ennemi.deplacer(direcASuivre);
		}
		if( direcASuivre.equals(Direction.OUEST) ){
			if( (ennemi.getPosition().x - ennemi.getVitesse()) < nextPosition.x)ennemi.setPosition(nextPosition);
			else ennemi.deplacer(direcASuivre);
		}
		moteur.updateMove(ennemi);
	}
	
	private void prepareNextPosition(){
		HashMap<String, Long> hm = deplacement.get(position);
		
		Iterator<String> i = hm.keySet().iterator();
		String key = i.next();
		
		direcASuivre = Direction.valueOf(key);
		long distance = hm.get(key);
		
		if( direcASuivre.equals(Direction.NORD) )nextPosition.translate(0, -((int)distance));
		else if( direcASuivre.equals(Direction.SUD) )nextPosition.translate(0, ((int)distance));
		if( direcASuivre.equals(Direction.EST) )nextPosition.translate((int)distance, 0);
		if( direcASuivre.equals(Direction.OUEST) )nextPosition.translate(-(int)distance, 0);
		
		position++;
		if( position == deplacement.size() ){
			position = 0;
		}
	}
	
	/**
	 * Remet la position
	 */
	public void reset(){
		position = 0;
		direcASuivre = null;
	}

}
