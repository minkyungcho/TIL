package frame;

public class Sql {
	public static String insertProducts = "INSERT INTO T_PRODUCTS VALUES (?,?,?,?,SYSDATE,?,?,?)";
	public static String deleteProducts = "DELETE FROM T_PRODUCTS WHERE PDNO = ?";
	public static String updateProducts = "UPDATE T_PRODUCTS SET PDMOUNT=? WHERE PDNO=?";
	public static String selectProducts = "SELECT * FROM T_PRODUCTS WHERE PDNO = ?";
	public static String selectAllProducts = "SELECT * FROM T_PRODUCTS";
	public static String insertFactory = "INSERT INTO T_FACTORY VALUES (?,?,?)";
	public static String deleteFactory = "DELETE FROM T_FACTORY WHERE FACTNO = ?";
	public static String updateFactory = "UPDATE T_FACTORY SET FACLOC=? WHERE FACTNO=?";
	public static String selectFactory = "SELECT * FROM T_FACTORY WHERE FACTNO = ?";
	public static String selectAllFactory = "SELECT * FROM T_FACTORY";
}
