package ws;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		DB<User, String> db = new UserDB();
		User user = new User("id01", "james", "pwd01");
		db.Create(user);
		User userdb = db.Select("pwd01");
		System.out.println(userdb);
		
		DB<Product, String> db2 = new ProductDB();
		db2.Create(new Product("p02", "T-shirt", 20000));
		Product p = db2.Select("p02");
		System.out.println(p);
		
		db.Update(new User("id02", "LEE", "LEE02"));
		
		db.Delete(new User("id02", "LEE", "LEE02"));
		
		ArrayList<User> dbuser = db.Select();
		for(int i=0; i<dbuser.size();i++) {
			System.out.println(dbuser.get(i));
		}
		
		db2.Update(new Product("p03", "Coffee", 100));
		
		db2.Delete(new Product("p03", "Coffee", 100));
		
		ArrayList<Product> db2p = db2.Select();
		for(int i=0; i<db2p.size();i++) {
			System.out.println(db2p.get(i));
		}
		
	}

}
