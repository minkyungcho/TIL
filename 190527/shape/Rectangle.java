package shape;

public class Rectangle extends Shape{
	private int height;
	private int width;
	public Rectangle() {
	}
	
	
	public Rectangle(int height, int width) {
		this.height = height;
		this.width = width;
	}


	public Rectangle(Point point, int height, int width) {
		super(point);
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	@Override
	public String toString() {
		return "Rectangle [point=" + point + ", height=" + height + ", width=" + width + "]";
	}


	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		double result = 0.0;
		result = this.width*this.height;
		return result;
	}
	@Override
	public double getCircume() {
		// TODO Auto-generated method stub
		double result = 0.0;
		result = (this.width+this.height) *2;
		return result;
	}
	
	
}
