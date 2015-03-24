package vue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import controleur.Controleur;

public class Vue extends JPanel{
	private HashMap<Point,BufferedImage> listImage;

	/**
	 * HashMap<Point,BufferedImage>
	 */
	public Vue(HashMap<Point,BufferedImage> imageList) {
		super();
		this.listImage = imageList;
	
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
	}
}