package test;

import com.ProductsBiz;

import frame.Biz;
import vo.Products;

public class ProductInsert {

	public static void main(String[] args) {
		Products p = new Products(16, "COFFEE", "LONGBLACK", "4", 1300, 3900, 32);
		Biz<String, Products> biz = new ProductsBiz();
		
		try {
			biz.insert(p);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}

}
