package com.user;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.Biz;
import com.frame.Dao;
import com.frame.LogAdvice;

@Service("ubiz")
public class UserBiz implements Biz<String, User> {
//	@Autowired // 어떤게 들어와도 상관 없다
	
	@Resource(name="uod")
	Dao<String, User> dao;
	
//	LogAdvice log;
	
	public UserBiz() {
//		log = new LogAdvice();
	}
	
	public void startBiz() {
//		log.printlog();
		System.out.println("Start Biz...");
	}
	
	public void endBiz() {
//		log.printlog();
		System.out.println("End Biz...");
	}
	
	public Dao<String, User> getDao() {
//		log.printlog();
		return dao;
	}

	public void setDao(Dao<String, User> dao) {
//		log.printlog();
		this.dao = dao;
	}

	@Override
	public void insert(User v) throws Exception {
//		log.printlog();
		dao.insert(v);
	}

	@Override
	public void update(User v) throws Exception {
//		log.printlog();
		dao.update(v);
	}

	@Override
	public void delete(String k) throws Exception {
//		log.printlog();
		dao.delete(k);
	}

	@Override
	public User select(String k) throws Exception {
//		log.printlog();
		Thread.sleep(2800);
		System.out.println("-------- Biz Selected --------");
		/*if(k.equals("id00")) {
			throw new Exception("Not Found Exception..");
		}*/
		return dao.select(k);
	}

	@Override
	public ArrayList<User> select() throws Exception {
//		log.printlog();
		return dao.select();
	}

}
