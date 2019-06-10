package test;

import com.FactoryBiz;

import frame.Biz;
import vo.Factory;

public class FactoryInsert {

	public static void main(String[] args) {

		Factory f = new Factory("4", "BLUEBOTTLE", "GANGNAM");
		Biz<String, Factory> biz = new FactoryBiz();
		
		try {
			biz.insert(f);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}

}
