package com;

import java.sql.Connection;
import java.util.ArrayList;

import frame.Biz;
import frame.Dao;
import vo.Factory;


public class FactoryBiz extends Biz<String, Factory> {
	Dao<String, Factory> dao;
	public FactoryBiz() {
		dao = new FactoryDao();
	}

	@Override
	public void insert(Factory v) throws Exception {
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
	public void update(Factory v) throws Exception {
		
	}

	@Override
	public Factory select(String k) throws Exception {
		Connection con = getCon();
		Factory f = null;
		
		try {
			f = dao.select(k, con);	
		} catch (Exception e) {
			
		} finally {
			close(con);
		}
		
		return f;
	}

	@Override
	public ArrayList<Factory> selectAll() throws Exception {
		Connection con = getCon();
		ArrayList<Factory> fs = null;
		
		try {
			fs = dao.selectAll(con);	
		} catch (Exception e) {
			
		} finally {
			close(con);
		}
		
		return fs;
	}
	

}
