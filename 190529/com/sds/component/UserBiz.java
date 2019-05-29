package com.sds.component;

import java.util.ArrayList;

import com.sds.frame.Biz;
import com.sds.frame.Dao;
import com.sds.vo.User;

public class UserBiz extends Biz<String, User> {
	Dao<String, User> dao; // Biz�� Dao�� �ʿ���.
	
	public UserBiz() {
		dao = new UserDao();
	}
	
	@Override
	public void register(User v) throws Exception {
		checkData(v);
		transactionStart();
		try {
			dao.insert(v); // insert�� Exception ������ ���⼭�� Exception ��������.
		}catch(Exception e) {
			throw e;
		}finally {
			transactionEnd();
		}
	}

	@Override
	public void remove(String k) throws Exception {
		transactionStart();
		try {
			dao.delete(k); // insert�� Exception ������ ���⼭�� Exception ��������.
		}catch(Exception e) {
			throw e;
		}finally {
			transactionEnd();
		}
		
	}

	@Override
	public void modify(User v) throws Exception {
		checkData(v);
		transactionStart();
		try {
			dao.update(v); // insert�� Exception ������ ���⼭�� Exception ��������.
		}catch(Exception e) {
			throw e;
		}finally {
			transactionEnd();
		}
		
	}

	@Override
	public User get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public ArrayList<User> get() throws Exception {
		return dao.select();
	}

}
