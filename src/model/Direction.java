package model;

import java.awt.event.KeyEvent;

public enum Direction {
	NORD(KeyEvent.VK_UP),
	SUD(KeyEvent.VK_DOWN),
	EST(KeyEvent.VK_RIGHT),
	OUEST(KeyEvent.VK_LEFT),
	NONE(-1);
	
	private int id;
	Direction(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	/**
	 * Renvoie la direction associé à l'id.
	 * @param id
	 * @return
	 */
	public static Direction getDirection(int id){
		for( Direction dir : values() ){
			if( dir.getId() == id ){
				return dir;
			}
		}
		
		return null;
	}
}