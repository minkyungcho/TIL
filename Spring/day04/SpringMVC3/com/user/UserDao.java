package com.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
//import com.mapper.SearchMapper;
import com.mapper.UserMapper;
import com.vo.User;

@Repository("udao")
public class UserDao implements Dao<String, User> {
	
	@Autowired
	UserMapper um;
	
	@Override
	public void insert(User v) throws Exception {
		System.out.println(v+" UserDao Inserted...");
		um.insert(v);
	}

	@Override
	public void update(User v) throws Exception {
		System.out.println(v+" UserDao Updated...");		
		um.update(v);
	}

	@Override
	public void delete(String k) throws Exception {
		System.out.println(k+" UserDao Deleted...");		
		um.delete(k);
	}

	@Override
	public User select(String k) throws Exception {
		System.out.println(k+" UserDao Selected...");
		return um.select(k);
	}

	@Override
	public ArrayList<User> select() throws Exception {
		return um.selectall();
	}

}
