package com.product;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.Biz;
import com.frame.Dao;

@Service("pbiz")
public class ProductBiz implements Biz<Integer, Product> {
	
	@Resource(name="pd")
	Dao<Integer,Product> dao;
	
	public void startBiz() {
		System.out.println("Start Biz...");
	}
	
	public void endBiz() {
		System.out.println("End Biz...");
	}

	public Dao<Integer, Product> getDao() {
		return dao;
	}

	public void setDao(Dao<Integer, Product> dao) {
		this.dao = dao;
	}

	@Override
	public void insert(Product v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void update(Product v) throws Exception {
		dao.update(v);
	}

	@Override
	public void delete(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public Product select(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<Product> select() throws Exception {
		return dao.select();
	}

}
