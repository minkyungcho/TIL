package com.sds;

public class UserBiz implements Biz{
	
	// Dao �ҷ�����.
	Dao dao; 
	
	// Dao�� getter, setter ����
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	// dao�� insert ȣ��
	public void register() {
		dao.insert();
	}
	
}
