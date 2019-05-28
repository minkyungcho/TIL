package db;

public class App2 {

	public static void main(String[] args) {
//		Product pro = new Product("id01", "Min", 9.99);
//		Biz biz = new ProductBiz("192.168.111.101");
//		biz.register(pro);
//		biz.remove("id01");
//		Product dbpro= null;
//		dbpro= (Product)biz.get("id01"); // 해당 id를 가지고와라.
//		System.out.println(dbpro);
		Biz biz = null;
		Product p = new Product("p01", "pants", 10000);
		biz.register(p);
		
	}

}
