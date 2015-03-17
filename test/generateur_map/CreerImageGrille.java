package generateur_map;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class CreerImageGrille {
	
	private static final int TAILLE_CARRE = 50; 
	
	public static void main(String[] args) {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		
		for( int y = 0; y < image.getHeight(); y += TAILLE_CARRE ){
			
		}
	}

}
