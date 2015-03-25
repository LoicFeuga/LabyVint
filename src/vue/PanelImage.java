package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.server.SDDocument;

/**
 * Cette classe permet de visualiser une image sur un Panel.
 * 
 * @author Nicoletti Sébastien
 *
 */
public class PanelImage extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	
	public PanelImage(String path, Rectangle rect) {
		
		if( path == null || path.isEmpty() ){
			System.err.println("ERROR: ImagePanel.constructor path empty || null");
			return;
		}
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		setBounds(rect);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if( image != null){
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
	}

}
