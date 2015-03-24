package vue;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Mur extends JPanel{

	private static final long serialVersionUID = 1L;

	public Mur(Rectangle rect) {
		setBackground(Color.black);
		setBounds(rect);
	}
}
