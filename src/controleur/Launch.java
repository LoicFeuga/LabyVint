package controleur;

public class Launch {
	@SuppressWarnings("unused")
	private Controleur controleur;

	public Launch() {
		controleur = new Controleur("VJoueur");
	}
	
	public static void main(String[] args) {
		new Launch();
	}
}