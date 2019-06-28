package com.product;

import java.sql.Connection;
import java.util.ArrayList;

import com.frame.Biz;
import com.frame.Dao;
import com.vo.Product;

public class ProductBiz extends Biz<Integer, Product> {
	Dao<Integer, Product> dao;
	public ProductBiz() {
		dao = new ProductDao();
	}
	@Override	
	public void register(Product v) throws Exception {
		Connection con = null;
		try {
			con = getCon();
			dao.insert(v, con);
			con.commit();
		}catch(Exception e) {
			con.rollback();
			throw e;
		}finally {
			close(con);
		}
	}

	@Override
	public void remove(Integer k) throws Exception {
		Connection con = null;
		try {
			con = getCon();
			dao.delete(k, con);
			con.commit();
		}catch(Exception e) {
			con.rollback();
			throw e;
		}finally {
			close(con);
		}
	}

	@Override
	public void modify(Product v) throws Exception {
		Connection con = null;
		try {
			con = getCon();
		dao.update(v, con);
		con.commit();
		}catch(Exception e) {
			con.rollback();
			throw e;
		}finally {
			close(con);
		}
	}
	@Override
	public Product get(Integer k) throws Exception {
		Connection con = null;
		Product p = null;
		try {
			con = getCon();
			p = dao.select(k, con);
		}catch(Exception e) {
			throw e;
		}finally {
			close(con);
		}
		return p;
	}
	@Override
	public ArrayList<Product> get() throws Exception {
		Connection con = null;
		ArrayList<Product> products= new ArrayList<>();
		try {
			con = getCon();
			products = dao.select(con);
		}catch(Exception e) {
			throw e;
		}finally {
			close(con);
		}
		return products;
	}
	

}
