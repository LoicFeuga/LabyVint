package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

public class CreationCarteVue extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Map<Point,JPanel> listPanel;
	
	private int ligne;
	private int colonne;
	private int largeurCase;
	private int hauteurCase;
	
	public CreationCarteVue(int colonne, int ligne, int largeurCase, int hauteurCase) {
		listPanel = new HashMap<>();
		this.ligne = ligne;
		this.colonne = colonne;
		this.largeurCase = largeurCase;
		this.hauteurCase = hauteurCase;
		
		setLayout(new GridLayout(ligne, colonne));
		setBackground(Color.white);
		
		for(int y = 0 ; y < ligne; y++){
			for( int x = 0; x < colonne; x++){
				Case c = new Case();
				c.setBounds(x*largeurCase, y*hauteurCase, largeurCase, hauteurCase);
				add(c);
				listPanel.put(new Point(x*largeurCase, y*hauteurCase), c);
			}
		}
		
		setLayout(null);
	}
	
	
	public void setMouseListener(MouseListener mouseListener){
		Iterator<Point> i = listPanel.keySet().iterator();
		
		while(i.hasNext()){
			listPanel.get(i.next()).addMouseListener(mouseListener);
		}
	}

	public void addPanel(JPanel panel){
		Point p = panel.getLocation();
		listPanel.put(new Point(p.x,p.y), panel);
		add(panel);
	}
	
	public void removePanel(JPanel panel){
		remove(panel);
		listPanel.remove(panel.getLocation());
	}
	
	public void removePanel(Point p){
		JPanel panel = listPanel.get(p);
		
		if( panel == null ) return;
		
		remove(panel);
		listPanel.remove(p);
	}
	
	public JPanel getPanel(Point p){
		return listPanel.get(p);
	}
	
	public void verifierCase(MouseListener mouseListener){
		
		for(int y = 0 ; y < ligne; y++){
			for( int x = 0; x < colonne; x++){
				Point p = new Point(x*largeurCase, y*hauteurCase);
				
				if( !listPanel.containsKey(p) ){
					Case c = new Case();
					c.addMouseListener(mouseListener);
					c.setBounds(x*largeurCase, y*hauteurCase, largeurCase, hauteurCase);
					add(c);
					listPanel.put(p, c);
				}
			}
		}
	}
}
