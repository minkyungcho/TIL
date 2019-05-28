package db;

public class App {

	public static void main(String[] args) {
		User user = new User("id01", "james", "pwd01"); // user 입력
		Biz biz = new UserBiz("192.168.111.100"); // db ip-address // biz 통해 넣기
		biz.register(user);
		biz.remove("id01"); // id에 해당하는 user 삭제
		User dbuser = null;
		dbuser = (User)biz.get("id01"); // 해당 id를 가지고와라.
		System.out.println(dbuser);
	}
}
