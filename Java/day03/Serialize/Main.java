package ser;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) throws Exception{
		FileOutputStream fos = new FileOutputStream("user.dat");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		User user = new User("id01", "pwd01", 25);
		oos.writeObject(user);
		
		oos.close(); // 여러 단계가 있을 경우에, 맨마지막만 close해주면 됨
	}

}
