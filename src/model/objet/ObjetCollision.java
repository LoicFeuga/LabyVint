package model.objet;
public class ObjetCollision {
	private Rectangle _hitBox;
	private Position _position;
	private int _id;
	/**
	 * static
	 */
	private int _nbTotalObjet;

	public ObjetCollision() {
		throw new UnsupportedOperationException();
	}

	public boolean isTouch(Object aRectangle_rect) {
		throw new UnsupportedOperationException();
	}

	public Position getPosition() {
		return this._position;
	}

	public int getId() {
		return this._id;
	}

	public void setId(int aId) {
		this._id = aId;
	}

	public int getNbTotalObjet() {
		return this._nbTotalObjet;
	}

	public void setNbTotalObjet(int aNbTotalObjet) {
		this._nbTotalObjet = aNbTotalObjet;
	}

	public void setPosition(Position aPosition) {
		this._position = aPosition;
	}

	public void incrementNbTotal() {
		throw new UnsupportedOperationException();
	}
}