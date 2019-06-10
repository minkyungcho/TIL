package test;

import com.FactoryBiz;

import frame.Biz;
import vo.Factory;

public class FactorySelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Biz<String,Factory> biz = new FactoryBiz();
		
		try {
			Factory f = biz.select("2");
			System.out.println(f.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
