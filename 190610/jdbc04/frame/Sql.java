package frame;

public class Sql {
	public static String insertProducts = "INSERT INTO T_PRODUCTS VALUES (?,?,?,?,SYSDATE,?,?,?)";
	public static String deleteProducts = "DELETE FROM T_PRODUCTS WHERE PDNO =?";
	public static String updateProducts = "UPDATE T_PRODUCTS SET PDAMOUNT=? WHERE PDNO=?";
	public static String selectProducts = "SELECT * FROM T_PRODUCTS WHERE PDNO = ?";
	public static String selectAllProducts = "SELECT * FROM T_PRODUCTS ORDER BY PDNO";
	public static String insertFactory = "INSERT INTO T_FACTORY VALUES (?,?,?)";
	public static String deleteFactory = "DELETE FROM T_FACTORY WHERE FACTNO =?";
	public static String updateFactory = "UPDATE T_FACTORY SET FACLOC=? WHERE FACTNO=?";
	public static String selectFactory = "SELECT * FROM T_FACTORY WHERE FACTNO =?";
	public static String selectAllFactory = "SELECT * FROM T_FACTORY ORDER BY FACTNO";
	public static String maxPriceProductsInTheFactory = "SELECT * FROM T_FACTORY f, T_PRODUCTS p WHERE f.FACTNO = p.FACTNO AND p.PDPRICE IN (SELECT MAX(p2.PDPRICE) FROM T_PRODUCTS p2 GROUP BY FACTNO) AND f.FACTNO = ?";
}
