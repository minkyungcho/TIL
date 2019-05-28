package recursive;

public class Factorial {

	public static void main(String[] args) {
		int n = 5;
		int result = factorial(n);
		System.out.println(result);
	}

	private static int factorial(int n) {
		int result = 0;
		if( n == 1) {
			return 1;
		}else {
			return n * factorial(n-1);
		}
//		return result;
	}

}
