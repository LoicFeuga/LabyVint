package vue;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Case extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Case() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);
	}
	
	public void setColorBorder(Color couleur){
		setBorder(BorderFactory.createLineBorder(couleur));
	}
}
