package inter;

public class View {

	public static void main(String[] args) {
		Shop shop = new Auction();
		Shop shop1 = new Gmarket();
		
		
		shop.register();
		shop.login();
		shop.logout();
		shop.order();
		
	}

}
