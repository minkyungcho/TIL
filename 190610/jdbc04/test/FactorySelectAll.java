package test;

import java.util.ArrayList;

import com.FactoryBiz;

import frame.Biz;
import vo.Factory;

public class FactorySelectAll {

	public static void main(String[] args) {
		Biz<String,Factory> biz = new FactoryBiz();
		
		try {
			ArrayList<Factory> fs = biz.selectAll();
			
			for (Factory f : fs) {
				System.out.println(f.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
