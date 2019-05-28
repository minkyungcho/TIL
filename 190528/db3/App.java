package db3;

public class App {

	public static void main(String[] args) {
		DB<User, String> db = new UserDB();
		User user = new User("id01", "james", "pwd01");
		db.insert(user);
		User userdb = db.Select("pwd01");
		System.out.println(userdb);
		
		DB<Product, Integer> db2 = new ProductDB();
		db2.insert(new Product(100, "T-shirt", 20000));
		Product p = db2.Select(200); // pants Ã£±â
		System.out.println(p);
	}

}
