package db2;

public class App {
//	// 90년대 코드 2 티어 모델(App>Oracle) -> 3 티어 모델(App>DB>Oracle)
//	public static void connection() {
//		System.out.println("DB Connection ...");
//	}
//	public static void close() {
//		System.out.println("DB Close ...");
//	}
//	public static void insertUser(String id, String name, String pwd) {
//		// 데이터 검증 ..
//		System.out.println(id+" "+name+" "+pwd+" Inserted");
//	}
//	public static String [] selectUser(String id) {
//		// 데이터 검증 ..
//		// ID의 정보를 가지고 온다.
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
