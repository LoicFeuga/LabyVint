public class Objet extends ObjetCollision implements IRamassable {
	private String _nom;
	private boolean _estRamassable;

	public String getNom() {
		return this._nom;
	}

	public Objet ramasser() {
		throw new UnsupportedOperationException();
	}

	public boolean estRamassable() {
		throw new UnsupportedOperationException();
	}
}