package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import frame.Dao;
import frame.Sql;
import vo.Factory;

public class FactoryDao extends Dao<String,Factory> {

	@Override
	public void insert(Factory v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.insertFactory);
			pstmt.setString(1, v.getFact_no());
			pstmt.setString(2, v.getFac_name());
			pstmt.setString(3, v.getFac_loc());
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
			pstmt = con.prepareStatement(Sql.deleteFactory);
			pstmt.setString(1, k);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
		
	}

	@Override
	public void update(Factory v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.updateFactory);
			pstmt.setString(1, v.getFac_loc());
			pstmt.setString(2, v.getFact_no());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
		
	}

	@Override
	public Factory select(String k, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Factory f = null;
		
		try {
			pstmt = con.prepareStatement(Sql.selectFactory);
			pstmt.setString(1, k);
			rs = pstmt.executeQuery();
			rs.next();
			f = new Factory(rs.getString("FACTNO"), rs.getString("FACNAME"), rs.getString("FACLOC"));	
		} catch (Exception e) {
			throw e;
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return f;
	}

	@Override
	public ArrayList<Factory> selectAll(Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Factory> fs = new ArrayList<Factory>();
		
		try {
			pstmt = con.prepareStatement(Sql.selectAllFactory);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Factory f = new Factory(rs.getString("FACTNO"), rs.getString("FACNAME"), rs.getString("FACLOC"));
				
				fs.add(f);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return fs;
	}

}
