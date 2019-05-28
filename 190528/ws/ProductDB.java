package ws;

import java.util.ArrayList;

public class ProductDB extends DB<Product, String> {


	@Override
	public void Create(Product obj) {
		System.out.println(obj.getId()+" "+obj.getName()+" "+obj.getPrice()+" Created...");
		
	}
	
	@Override
	public Product Select(String obj) {
		Product p = null;
		System.out.println("Search:"+obj);
		p = new Product("p02", "Pants", 10000);
		return p;
	}

	@Override
	public ArrayList<Product> Select() {
		Product p1 = new Product("p001", "Hat", 100000);
		Product p2 = new Product("p002", "Pet", 200000);
		Product p3 = new Product("p003", "Glasses", 300000);
		Product p4 = new Product("p004", "Phone", 400000);
		ArrayList<Product> ProductList = new ArrayList<Product>();
		ProductList.add(p1);
		ProductList.add(p2);
		ProductList.add(p3);
		ProductList.add(p4);
		
		return ProductList;
	}
	
	@Override
	public void Update(Product obj) {
		System.out.println(obj.getId()+" Updated...");				
	}

	@Override
	public void Delete(Product obj) {
		System.out.println(obj.getId()+" Deleted...");				
	}
}
