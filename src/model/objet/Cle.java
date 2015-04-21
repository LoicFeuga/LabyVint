package model.objet;

import java.awt.Rectangle;

public class Cle extends Objet {

	public Cle(Rectangle hitBox) {
		super(hitBox,Type.Cle.name());
		super.estRamassable = true;
	}
}