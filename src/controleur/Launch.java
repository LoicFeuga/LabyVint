package controleur;

import java.awt.Color;


public class Launch {
	private Controleur controleur;

	public Launch() {
		controleur = new Controleur("VJoueur");
	}
	
	public static void main(String[] args) {
		new Launch();
	}
}