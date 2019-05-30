package test;

import com.ProductDao;

//import com.UserDao;

import frame.Dao;
import vo.Product;

public class ProductDelete {

	public static void main(String[] args) {
		Dao<String, Product> dao = new ProductDao();
		String str = "66";
		try {
			dao.delete(str);
			System.out.println("Product Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
