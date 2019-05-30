package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import frame.Dao;
import frame.Sql;
import vo.Product;
import vo.User;

public class ProductDao extends Dao<String, Product> {

	@Override
	public void insert(Product v) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getCon();
			pstmt = getCon().prepareStatement(Sql.insertProduct);
			pstmt.setString(1, v.getId());
			pstmt.setString(2, v.getName());
			pstmt.setDouble(3, v.getPrice());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
			close(con);
		}
	}

	@Override
	public void delete(String k) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getCon();
			pstmt = getCon().prepareStatement(Sql.deleteProduct);
			pstmt.setString(1, k);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
			close(con);
		}
	}

	@Override
	public void update(Product v) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = getCon();
			pstmt = getCon().prepareStatement(Sql.updateProduct);
			pstmt.setString(1, v.getName());
			pstmt.setDouble(2, v.getPrice());
			pstmt.setString(3, v.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
			close(con);
		}
	}

	@Override
	public Product select(String k) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rset = null;
		Product p = null;
		try {
			con = getCon();
			pstmt = getCon().prepareStatement(Sql.selectProduct);
			pstmt.setString(1, k);
			rset = pstmt.executeQuery(); 
			rset.next();
			String pid = rset.getString("ID");
			String pname = rset.getString("NAME");
			Double pprice = rset.getDouble("PRICE");
			Date pregdate = rset.getDate("REGDATE");
			p = new Product(pid,pname,pprice,pregdate);
			pstmt.executeUpdate();
			System.out.println(pid+" "+pname+" "+pprice+" "+pregdate);
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
			close(con);
		}
		return p;
	}

	@Override
	public ArrayList<Product> select() throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rset = null;
		Product p = null;
		ArrayList<Product> ps = new ArrayList<>();
		try {
			con = getCon();
			pstmt = getCon().prepareStatement(Sql.selectAllProduct);
			rset = pstmt.executeQuery(); 
			while(rset.next()) {
				String pid = rset.getString("ID");
				String pname = rset.getString("NAME");
				Double pprice = rset.getDouble("PRICE");
				Date pregdate = rset.getDate("REGDATE");
				p = new Product(pid,pname,pprice,pregdate);
				ps.add(p);
				System.out.println(pid+" "+pname+" "+pprice+" "+pregdate);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
			close(con);
		}
		return ps;
	}

}
