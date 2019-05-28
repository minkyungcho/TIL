package db;

public class ProductBiz extends Biz {
	Dao dao;
	public ProductBiz(String ip) {
		dao = new ProductDao(ip);
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
		Product pro = null;
		// Transaction start ...
		pro = (Product)dao.select(obj);
		// Transaction end ...	
		return pro;
	}

}
