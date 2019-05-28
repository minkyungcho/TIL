package shape;

public class Triangle extends Shape {
	private int height;
	private int width;
	public Triangle() {
	}

	public Triangle(int height, int width) {
		this.height = height;
		this.width = width;
	}

	
	public Triangle(Point point, int height, int width) {
		super(point);
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
	}
	

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		double result = 0.0;
		result = (this.width*this.height)/2;
		return result;
	}

	@Override
	public double getCircume() {
		// TODO Auto-generated method stub
		double result = 0.0;
		result = Math.sqrt(Math.pow(this.width,2)+Math.pow(this.height,2));
		return result;
	}
	
	
	
}
