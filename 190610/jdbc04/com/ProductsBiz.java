package com;

import java.sql.Connection;
import java.util.ArrayList;

import frame.Biz;
import frame.Dao;
import vo.Products;

public class ProductsBiz extends Biz<String, Products> {
	Dao<String, Products> dao;
	
	public ProductsBiz() {
		dao = new ProductsDao();
	}

	@Override
	public void insert(Products v) throws Exception {
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
	public void delete(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Products v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Products select(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Products> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
