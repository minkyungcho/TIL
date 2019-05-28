package shape;

public abstract class Shape {
	protected Point point;

	public Shape() {
	}

	public Shape(Point point) {
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Shape [point=" + point + "]";
	}
	
	public void move(int x, int y) {
		this.point.setX(x);
		this.point.setY(y);
	}
	
	// �߻�Ŭ����
	public abstract double getArea();
	public abstract double getCircume();
	
}
