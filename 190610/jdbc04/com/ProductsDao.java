package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import frame.Dao;
import frame.Sql;
import vo.Factory;
import vo.Products;

public class ProductsDao extends Dao<String, Products> {

	@Override
	public void insert(Products v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.insertProducts);
			pstmt.setInt(1, v.getPd_no());
			pstmt.setString(2, v.getPd_name());
			pstmt.setString(3, v.getPd_sub_name());
			pstmt.setString(4, v.getFact_no());
			pstmt.setInt(5, v.getPd_cost());
			pstmt.setInt(6, v.getPd_price());
			pstmt.setInt(7, v.getPd_amount());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public void delete(String k, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.deleteProducts);
			pstmt.setString(1, k);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public void update(Products v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.updateProducts);
			pstmt.setInt(1, v.getPd_amount());
			pstmt.setInt(2, v.getPd_no());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public Products select(String k, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Products p = null;
		
		try {
			pstmt = con.prepareStatement(Sql.selectProducts);
			pstmt.setString(1, k);
			
			rs = pstmt.executeQuery();
			rs.next();
			
//			p = new Products(rs.getString("PDNO"), rs.getString("FACNAME"), rs.getString("FACLOC"), rs.getString("FACLOC"), rs.getString("FACLOC"), rs.getString("FACLOC"), rs.getString("FACLOC"), rs.getString("FACLOC"));	
		} catch (Exception e) {
			throw e;
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return p;
	}

	@Override
	public ArrayList<Products> selectAll(Connection con) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
