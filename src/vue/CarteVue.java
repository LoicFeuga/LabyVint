package vue;
import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;



public class CarteVue extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	/** HashMap<ID_OBJET_COLLISION, JPANEL>  */
	private HashMap<Integer, JPanel> listPanel;

	/**
	 * HashMap<Point,BufferedImage>
	 */
	public CarteVue(HashMap<Integer, JPanel> listJPanel) {
		super();
		setLayout(null);
		setBackground(Color.white);
		this.listPanel = listJPanel;
		initListPanel();
	}
	
	/**
	 * Permet d'initialiser la liste des panels
	 */
	private void initListPanel(){
		if( listPanel == null || listPanel.isEmpty() ){
			System.err.println("ERROR: initListPanel is null || empty");
			return;
		}
		
		Iterator<Integer> i = listPanel.keySet().iterator();
		
		while( i.hasNext() ){
			JPanel panel = listPanel.get(i.next());
			add(panel);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object obj) {
		
		HashMap<String, Object> data = (HashMap<String, Object>) obj;
		if( data.containsKey("deplacer") ){
			
			deplacer((HashMap<String, Object>)data.get("deplacer"));
		}
	}

	/**
	 * Permet de déplacer un panel parmit
	 * la liste de panel
	 * @param data
	 */
	private void deplacer(HashMap<String, Object> data){
		Point point = (Point)data.get("point");
		int id = (int)data.get("id");
		
		JPanel panel = listPanel.get(id);
		panel.setLocation(point);
	}


}