package test;

import com.ProductDao;

import frame.Dao;
import vo.Product;

public class ProductSelect {

	public static void main(String[] args) {
		Dao<String, Product> dao = new ProductDao();
		String str = "P01";
		try {
			dao.select(str);
			System.out.println("Product Seleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
