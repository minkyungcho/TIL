package db2;

public class App {
//	// 90��� �ڵ� 2 Ƽ�� ��(App>Oracle) -> 3 Ƽ�� ��(App>DB>Oracle)
//	public static void connection() {
//		System.out.println("DB Connection ...");
//	}
//	public static void close() {
//		System.out.println("DB Close ...");
//	}
//	public static void insertUser(String id, String name, String pwd) {
//		// ������ ���� ..
//		System.out.println(id+" "+name+" "+pwd+" Inserted");
//	}
//	public static String [] selectUser(String id) {
//		// ������ ���� ..
//		// ID�� ������ ������ �´�.
//		String [] user = {"id01","james","pwd01"};
//		return user;
//	}
	
	public static void main(String[] args) {
		String id = "id02";
		String name = "Tom";
		String pwd = "pwd02";
		DB db = new DBUser("192.100.100.1");
		
		User user = new User(id, name, pwd);
		db.insert(user);
		
		User dbuser = null;
		dbuser = (User)db.select("id02");
		System.out.println("dbuser");
	}

}
