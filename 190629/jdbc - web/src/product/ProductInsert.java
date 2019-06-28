package com.product;

import com.frame.Biz;
import com.vo.Product;

public class ProductInsert {

	public static void main(String[] args) {
		Product p = new Product("바지", 1000, "img1");
		Biz<Integer, Product> biz = new ProductBiz();
		
		try {
			biz.register(p);
			System.out.println("INSERT OK");
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}
}
