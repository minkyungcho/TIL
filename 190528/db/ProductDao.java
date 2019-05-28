package db;

public class ProductDao extends Dao {
	public ProductDao() {
	}
	public ProductDao(String ip) {
		super(ip);
	}
	
	@Override
	public void insert(Object obj) {
		connection();
		Product pro = (Product)obj;
		System.out.println(pro.getId()+" "+pro.getName()+" "+pro.getPrice()+" Inserted...");
		close();
	}

	@Override
	public Object select(Object obj) {
		Product pro = null;
		String str = (String)obj;
		// DB에서 SELECT해서 데이터를 가지고 온다.
		connection();
		pro = new Product("p01","pants", 10000);
		close();
		return pro;
	}

	@Override
	public void delete(Object obj) {
		connection();
		String str = (String)obj;
		System.out.println(str+" Deleted...");
		close();
	}

}
