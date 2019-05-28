package recursive;

public class Cub {

	public static void main(String[] args) {
		int x = 2;
		int n = 10;
		int result = cube(x,n);
		System.out.println(result);
	}
	private static int cube(int x, int n) {
		int result = 0;
		if(n == 1) {
			return x;
		}else {
			result = x * cube(x,n-1);
		}
		return result;
	}
}
