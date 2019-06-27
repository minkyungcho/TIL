package com.product;

import com.frame.Biz;
import com.frame.Dao;
import com.user.UserBiz;
import com.vo.Product;
import com.vo.User;

public class ProductSelect {

	public static void main(String[] args) {
		Integer i = 37;
		Biz<Integer, Product> biz = new ProductBiz();
		try {
			System.out.println("SELECT OK");
			biz.get(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
