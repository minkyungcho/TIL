package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.frame.Dao;
import com.frame.Sql;
import com.vo.User;

public class UserDao extends Dao<String, User> {

	@Override
	public void insert(User v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.insertUser);
			pstmt.setString(1, v.getId());
			pstmt.setString(2, v.getPwd());
			pstmt.setString(3, v.getName());
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
			pstmt = con.prepareStatement(Sql.deleteUser);
			pstmt.setString(1, k);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public void update(User v, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Sql.updateUser);
			pstmt.setString(1, v.getPwd());
			pstmt.setString(2, v.getName());
			pstmt.setString(3, v.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
	}

	@Override
	public User select(String k, Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		try {
			pstmt = con.prepareStatement(Sql.selectUser);
			pstmt.setString(1, k);
			rset = pstmt.executeQuery(); 
			rset.next();
			String uid = rset.getString("ID");
			String upwd = rset.getString("PWD");
			String uname = rset.getString("NAME");
			user = new User(uid,upwd,uname);
			pstmt.executeUpdate();
			System.out.println(uid+" "+upwd+" "+uname);
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
		return user;
	}

	@Override
	public ArrayList<User> select(Connection con) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		ArrayList<User> users= new ArrayList<>();
		try {
			pstmt = con.prepareStatement(Sql.selectAllUser);
			rset = pstmt.executeQuery(); 
			while(rset.next()) {
				String uid = rset.getString("ID");
				String upwd = rset.getString("PWD");
				String uname = rset.getString("NAME");
				user = new User(uid,upwd,uname);
				users.add(user);
				System.out.println(uid+" "+upwd+" "+uname);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			close(pstmt);
		}
		return users;
	}

}
