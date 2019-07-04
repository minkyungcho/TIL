package com.user;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.Biz;
import com.frame.Dao;
import com.frame.Search;
import com.vo.User;

@Service("ubiz")
public class UserBiz implements Biz<String, User>, Search<String,User> {

	@Resource(name="ud")
	Dao<String,User> dao;

	@Resource(name="ud")
	Search<String,User> search;

	@Transactional
	@Override
	public void register(User v) throws Exception {	
		dao.insert(v);
		
	}

	@Override
	public void modify(User v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
		
	}

	@Override
	public User get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<User> get() throws Exception {
		return dao.select();
	}

	@Override
	public ArrayList<User> search(String k) throws Exception {
		System.out.println(k+" Searched...");
		return search.search(k);
	}

}
