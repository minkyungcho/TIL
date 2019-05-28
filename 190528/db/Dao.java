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
	public abstract void insert(Object obj); // �ֻ��� Ŭ���� Object Ȱ��
	public abstract Object select(Object obj); // Object�� ��� ���� Ŭ������ return �� �� ����
	public abstract void delete(Object obj);
	
}
