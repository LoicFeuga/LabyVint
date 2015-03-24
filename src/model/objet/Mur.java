package model.objet;

import java.awt.Rectangle;

public class Mur extends Objet {
	
	 
	public Mur(){
		super(null);
		this.estRamassable = false;
	}
	
	/**
	 * Constructeur normal du mur
	 * 
	 * @param hitBox
	 */
	public Mur(Rectangle hitBox){
		super(hitBox);
		this.estRamassable = false;
		
	}
	
	public Mur(String nom, boolean ramassable, Rectangle hitBox){
		super(hitBox);
		this.estRamassable = ramassable;
		this.nom = nom;
	}
	
	
	
}