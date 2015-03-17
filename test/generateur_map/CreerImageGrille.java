package generateur_map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreerImageGrille {
	
	public static int TAILLE_CARRE = 50; 
	public static final String NOM =  "data\\image\\map\\Map";
	public static final String FORMAT =  "jpg";
	
	public static void main(String[] args) {
		//Fond
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.setSize( ((int)(dim.getWidth()/TAILLE_CARRE)) * TAILLE_CARRE,
				((int)(dim.getHeight()/TAILLE_CARRE))  * TAILLE_CARRE);
		BufferedImage image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < image.getHeight(); y ++){
			for( int x = 0; x < image.getWidth(); x++){
				image.setRGB(x, y, Color.white.getRGB());
			}
		}
		
		//Disposition des lignes
		for( int y = 0; y < image.getHeight(); y += TAILLE_CARRE ){
			for( int x = 0; x < image.getWidth(); x ++){
				image.setRGB(x, y, Color.black.getRGB());
			}
		}
		
		for( int x = 0; x < image.getWidth(); x += TAILLE_CARRE ){
			for( int y = 0; y < image.getHeight(); y ++){
				image.setRGB(x, y, Color.black.getRGB());
			}
		}
		
		//Save
		try {
			ImageIO.write(image, FORMAT, new File(NOM+"."+FORMAT));
			System.out.println("Image créer !!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
