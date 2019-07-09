package com.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.ProductMapper;
import com.vo.Product;

@Repository("pd")
public class ProductDao implements Dao<Integer, Product> {
	@Autowired
	ProductMapper pm;
	
	@Override
	public void insert(Product v) throws Exception {
		System.out.println(v+" Inserted...");	
		pm.insert(v);
	}

	@Override
	public void update(Product v) throws Exception {
		System.out.println(v+" Updated...");	
		pm.update(v);
	}

	@Override
	public void delete(Integer k) throws Exception {
		System.out.println(k+" Deleted...");		
		pm.delete(k);
	}

	@Override
	public Product select(Integer k) throws Exception {
		System.out.println(k+" Selected...");
		return pm.select(k);
	}

	@Override
	public ArrayList<Product> select() throws Exception {
		
		return pm.selectall();
	}

}
