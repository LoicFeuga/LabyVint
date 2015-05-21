package model.personne;

import java.awt.Rectangle;
import java.util.Timer;

import model.Pattern;

public class Ennemi extends Personne {
	
	private Pattern pattern;
	private Timer timer;
	
	/**
	 * Constructeur de Ennemis
	 * @param vitesse
	 * @param nomType
	 */
	public Ennemi(int vitesse, Rectangle hitBox) {
		super(vitesse, hitBox);
		super.nomType = Type.Ennemi.name();
	}
	
	/**
	 * Constructeur de Ennemis
	 * @param vitesse
	 * @param nomType
	 */
	public Ennemi(int vitesse, Rectangle hitBox, Pattern pattern) {
		super(vitesse, hitBox);
		super.nomType = Type.Ennemi.name();
		this.pattern = pattern;
	}
	
	/**
	 * Permet de jouer le pattern de déplacement
	 */
	public void play(){
		timer = new Timer(getNomType() + getId() );
		timer.schedule(pattern, 0, 100);
	}
	
	/**
	 * Permet d'arreter le timer
	 */
	public void stop(){
		timer.cancel();
	}
	
	/**
	 * Permet de recommencer le pattern.
	 */
	public void reset(){
		pattern.reset();
	}
	
	public void setPattern(Pattern pattern){
		this.pattern = pattern;
	}
	
}