package test;

import com.ProductsBiz;

import frame.Biz;
import vo.Products;

public class ProductDelete {

	public static void main(String[] args) {
		String str = "16";
		Biz<String, Products> biz = new ProductsBiz();
		try {
			biz.delete(str);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
