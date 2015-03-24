package model.objet;

import java.awt.Rectangle;


public class Porte extends Objet {

	private Cle serrure;
	
	
	public Porte(Rectangle hitBox) {
		super(hitBox,"Porte");

	}


	public void ouvrir() {
		throw new UnsupportedOperationException();
	}
}