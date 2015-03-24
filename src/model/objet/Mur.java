package model.objet;

import java.awt.Rectangle;

public class Mur extends Objet {
	
	 
	public Mur(){
		super(new Rectangle(),Type.Mur.name());
		this.estRamassable = false;
	}
	
	/**
	 * Constructeur normal du mur
	 * 
	 * @param hitBox
	 */
	public Mur(Rectangle hitBox){
		super(hitBox,Type.Mur.name());
		this.estRamassable = false;
		
	}

	
	
	
}