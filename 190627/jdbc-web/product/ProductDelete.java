package com.product;
import com.frame.Biz;
import com.vo.Product;

public class ProductDelete {

	public static void main(String[] args) {
		Biz<Integer, Product> biz = new ProductBiz();
		int i = 37;
		try {
			biz.remove(i);
			System.out.println("Product Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
