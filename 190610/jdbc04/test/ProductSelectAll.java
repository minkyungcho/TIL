package test;

import java.util.ArrayList;

import com.ProductsBiz;

import frame.Biz;
import vo.Products;

public class ProductSelectAll {

	public static void main(String[] args) {
		Biz<String,Products> biz= new ProductsBiz();
		try {
		ArrayList<Products> ps=biz.selectAll();
			
			for(Products p : ps) {
				System.out.println(p.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
