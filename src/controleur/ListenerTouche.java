package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class ListenerTouche implements KeyListener{
	private Controleur createur;
	
	public ListenerTouche(Controleur createur) {
		this.createur = createur;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			System.out.println("left");
			createur.moveLeft();
		}else if(key == KeyEvent.VK_RIGHT){
			System.out.println("right");
			createur.moveRight();
		}else if(key == KeyEvent.VK_DOWN){
			System.out.println("down");
			createur.moveDown();
		}else if(key == KeyEvent.VK_UP){
			System.out.println("up");
			createur.moveUp();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
