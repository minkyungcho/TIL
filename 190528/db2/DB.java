package db2;

public abstract class DB {
//	public static void connection() {
//		System.out.println("DB Connection ...");
//	}
//	public static void close() {
//		System.out.println("DB Close ...");
//	}
//	public static void insertUser(String id, String name, String pwd) {
//		// 데이터 검증 ..
//		connection();
//		System.out.println(id+" "+name+" "+pwd+" Inserted");
//		close();
//	}
//	public static String [] selectUser(String id) {
//		// 데이터 검증 ..
//		// ID의 정보를 가지고 온다.
//		String [] user = {"id01","james","pwd01"};
//		return user;
//	}
	
	String ip;
	public DB() {
	}
	public DB(String ip) {
		this.ip = ip;
	}
	public void connection() {
		System.out.println(ip+" Connect..");
	}
	public void close() {
		System.out.println(ip+" Close..");
	}
	public abstract void insert(Object obj);
	public abstract Object select(Object obj);
	
}
