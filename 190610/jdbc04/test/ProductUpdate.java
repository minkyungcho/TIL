package test;

import com.ProductsBiz;

import frame.Biz;
import vo.Products;

public class ProductUpdate {

	public static void main(String[] args) {
		Products p = new Products(16, 35);
		Biz<String, Products> biz = new ProductsBiz();
		try {
			biz.update(p);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
