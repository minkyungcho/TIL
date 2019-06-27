package com.product;

import com.frame.Biz;
import com.frame.Dao;
import com.user.UserBiz;
import com.vo.Product;
import com.vo.User;

public class ProductSelectAll {

	public static void main(String[] args) {
		Biz<Integer, Product> biz = new ProductBiz();
		try {
			System.out.println("SELECT ALL OK");
			biz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
