package test;

import com.ProductDao;

import frame.Dao;
import vo.Product;

public class ProductSelectAll {

	public static void main(String[] args) {
		Dao<String, Product> dao = new ProductDao();
		try {
			dao.select();
			System.out.println("Product Seleted All");
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
