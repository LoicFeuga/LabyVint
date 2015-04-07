package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import model.Direction;
import model.Son;

public class ListenerTouche implements KeyListener {
	private static final long TIME = 20;
	
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
					createur.getMoteur().moveJoueur(d);
				}
			}
			
		}
	};
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
