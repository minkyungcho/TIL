package com.product;

import com.frame.Biz;
import com.frame.Dao;
import com.user.UserBiz;
import com.vo.Product;
import com.vo.User;

public class ProductUpdate {

	public static void main(String[] args) {
		Product product = new Product("하의", 3000,"img3");
		Biz<Integer, Product> biz = new ProductBiz();
		try {
			biz.modify(product);
			System.out.println("UPDATE OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
