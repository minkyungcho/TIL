package com.sds;

public class UserBiz implements Biz{
	
	// Dao 불러야함.
	Dao dao; 
	
	// Dao의 getter, setter 생성
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	// dao의 insert 호출
	public void register() {
		dao.insert();
	}
	
}
