package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Direction;

public class ListenerTouche implements KeyListener {
	private Controleur createur;
	private boolean h = false;
	private boolean b = false;
	private boolean d = false;
	private boolean g = false;

	public ListenerTouche(Controleur createur) {
		this.createur = createur;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if( key == KeyEvent.VK_DELETE){
			createur.nextLevel();
		}
		if (key == KeyEvent.VK_LEFT) {
			g = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			d = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			b = true;
		}
		if (key == KeyEvent.VK_UP) {
			h = true;
		}

		if (h && d) {
			createur.getMoteur().moveJoueur(Direction.NEST);
		} else if (h && g) {
			createur.getMoteur().moveJoueur(Direction.NOUEST);
		} else if (b && d) {
			createur.getMoteur().moveJoueur(Direction.SEST);
		} else if (b && g) {
			createur.getMoteur().moveJoueur(Direction.SOUEST);
		} else if (g) {
			createur.getMoteur().moveJoueur(Direction.OUEST);
		} else if (d) {
			createur.getMoteur().moveJoueur(Direction.EST);
		} else if (b) {
			createur.getMoteur().moveJoueur(Direction.SUD);
		} else if (h) {
			createur.getMoteur().moveJoueur(Direction.NORD);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			g = false;
		}
		if (key == KeyEvent.VK_RIGHT) {
			d = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			b = false;
		}
		if (key == KeyEvent.VK_UP) {
			h = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
