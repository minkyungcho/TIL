package game.chess.piece;

public abstract class Piece {
	private int x;
	private int y;
	private String name;
	private boolean alive;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public String toString() {
		return "Piece [x=" + x + ", y=" + y + ", name=" + name + ", alive=" + alive + "]";
	}

	public abstract void catchPiece();
}
