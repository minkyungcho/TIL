package db;

public class App {

	public static void main(String[] args) {
		User user = new User("id01", "james", "pwd01"); // user �Է�
		Biz biz = new UserBiz("192.168.111.100"); // db ip-address // biz ���� �ֱ�
		biz.register(user);
		biz.remove("id01"); // id�� �ش��ϴ� user ����
		User dbuser = null;
		dbuser = (User)biz.get("id01"); // �ش� id�� ������Ͷ�.
		System.out.println(dbuser);
	}
}
