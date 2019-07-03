package com.product;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.user.User;

@Repository("pd")
public class ProductDao implements Dao<Integer, Product> {

	@Override
	public void insert(Product v) throws Exception {
		System.out.println(v+" Inserted...");
	}

	@Override
	public void update(Product v) throws Exception {
		System.out.println(v+" Updated...");
	}

	@Override
	public void delete(Integer k) throws Exception {
		System.out.println(k+" Deleted...");
	}

	@Override
	public Product select(Integer k) throws Exception {
		System.out.println(k+" Selected...");
		return new Product(100, "p01", 1000.0);
	}

	@Override
	public ArrayList<Product> select() throws Exception {
		ArrayList<Product> list = new ArrayList<>();
		list.add(new Product(101, "p01", 1000.0));
		list.add(new Product(102, "p02", 1000.0));
		list.add(new Product(103, "p03", 1000.0));
		list.add(new Product(104, "p04", 1000.0));
		return list;
	}

}
