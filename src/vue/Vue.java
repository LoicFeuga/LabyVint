package vue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;



import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import org.json.simple.JSONArray;



public class Vue extends JPanel implements Observer{
	//private HashMap<Point,BufferedImage> listImage;
	private ArrayList<Image> listImage;
	private  HashMap<String,Object> configJSON;

	/**
	 * HashMap<Point,BufferedImage>
	 */
	public Vue(ArrayList<Image>imageList, HashMap<String,Object> json) {
		super();
		this.listImage = imageList;
		this.configJSON = json;
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/*
		carte->0->x | y | largeur | hauteur | type 
		nbCaseLargeur 
		nbCaseHauteur
		tailleCase
		*/
		
		JSONArray list =  (JSONArray) configJSON.get("carte");
		
		for(int i = 0 ; i < (int) (long) configJSON.get("nbCaseLargeur") ; i++){
			
			for(int j = 0; j < (int) (long) configJSON.get("nbCaseHauteur") ; j++){
				
				HashMap<String, Long> listIn = (HashMap<String, Long>) list.get(i+j);
				int x = (int) (long) listIn.get("x");
				int y = (int) (long) listIn.get("y");
				int largeur = (int) (long) listIn.get("largeur");
				int hauteur = (int) (long) listIn.get("hauteur");
				
				g.drawImage(listImage.get(0), x , y, largeur , hauteur,null);
			}
		}
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	


}