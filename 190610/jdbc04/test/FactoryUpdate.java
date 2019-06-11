package test;

import com.FactoryBiz;

import frame.Biz;
import vo.Factory;

public class FactoryUpdate {

	public static void main(String[] args) {
		Factory f = new Factory("4","","GANGNAM");
		Biz<String, Factory> biz = new FactoryBiz();
		try {
			biz.update(f);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
