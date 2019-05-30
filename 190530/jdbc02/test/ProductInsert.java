package test;

import com.ProductDao;

//import com.UserDao;

import frame.Dao;
import vo.Product;

public class ProductInsert {

	public static void main(String[] args) {
		Dao<String, Product> dao = new ProductDao();
		Product p = new Product("p6","cat",10000);
		try {
			dao.insert(p);
			System.out.println("Product Inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
