package test;

import com.FactoryBiz;

import frame.Biz;
import vo.Factory;

public class FactoryDelete {

	public static void main(String[] args) {
		String str = "3";
		Biz<String, Factory> biz = new FactoryBiz();
		try {
			biz.delete(str);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
