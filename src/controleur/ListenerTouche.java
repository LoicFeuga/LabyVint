package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.Direction;
import model.Moteur;
import model.Son;
import model.objet.Objet;
import model.objet.ObjetCollision;
import model.objet.ObjetCollision.Type;
import model.personne.Joueur;

public class ListenerTouche implements KeyListener {
	private static final long TIME = 15;
	
	private Controleur createur;
	private HashMap<Direction, Boolean> directions;
	
	// ------------------------------------------- Timer
	private Timer timer;
	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			Iterator<Direction> i = directions.keySet().iterator();
			while( i.hasNext() ){
				Direction d = i.next();
				if( directions.get(d) == true ){
					moveJoueur(d);
				}
			}
			
		}
	};
	
	private int getNbDirection(){
		int nb = 0;
		
		Iterator<Direction> i = directions.keySet().iterator();
		while(i.hasNext()){
			if( directions.get(i.next()) )
				nb++;
		}
		return nb;
	}
	
	private void moveJoueur(Direction direction){
		Moteur moteur = Moteur.getMoteur();
		List<ObjetCollision> listObj =  moteur.listObjCollisionJ(direction);
		if( listObj.isEmpty() ){
			moteur.moveJoueur(direction);
			createur.jouerSonPas();
		}
		else{
			
			for( ObjetCollision objC : listObj){
				Objet obj = (Objet) objC;
				if( obj.estRamassable() )moteur.getJoueur().ramasser(obj);
				else if( obj.getNomType().equals(Type.Porte.name()) )createur.nextLevel();
				else if ( obj.getNomType().equals(Type.Bloc.name()) &&
						 moteur.listObjCollision(objC, direction).isEmpty() ){
						moteur.moveObjet(objC, direction);
				}
				else if( getNbDirection() < 2){ //evite que les sons se mélangent
					createur.jouerSonBoum();
				}
			}
		}
	}
	
	// ------------------------------------------- 
	public ListenerTouche(final Controleur createur) {
		this.createur = createur;
		
		//init directions
		directions = new HashMap<>();
		for( Direction dir : Direction.values() ){
			directions.put(dir, false);
		}
		
		//Timer
		timer = new Timer();
		timer.schedule(timerTask, 0,TIME);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		//change lev
		if( key == KeyEvent.VK_DELETE){
			createur.nextLevel();
			return;
		}
		else if( key == KeyEvent.VK_SPACE ){
			createur.reset();
			return;
		}
		else if( key == KeyEvent.VK_ESCAPE ){
			createur.getFenetre().dispose();
		}
		
		Direction d = Direction.getDirection(key);	
		if( d != null && !Direction.NONE.equals(d) ){
			directions.put(d, true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		Direction d = Direction.getDirection(key);	
		if( d != null && !Direction.NONE.equals(d) ){
			directions.put(d, false);
		}
		
		Son.stopToPlay();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public void arreter(){
		timer.cancel();
	}
}
