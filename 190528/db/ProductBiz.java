package db;

public class ProductBiz extends Biz {
	Dao dao;
	public ProductBiz(String ip) {
		dao = new ProductDao(ip);
	}
	@Override
	public void register(Object obj) {
		// Transaction start ...
		// ���� : ��ȭ��ȣ �ڸ���, �̸� Ȯ��, �ֹι�ȣ ���� ��
		dao.insert(obj); // ���� �Ϸ� �� db�� ����
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
