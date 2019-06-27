package com.product;

import java.sql.Connection;
//import java.util.Date;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.frame.Dao;
import com.frame.Sql;
import com.vo.Product;

public class ProductDao extends Dao<Integer, Product> {

	@Override
	public void insert(Product v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.insertProduct);
			pstmt.setInt(1, v.getId());
			pstmt.setString(2, v.getName());
			pstmt.setDouble(3, v.getPrice());
//			pstmt.setRegdate(4, v.getDate());
			pstmt.setString(4, v.getImgname());
			System.out.println(v.toString());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public void delete(Integer k, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.deleteProduct);
			pstmt.setInt(1, k);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public void update(Product v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.updateProduct);
			pstmt.setString(1, v.getName());
			pstmt.setDouble(2, v.getPrice());
			pstmt.setString(3, v.getImgname());
			pstmt.setInt(4, v.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public Product select(Integer k, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;
		try {
			pstmt = con.prepareStatement(Sql.selectProduct);
			pstmt.setInt(1, k);
			rset = pstmt.executeQuery(); 
			rset.next();
			int pid = rset.getInt("ID");
			String pname = rset.getString("NAME");
			Double pprice = rset.getDouble("PRICE");
			Date pdate = rset.getDate("REGDATE");
			String pimgname = rset.getString("IMGNAME");
			product = new Product(pid,pname,pprice,pdate,pimgname);
			pstmt.executeUpdate();
			System.out.println(pid+" "+pname+" "+pprice+" "+pdate+" "+pimgname);
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
		return product;
	}

	@Override
	public ArrayList<Product> select(Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;
		ArrayList<Product> products= new ArrayList<>();
		try {
			pstmt = con.prepareStatement(Sql.selectAllProduct);
			rset = pstmt.executeQuery(); 
			while(rset.next()) {
				int pid = rset.getInt("ID");
				String pname = rset.getString("NAME");
				Double pprice = rset.getDouble("PRICE");
				Date pdate = rset.getDate("REGDATE");
				String pimgname = rset.getString("IMGNAME");
				product = new Product(pid,pname,pprice,pdate,pimgname);
				pstmt.executeUpdate();
				System.out.println(pid+" "+pname+" "+pprice+" "+pdate+" "+pimgname);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
		return products;
	}

}
