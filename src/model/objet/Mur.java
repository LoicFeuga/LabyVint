package model.objet;


public class Mur extends Objet {
	
	
	public Mur(){
		this.estRamassable = false;
		
	}
	
	public Mur(String nom, boolean ramassable){
		this.estRamassable = ramassable;
		this.nom = nom;
	}
	
}