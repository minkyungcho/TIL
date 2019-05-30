package test;

import com.ProductDao;

import frame.Dao;
import vo.Product;

public class ProductUpdate {

	public static void main(String[] args) {
		Dao<String, Product> dao = new ProductDao();
		Product p = new Product("p6","rentcar",500);
		try {
			dao.update(p);
			System.out.println("Product Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
