package db;

public abstract class Dao {
	private String ip;

	public Dao() {
	}

	public Dao(String ip) {
		this.ip = ip;
	}
	public void connection() {
		System.out.println(ip+":connected..");
	}
	public void close() {
		System.out.println(ip+":closed..");
	}
	public abstract void insert(Object obj); // 최상위 클래스 Object 활용
	public abstract Object select(Object obj); // Object의 모든 하위 클래스로 return 할 수 있음
	public abstract void delete(Object obj);
	
}
