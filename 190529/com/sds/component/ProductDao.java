package com.sds.component;

import java.util.ArrayList;

import com.sds.frame.Dao;
import com.sds.vo.Product;

public class ProductDao extends Dao<Integer, Product> {

	@Override
	public void insert(Product v) throws Exception {
		if(v.getId() == 200) {
			throw new Exception("E0001"); // ID 중복으로 인한 ERROR
		}
		System.out.println(v+" Inserted.");	
	}

	@Override
	public void delete(Integer k) throws Exception {
		if(k == 200) {
			throw new Exception("E0002"); 
		}
		System.out.println(k+" Deleted.");	
	}

	@Override
	public void update(Product v) throws Exception {
		if(v.getId() == 200) {
			throw new Exception("E0003"); 
		}
		System.out.println(v+" Uodated.");		
	}

	@Override
	public Product select(Integer k) throws Exception {
		Product product = new Product(k, "pants", 10000);
		return product;
	}

	@Override
	public ArrayList<Product> select() throws Exception {
		ArrayList<Product> list = new ArrayList<>();
		list.add(new Product(100, "pants", 10000));
		list.add(new Product(200, "pants", 20000));
		list.add(new Product(300, "pants", 30000));
		list.add(new Product(400, "pants", 40000));
		list.add(new Product(500, "pants", 50000));
		return list;
	}

}
