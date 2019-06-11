package test;

import com.ProductsBiz;

import frame.Biz;
import vo.Products;

public class Max {

	public static void main(String[] args) {
		Biz<String,Products> biz= new ProductsBiz();
		ProductsBiz pbiz = (ProductsBiz) biz;
		try {
			Products p=pbiz.max("2");
			System.out.println(p.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
