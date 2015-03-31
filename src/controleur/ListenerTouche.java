package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Direction;

public class ListenerTouche implements KeyListener{
	private Controleur createur;
	
	public ListenerTouche(Controleur createur) {
		this.createur = createur;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			createur.getMoteur().moveJoueur(Direction.OUEST);
		}else if(key == KeyEvent.VK_RIGHT){
			createur.getMoteur().moveJoueur(Direction.EST);
		}else if(key == KeyEvent.VK_DOWN){
			createur.getMoteur().moveJoueur(Direction.SUD);
		}else if(key == KeyEvent.VK_UP){
			createur.getMoteur().moveJoueur(Direction.NORD);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	

}
