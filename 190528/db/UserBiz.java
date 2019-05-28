package db;

public class UserBiz extends Biz {
	Dao dao;
	public UserBiz(String ip){
		dao = new UserDao(ip);
	}
	
	@Override
	public void register(Object obj) {
		// Transaction start ...
		// 검증 : 전화번호 자릿수, 이름 확인, 주민번호 길이 비교
		dao.insert(obj); // 검증 완료 후 db에 저장
		// Transaction end ...
	}

	@Override
	public void remove(Object obj) {
		// Transaction start ...
		dao.delete(obj);
		// Transaction end ...		
	}

	@Override
	public Object get(Object obj) {
		User user = null;
		// Transaction start ...
		user = (User)dao.select(obj);
		// Transaction end ...	
		return user;
	}

}
